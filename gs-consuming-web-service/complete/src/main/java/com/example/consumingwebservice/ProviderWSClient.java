
package com.example.consumingwebservice;

import com.example.consumingwebservice.wsdl.ParametrosUbicaPlusDTO;
import com.example.consumingwebservice.wsdl.String;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

@Component
public class ProviderWSClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(ProviderWSClient.class);

	public void post() {

		ParametrosUbicaPlusDTO request = new ParametrosUbicaPlusDTO();
		String codi = new String();
		codi.setValue(java.lang.String.valueOf(5632));
		request.setCodigoInformacion(codi);
		String tipo = new String();
		tipo.setValue(java.lang.String.valueOf(1));
		request.setTipoIdentificacion(tipo);
		String motivo = new String();
		motivo.setValue(java.lang.String.valueOf(24));
		request.setTipoIdentificacion(motivo);

		String response = (String) getWebServiceTemplate()
				.marshalSendAndReceive("https://miportafoliouat.transunion.co/ws/UbicaPlusWebService/services/UbicaPlus?wsdl", request,
						new SoapActionCallback(
								"http://localhost:8080/ParametrosUbicaPlusDTO"));
		System.out.println(response);
	}

}
