package org.union.trans.ubica.plus.soap.client.payload;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "Error")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Error implements Serializable {
    @XmlElement(name = "CodigoInformacion")
    private String CodigoInformacion;

    @XmlElement(name = "MotivoConsulta")
    private String MotivoConsulta;

    @XmlElement(name = "NumeroIdentificacion")
    private String NumeroIdentificacion;

    @XmlElement(name = "TipoIdentificacion")
    private String TipoIdentificacion;

    @XmlElement(name = "Operacion")
    private String Operacion;

    @XmlElement(name = "CodigoError")
    private String CodigoError;

    @XmlElement(name = "MensajeError")
    private String MensajeError;

    public Error() {}

    public String getCodigoInformacion() {
        return CodigoInformacion;
    }

    public void setCodigoInformacion(String codigoInformacion) {
        CodigoInformacion = codigoInformacion;
    }

    public String getMotivoConsulta() {
        return MotivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        MotivoConsulta = motivoConsulta;
    }

    public String getNumeroIdentificacion() {
        return NumeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        NumeroIdentificacion = numeroIdentificacion;
    }

    public String getTipoIdentificacion() {
        return TipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        TipoIdentificacion = tipoIdentificacion;
    }

    public String getOperacion() {
        return Operacion;
    }

    public void setOperacion(String operacion) {
        Operacion = operacion;
    }

    public String getCodigoError() {
        return CodigoError;
    }

    public void setCodigoError(String codigoError) {
        CodigoError = codigoError;
    }

    public String getMensajeError() {
        return MensajeError;
    }

    public void setMensajeError(String mensajeError) {
        MensajeError = mensajeError;
    }

    @Override
    public String toString() {
        return "Error{" +
                "CodigoInformacion='" + CodigoInformacion + '\'' +
                ", MotivoConsulta='" + MotivoConsulta + '\'' +
                ", NumeroIdentificacion='" + NumeroIdentificacion + '\'' +
                ", TipoIdentificacion='" + TipoIdentificacion + '\'' +
                ", Operacion='" + Operacion + '\'' +
                ", CodigoError='" + CodigoError + '\'' +
                ", MensajeError='" + MensajeError + '\'' +
                '}';
    }
}
