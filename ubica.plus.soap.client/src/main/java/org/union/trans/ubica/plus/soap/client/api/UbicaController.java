package org.union.trans.ubica.plus.soap.client.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.union.trans.ubica.plus.soap.client.payload.RestApiRequest;
import org.union.trans.ubica.plus.soap.client.payload.RestApiResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UbicaController {

    private UbicaService service;
    public UbicaController(UbicaService service) {
        this.service = service;
    }

    @PostMapping("/submit")
    public RestApiResponse submit(@Valid @RequestBody RestApiRequest request) {
        return service.submit(request);
    }

}
