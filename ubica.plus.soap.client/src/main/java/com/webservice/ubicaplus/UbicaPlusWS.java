package com.webservice.ubicaplus;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.2.4
 * 2022-10-02T16:07:02.020+06:00
 * Generated source version: 3.2.4
 *
 */
@WebService(targetNamespace = "http://ubicaplus.webservice.com", name = "UbicaPlusWS")
@XmlSeeAlso({org.xmlsoap.schemas.soap.encoding.ObjectFactory.class, com.webservice.ubicaplus.dto.ObjectFactory.class})
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface UbicaPlusWS {

    @WebMethod
    @WebResult(name = "consultaUbicaPlusReturn", targetNamespace = "http://ubicaplus.webservice.com", partName = "consultaUbicaPlusReturn")
    public org.xmlsoap.schemas.soap.encoding.String consultaUbicaPlus(
        @WebParam(partName = "parametrosUbica", name = "parametrosUbica")
        com.webservice.ubicaplus.dto.ParametrosUbicaPlusDTO parametrosUbica
    );
}
