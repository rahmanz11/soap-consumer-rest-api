package org.union.trans.ubica.plus.soap.client.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "Tercero")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Tercero implements Serializable {

    @XmlElement(name = "IdentificadorLinea")
    private String IdentificadorLinea;

    @XmlElement(name = "TipoIdentificacion")
    private String TipoIdentificacion;

    @XmlElement(name = "CodigoTipoIndentificacion")
    private String CodigoTipoIndentificacion;

    @XmlElement(name = "NumeroIdentificacion")
    private String NumeroIdentificacion;

    @XmlElement(name = "Nombre1")
    private String Nombre1;

    @XmlElement(name = "Apellido1")
    private String Apellido1;

    @XmlElement(name = "Apellido2")
    private String Apellido2;

    @XmlElement(name = "NombreTitular")
    private String NombreTitular;

    @XmlElement(name = "NombreCiiu")
    private String NombreCiiu;

    @XmlElement(name = "LugarExpedicion")
    private String LugarExpedicion;

    @XmlElement(name = "FechaExpedicion")
    private String FechaExpedicion;

    @XmlElement(name = "Estado")
    private String Estado;

    @XmlElement(name = "NumeroInforme")
    private String NumeroInforme;

    @XmlElement(name = "EstadoTitular")
    private String EstadoTitular;

    @XmlElement(name = "RangoEdad")
    private String RangoEdad;

    @XmlElement(name = "CodigoCiiu")
    private String CodigoCiiu;

    @XmlElement(name = "CodigoDepartamento")
    private String CodigoDepartamento;

    @XmlElement(name = "CodigoMunicipio")
    private String CodigoMunicipio;

    @XmlElement(name = "Fecha")
    private String Fecha;

    @XmlElement(name = "Hora")
    private String Hora;

    @XmlElement(name = "Entidad")
    private String Entidad;

    @XmlElement(name = "RespuestaConsulta")
    private String RespuestaConsulta;

    @XmlElement(name = "UbicaPlusCifin")
    private UbicaPlusCifin UbicaPlusCifin;
}
