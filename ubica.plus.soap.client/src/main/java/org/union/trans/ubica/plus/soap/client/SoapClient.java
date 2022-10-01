package org.union.trans.ubica.plus.soap.client;

import com.webservice.ubicaplus.UbicaPlusWS;
import com.webservice.ubicaplus.dto.ParametrosUbicaPlusDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.union.trans.ubica.plus.soap.client.payload.CIFIN;
import org.union.trans.ubica.plus.soap.client.payload.SoapRequest;
import org.union.trans.ubica.plus.soap.client.payload.SoapResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.BindingProvider;
import java.io.StringReader;
import java.util.Map;

@Component
public class SoapClient {

	public SoapResponse call(SoapRequest request) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        UbicaPlusWS ubicaws = (UbicaPlusWS) context.getBean("ubicaClient");

        if (request.getUserName() != null && request.getUserName().trim() != ""
		&& request.getPassword() != null && request.getPassword().trim() != "") {
			Map<String, Object> reqContext = ((BindingProvider)
					ubicaws).getRequestContext();
			reqContext.put(BindingProvider.USERNAME_PROPERTY, "307883");
			reqContext.put(BindingProvider.PASSWORD_PROPERTY, "Equidad2208*");
		}

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

		org.xmlsoap.schemas.soap.encoding.String response;

		try {
			response = ubicaws.consultaUbicaPlus(dto);
			System.out.println(response.getValue());
		} catch (RuntimeException t) {
			if (t.getMessage().contains("401: Unauthorized")) {
				return new SoapResponse(true);
			} else {
				return new SoapResponse(t.getMessage());
			}
		}

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(CIFIN.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			CIFIN cifin = (CIFIN) jaxbUnmarshaller.unmarshal(new StringReader(response.getValue()));

			System.out.println(cifin);

			return new SoapResponse(cifin);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

        return null;
	}

}