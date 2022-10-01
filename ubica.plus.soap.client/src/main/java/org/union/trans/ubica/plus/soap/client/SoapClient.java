package org.union.trans.ubica.plus.soap.client;

import com.webservice.ubicaplus.UbicaPlusWS;
import com.webservice.ubicaplus.dto.ParametrosUbicaPlusDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.xml.ws.BindingProvider;
import java.util.Map;

@Component
public class SoapClient implements CommandLineRunner {

	@Override
	public void run(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        UbicaPlusWS helloworld= (UbicaPlusWS) context.getBean("ubicaClient");

		Map<String, Object> reqContext = ((BindingProvider)
				helloworld).getRequestContext();
		reqContext.put(BindingProvider.USERNAME_PROPERTY, "307883");
		reqContext.put(BindingProvider.PASSWORD_PROPERTY, "Equidad2208*");

		ParametrosUbicaPlusDTO hreq = new ParametrosUbicaPlusDTO();

		org.xmlsoap.schemas.soap.encoding.String codigoInformacion = new org.xmlsoap.schemas.soap.encoding.String();
		codigoInformacion.setValue(String.valueOf(5632));
		hreq.setCodigoInformacion(codigoInformacion);

		org.xmlsoap.schemas.soap.encoding.String tipoIdentificacion = new org.xmlsoap.schemas.soap.encoding.String();
		tipoIdentificacion.setValue(String.valueOf(1));
		hreq.setTipoIdentificacion(tipoIdentificacion);

		org.xmlsoap.schemas.soap.encoding.String motivoConsulta = new org.xmlsoap.schemas.soap.encoding.String();
		motivoConsulta.setValue(String.valueOf(24));
		hreq.setMotivoConsulta(motivoConsulta);

		org.xmlsoap.schemas.soap.encoding.String numeroIdentificacion = new org.xmlsoap.schemas.soap.encoding.String();
		numeroIdentificacion.setValue(String.valueOf(262744));
		hreq.setNumeroIdentificacion(numeroIdentificacion);

		org.xmlsoap.schemas.soap.encoding.String primerApellido = new org.xmlsoap.schemas.soap.encoding.String();
		primerApellido.setValue("SEPULVEDA");
		hreq.setPrimerApellido(primerApellido);

		org.xmlsoap.schemas.soap.encoding.String hres = helloworld.consultaUbicaPlus(hreq);

        System.out.println(hres.getValue());
	}

}