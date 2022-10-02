package org.union.trans.ubica.plus.soap.client.payload;

import java.io.Serializable;

public class SoapResponse implements Serializable {
    private CIFIN CIFIN;
    private CifinError CifinError;
    private boolean is401;
    private boolean is200 = true;
    private String errorMessage;
    private String errorCode;

    public SoapResponse() {}

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

    public CIFIN getCIFIN() {
        return CIFIN;
    }

    public void setCIFIN(CIFIN CIFIN) {
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

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public CifinError getCifinError() {
        return CifinError;
    }

    public void setCifinError(CifinError cifinError) {
        CifinError = cifinError;
    }
}
