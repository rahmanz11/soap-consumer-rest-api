package org.union.trans.ubica.plus.soap.client.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestApiResponse implements Serializable {
    private CIFIN data;
    private CifinError error;

    public RestApiResponse(CIFIN data) {
        this.data = data;
    }

    public RestApiResponse(CifinError error) {
        this.error = error;
    }
}
