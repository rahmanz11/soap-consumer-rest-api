
package com.example.consumingwebservice.middleware;

import com.databasesandlife.util.spring.SoapClientSecurityHeaderWriter;
import com.example.consumingwebservice.wsdl.ParametrosUbicaPlusDTO;
import com.example.consumingwebservice.wsdl.QName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.soap.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class ProviderWSClient extends WebServiceGatewaySupport {

	@Value("${client.default-uri}")
	private String defaultUri;

	@Value("${client.callback}")
	private String callbackUri;

	@Value("${client.user.name}")
	private String userName;

	@Value("${client.user.password}")
	private String userPassword;

	@Autowired
	private WebServiceTemplate webServiceTemplate;

	private static final Logger log = LoggerFactory.getLogger(ProviderWSClient.class);

	public void post() throws JAXBException {
		/*
		ParametrosUbicaPlusDTO request = new ParametrosUbicaPlusDTO();
		com.example.consumingwebservice.wsdl.String codi = new com.example.consumingwebservice.wsdl.String();
		codi.setValue(java.lang.String.valueOf(5632));
		request.setCodigoInformacion(codi);
		com.example.consumingwebservice.wsdl.String tipo = new com.example.consumingwebservice.wsdl.String();
		tipo.setValue(java.lang.String.valueOf(1));
		request.setTipoIdentificacion(tipo);
		com.example.consumingwebservice.wsdl.String motivo = new com.example.consumingwebservice.wsdl.String();
		motivo.setValue(java.lang.String.valueOf(24));
		request.setMotivoConsulta(motivo);

		JAXBContext jaxbContext = JAXBContext.newInstance(ParametrosUbicaPlusDTO.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		QName qName = new QName();
		qName.setValue(new javax.xml.namespace.QName("ubic","local"));
		//If we DO NOT have JAXB annotated class
		JAXBElement<ParametrosUbicaPlusDTO> jaxbElement =
				new JAXBElement<ParametrosUbicaPlusDTO>( qName.getValue(),
						ParametrosUbicaPlusDTO.class,
						request);

		jaxbMarshaller.marshal(jaxbElement, System.out);

		JAXBElement<String> response = (JAXBElement<String>)webServiceTemplate.marshalSendAndReceive(jaxbElement,
				new SoapClientSecurityHeaderWriter(userName, userPassword));
	*/
		String soapMessageString = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ubic=\"http://ubicaplus.webservice.com\">" +
				"   <soapenv:Header/>" +
				"   <soapenv:Body>" +
				"      <ubic:consultaUbicaPlus soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">" +
				"         <parametrosUbica xsi:type=\"dto:ParametrosUbicaPlusDTO\" xmlns:dto=\"http://dto.ubicaplus.webservice.com\">" +
				"            <codigoInformacion xsi:type=\"soapenc:string\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">5632</codigoInformacion>" +
				"            <motivoConsulta xsi:type=\"soapenc:string\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">24</motivoConsulta>" +
				"            <numeroIdentificacion xsi:type=\"soapenc:string\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">?</numeroIdentificacion>" +
				"            <primerApellido xsi:type=\"soapenc:string\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">?</primerApellido>" +
				"            <tipoIdentificacion xsi:type=\"soapenc:string\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">1</tipoIdentificacion>" +
				"         </parametrosUbica>" +
				"      </ubic:consultaUbicaPlus>" +
				"   </soapenv:Body>" +
				"</soapenv:Envelope>";
		SOAPMessage m = createRequest(soapMessageString);
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			m.writeTo(out);
			String strMsg = new String(out.toByteArray());
			System.out.println(strMsg);
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		JAXBElement<String> response =
				(JAXBElement<String>) webServiceTemplate.
						marshalSendAndReceive(jaxbElement, new WebServiceMessageCallback() {
					public void doWithMessage(WebServiceMessage webServiceMessage) {
						try {
							SoapMessage soapMessage = (SoapMessage) webServiceMessage;
							soapMessage.setSoapAction("consultaUbicaPlus");

							SoapHeader header = soapMessage.getSoapHeader();
							StringSource headerSource = new StringSource("<wsse>\n" +
									"<Username>" + "johnsmith" + "</Username>\n" +
									"<Password>" + "1234" + "</Password>\n" +
									"</wsse>");
							Transformer transformer = TransformerFactory.newInstance().newTransformer();
							transformer.transform(headerSource, header.getResult());

						} catch (Exception e) {
							new RuntimeException(e);
						}
					}
				});
		 */

		/*
		String response = (String) webServiceTemplate
				.marshalSendAndReceive(defaultUri, jaxbElement,
						new SoapActionCallback(
								callbackUri));

		 */
	}

	private static SOAPMessage createRequest(String msg) {
		SOAPMessage request = null;
		try {
			MessageFactory msgFactory = MessageFactory
					.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
			request = msgFactory.createMessage();

			SOAPPart msgPart = request.getSOAPPart();
			SOAPEnvelope envelope = msgPart.getEnvelope();
			SOAPBody body = envelope.getBody();

			javax.xml.transform.stream.StreamSource _msg = new javax.xml.transform.stream.StreamSource(
					new java.io.StringReader(msg));
			msgPart.setContent(_msg);

			request.saveChanges();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return request;
	}

}
