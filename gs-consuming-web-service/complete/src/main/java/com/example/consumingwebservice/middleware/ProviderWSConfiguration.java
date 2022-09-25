
package com.example.consumingwebservice.middleware;

import org.apache.http.Header;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.RequestDefaultHeaders;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Configuration
public class ProviderWSConfiguration {

	@Value("${client.default-uri}")
	private String defaultUri;

	@Value("${client.context-path}")
	private String contextPath;

	@Value("${client.user.name}")
	private String userName;

	@Value("${client.user.password}")
	private String userPassword;

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath(contextPath);
		return marshaller;
	}

	@Bean
	public ProviderWSClient client(Jaxb2Marshaller marshaller) {
		ProviderWSClient client = new ProviderWSClient();
		client.setDefaultUri(defaultUri);
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

	@Bean
	public WebServiceTemplate webServiceTemplate() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath(contextPath);
		WebServiceTemplate template = new WebServiceTemplate(marshaller, marshaller);
		template.setDefaultUri(defaultUri);
		template.setMessageSender(defaultMessageSender());
		return template;
	}

	private String base64authUserPassword() {
		String userpassword = userName + ":" + userPassword;
		String encodedAuthorization = new String(Base64.getEncoder().encode(userpassword.getBytes()));
		return encodedAuthorization;
	}

	@Bean
	HttpClient createHttpClient() {
		List<Header> headers = new ArrayList<>();
		BasicHeader authHeader = new BasicHeader("Authorization", "Basic " + base64authUserPassword());
		headers.add(authHeader);
		RequestDefaultHeaders reqHeader = new RequestDefaultHeaders(headers);

		CloseableHttpClient httpClient =
				HttpClients.custom()
						.addInterceptorFirst(new HttpComponentsMessageSender.RemoveSoapHeadersInterceptor())
						.addInterceptorLast(reqHeader)
						.build();
		return httpClient;
	}

	@Bean
	public HttpComponentsMessageSender defaultMessageSender() {

		HttpComponentsMessageSender messageSender = new HttpComponentsMessageSender(createHttpClient());
		return messageSender;
	}
}
