package org.union.trans.ubica.plus.soap.client.api;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.union.trans.ubica.plus.soap.client.SoapClient;
import org.union.trans.ubica.plus.soap.client.payload.*;

@Service
public class UbicaService {

    private UbicaRepository repository;
    private SoapClient soapClient;
    private ModelMapper modelMapper;

    public UbicaService(UbicaRepository repository, SoapClient soapClient, ModelMapper modelMapper) {
        this.repository = repository;
        this.soapClient = soapClient;
        this.modelMapper = modelMapper;
    }
    public RestApiResponse submit(RestApiRequest request) {
        SoapRequest soapRequest = modelMapper.map(request, SoapRequest.class);
        SoapResponse soapResponse = soapClient.call(soapRequest);

        RestApiResponse response = new RestApiResponse();

        if (soapResponse.getCifinError() != null) {
            response.setError(modelMapper.map(soapResponse.getCifinError(), CifinError.class));
        } else {
            response.setData(modelMapper.map(soapResponse.getCIFIN(), CIFIN.class));
        }
        return response;
    }
}
