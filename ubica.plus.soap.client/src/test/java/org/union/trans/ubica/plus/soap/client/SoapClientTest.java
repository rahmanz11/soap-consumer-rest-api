package org.union.trans.ubica.plus.soap.client;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.union.trans.ubica.plus.soap.client.payload.SoapRequest;
import org.union.trans.ubica.plus.soap.client.payload.SoapResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ComponentScan("org.union.trans.ubica.plus.soap.client")
@SpringBootTest
public class SoapClientTest {
    @Autowired
    private SoapClient soapClient;
    private SoapRequest request;

    @Before
    public void setUp() {
        request = new SoapRequest();
        request.setUserName("307883");
        request.setPassword("Equidad2208*");
        request.setCodigoInformacion("5632");
        request.setMotivoConsulta("24");
        request.setNumeroIdentificacion("262744");
        request.setPrimerApellido("SEPULVEDA");
        request.setTipoIdentificacion("1");
    }

    @Test
    public void testSoapResponse() {
        SoapResponse response = soapClient.call(request);
        assertTrue(response.getCIFIN().getTercero().getIdentificadorLinea().equals("97324"));
        assertTrue(response.getCIFIN().getTercero().getCodigoDepartamento().equals("25"));
        assertTrue(response.getCIFIN().getTercero().getApellido1().equals("SEPULVEDA"));
        assertTrue(response.getCIFIN().getTercero().getUbicaPlusCifin().getGeneroTercero().equals("HOMBRE"));
    }

    @Test
    public void testSoapResponseErrorCode4() {
        request.setNumeroIdentificacion(null);
        SoapResponse response = soapClient.call(request);
        assertTrue(response.getCifinError().getError().getCodigoError().trim().equals("4"));
    }

    @Test
    public void test401UnauthorizedWithNullCredentials() {
        request.setUserName(null);
        request.setPassword(null);
        SoapResponse response = soapClient.call(request);
        assertTrue(response.isIs401() == true);
    }

    @Test
    public void test401UnauthorizedWithInvalidCredentials() {
        request.setUserName("dummy");
        request.setPassword("dummy");
        SoapResponse response = soapClient.call(request);
        assertTrue(response.isIs401() == true);
    }
}
