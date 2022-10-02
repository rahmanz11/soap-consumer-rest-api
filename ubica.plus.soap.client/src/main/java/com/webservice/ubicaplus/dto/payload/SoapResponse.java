package org.union.trans.ubica.plus.soap.client.payload;

import lombok.*;

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
    private boolean providerUnreachable;
    private String errorMessage;
    private String errorCode;
    private String soapResponseStr;
}
