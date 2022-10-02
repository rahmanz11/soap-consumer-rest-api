package org.union.trans.ubica.plus.soap.client.provider;

import com.webservice.ubicaplus.UbicaPlusWS;
import com.webservice.ubicaplus.dto.ParametrosUbicaPlusDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.union.trans.ubica.plus.soap.client.payload.CIFIN;
import org.union.trans.ubica.plus.soap.client.payload.CifinError;
import org.union.trans.ubica.plus.soap.client.payload.SoapRequest;
import org.union.trans.ubica.plus.soap.client.payload.SoapResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.BindingProvider;
import java.io.StringReader;
import java.util.Map;

@Service
public class SoapClient {

	public SoapResponse call(SoapRequest request) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        UbicaPlusWS ubicaws = (UbicaPlusWS) context.getBean("ubicaClient");

        if (request.getUserName() != null && request.getUserName().trim() != ""
		&& request.getPassword() != null && request.getPassword().trim() != "") {
			Map<String, Object> reqContext = ((BindingProvider)
					ubicaws).getRequestContext();
			reqContext.put(BindingProvider.USERNAME_PROPERTY, request.getUserName());
			reqContext.put(BindingProvider.PASSWORD_PROPERTY, request.getPassword());
		}
        SoapResponse response = new SoapResponse();
		org.xmlsoap.schemas.soap.encoding.String soapResponse;
		try {
			soapResponse = ubicaws.consultaUbicaPlus(this.prepareSoapRequest(request));
			String responseString = soapResponse.getValue();
			System.out.println("Response String: " + responseString);
			boolean error = false;
			if (responseString.contains("CifinError")) {
				error = true;
			}

			this.prepareSoapResponse(responseString, error, response);

		} catch (RuntimeException t) {
			t.printStackTrace();
			if (t.getCause().getMessage().contains("401: Unauthorized")) {
				System.err.println("Unauthorized Access");
				response.set401(true);
			} else if (t.getCause().getMessage().contains("IOException invoking")) {
				System.err.println("IOException invoking");
				response.setErrorMessage("Provider Unreachable");
				response.set200(false);
				response.setProviderUnreachable(true);
			}  else {
				System.err.println("SOAP Exception");
				response.setErrorMessage(t.getMessage());
				response.set200(false);
			}
		}

		return response;
	}

	private ParametrosUbicaPlusDTO prepareSoapRequest(SoapRequest request) {
		ParametrosUbicaPlusDTO dto = new ParametrosUbicaPlusDTO();

		if (request.getCodigoInformacion() != null && request.getCodigoInformacion().trim() != "") {
			org.xmlsoap.schemas.soap.encoding.String codigoInformacion = new org.xmlsoap.schemas.soap.encoding.String();
			codigoInformacion.setValue(request.getCodigoInformacion());
			dto.setCodigoInformacion(codigoInformacion);
		}

		if (request.getTipoIdentificacion() != null && request.getTipoIdentificacion().trim() != "") {
			org.xmlsoap.schemas.soap.encoding.String tipoIdentificacion = new org.xmlsoap.schemas.soap.encoding.String();
			tipoIdentificacion.setValue(request.getTipoIdentificacion());
			dto.setTipoIdentificacion(tipoIdentificacion);
		}

		if (request.getMotivoConsulta() != null && request.getMotivoConsulta().trim() != "") {
			org.xmlsoap.schemas.soap.encoding.String motivoConsulta = new org.xmlsoap.schemas.soap.encoding.String();
			motivoConsulta.setValue(request.getMotivoConsulta());
			dto.setMotivoConsulta(motivoConsulta);
		}

		if (request.getNumeroIdentificacion() != null && request.getNumeroIdentificacion().trim() != "") {
			org.xmlsoap.schemas.soap.encoding.String numeroIdentificacion = new org.xmlsoap.schemas.soap.encoding.String();
			numeroIdentificacion.setValue(request.getNumeroIdentificacion());
			dto.setNumeroIdentificacion(numeroIdentificacion);
		}

		if (request.getPrimerApellido() != null && request.getPrimerApellido().trim() != "") {
			org.xmlsoap.schemas.soap.encoding.String primerApellido = new org.xmlsoap.schemas.soap.encoding.String();
			primerApellido.setValue(request.getPrimerApellido());
			dto.setPrimerApellido(primerApellido);
		}

		return dto;
	}

	private void prepareSoapResponse(String responseString, boolean error, SoapResponse response) {
		JAXBContext jaxbContext;
		try {
			if (!error) {
				jaxbContext = JAXBContext.newInstance(CIFIN.class);
			} else {
				jaxbContext = JAXBContext.newInstance(CifinError.class);
			}

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			if (!error) {
				CIFIN cifin = (CIFIN) jaxbUnmarshaller.unmarshal(new StringReader(responseString));
				System.out.println(cifin);
				response.setCIFIN(cifin);
			} else {
				CifinError cifinError = (CifinError) jaxbUnmarshaller.unmarshal(new StringReader(responseString));
				System.out.println(cifinError);
				response.setCifinError(cifinError);
			}
		} catch (JAXBException e) {
			System.err.println("Response conversion exception");
			response.setErrorMessage(e.getMessage());
			response.set200(false);
		}

		response.setSoapResponseStr(responseString);
	}

}
