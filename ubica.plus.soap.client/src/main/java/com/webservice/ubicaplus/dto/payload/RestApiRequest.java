package org.union.trans.ubica.plus.soap.client.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestApiRequest implements Serializable {
    private String userName;
    private String password;
    private String codigoInformacion;
    private String tipoIdentificacion;
    private String motivoConsulta;
    private String numeroIdentificacion;
    private String primerApellido;
}
