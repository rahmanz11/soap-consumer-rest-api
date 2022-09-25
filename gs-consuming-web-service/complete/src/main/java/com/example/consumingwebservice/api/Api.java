package com.example.consumingwebservice.api;

import com.example.consumingwebservice.middleware.ProviderWSClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;

@RequestMapping("api")
@RestController
public class Api {

    private ProviderWSClient client;

    public Api(ProviderWSClient client) {
        this.client = client;
    }

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.CREATED)
    public int test() throws JAXBException {
        client.post();
        return HttpStatus.CREATED.value();
    }
}
