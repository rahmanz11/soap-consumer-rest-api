package org.union.trans.ubica.plus.soap.client.payload;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "CIFIN")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CIFIN implements Serializable {

    @XmlElement(name = "Tercero")
    private Tercero Tercero;

    public CIFIN() {}

    @Override
    public String toString() {
        return "CIFIN{" +
                "Tercero=" + Tercero.toString() +
                '}';
    }

    public org.union.trans.ubica.plus.soap.client.payload.Tercero getTercero() {
        return Tercero;
    }

    public void setTercero(org.union.trans.ubica.plus.soap.client.payload.Tercero tercero) {
        Tercero = tercero;
    }
}
