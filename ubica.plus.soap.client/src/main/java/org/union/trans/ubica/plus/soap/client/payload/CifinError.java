package org.union.trans.ubica.plus.soap.client.payload;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "CifinError")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CifinError implements Serializable {
    @XmlElement(name = "Error")
    private Error Error;

    public CifinError() {}

    @Override
    public String toString() {
        return "CifinError{" +
                "Error=" + Error.toString() +
                '}';
    }

    public org.union.trans.ubica.plus.soap.client.payload.Error getError() {
        return Error;
    }

    public void setError(org.union.trans.ubica.plus.soap.client.payload.Error error) {
        Error = error;
    }
}
