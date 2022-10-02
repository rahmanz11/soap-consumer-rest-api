package org.union.trans.ubica.plus.soap.client.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SoapResponse implements Serializable {
    private CIFIN CIFIN;
    private CifinError CifinError;
    private boolean is401;
    private boolean is200 = true;
    private String errorMessage;
    private String errorCode;

    public SoapResponse(CIFIN cifin) {
        this.CIFIN = cifin;
    }

    public SoapResponse(CifinError cifinError) {
        this.CifinError = cifinError;
    }

    public SoapResponse(boolean is401) {
        this.is401 = is401;
    }

    public SoapResponse(String errorMessage) {
        this.is200 = false;
        this.errorMessage = errorMessage;
    }
}
