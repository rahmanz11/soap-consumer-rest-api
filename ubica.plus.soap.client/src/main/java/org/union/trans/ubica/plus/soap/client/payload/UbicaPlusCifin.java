package org.union.trans.ubica.plus.soap.client.payload;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "UbicaPlusCifin")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class UbicaPlusCifin implements Serializable {

    @XmlElement(name = "GeneroTercero")
    private String GeneroTercero;

    public UbicaPlusCifin() {}

    @Override
    public String toString() {
        return "UbicaPlusCifin{" +
                "GeneroTercero='" + GeneroTercero + '\'' +
                '}';
    }

    public String getGeneroTercero() {
        return GeneroTercero;
    }

    public void setGeneroTercero(String generoTercero) {
        GeneroTercero = generoTercero;
    }
}
