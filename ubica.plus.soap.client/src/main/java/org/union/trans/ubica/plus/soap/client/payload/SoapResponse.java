package org.union.trans.ubica.plus.soap.client.payload;

import java.io.Serializable;

public class SoapResponse implements Serializable {
    private CIFIN CIFIN;
    private boolean is401;
    private boolean is200 = true;
    private String errorMessage;

    public SoapResponse() {}

    public SoapResponse(CIFIN cifin) {
        this.CIFIN = cifin;
    }

    public SoapResponse(boolean is401) {
        this.is401 = is401;
    }

    public SoapResponse(String errorMessage) {
        this.is200 = false;
        this.errorMessage = errorMessage;
    }

    public org.union.trans.ubica.plus.soap.client.payload.CIFIN getCIFIN() {
        return CIFIN;
    }

    public void setCIFIN(org.union.trans.ubica.plus.soap.client.payload.CIFIN CIFIN) {
        this.CIFIN = CIFIN;
    }

    public boolean isIs401() {
        return is401;
    }

    public void setIs401(boolean is401) {
        this.is401 = is401;
    }

    public boolean isIs200() {
        return is200;
    }

    public void setIs200(boolean is200) {
        this.is200 = is200;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
