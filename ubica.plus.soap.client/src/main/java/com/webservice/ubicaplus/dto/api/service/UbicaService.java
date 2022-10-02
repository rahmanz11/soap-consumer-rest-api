package org.union.trans.ubica.plus.soap.client.api.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.union.trans.ubica.plus.soap.client.api.exception.BadRequestException;
import org.union.trans.ubica.plus.soap.client.api.exception.InternalServerException;
import org.union.trans.ubica.plus.soap.client.api.exception.ProviderServiceNotAvailableException;
import org.union.trans.ubica.plus.soap.client.api.exception.UnauthorizedException;
import org.union.trans.ubica.plus.soap.client.api.model.UbicaDetail;
import org.union.trans.ubica.plus.soap.client.provider.SoapClient;
import org.union.trans.ubica.plus.soap.client.api.repository.UbicaRepository;
import org.union.trans.ubica.plus.soap.client.payload.*;

import java.util.Date;

@Service
public class UbicaService {

//    @Autowired
//    private UbicaRepository repository;

    @Autowired
    private SoapClient soapClient;

    @Autowired
    private ModelMapper modelMapper;

    public RestApiResponse submit(RestApiRequest request) throws UnauthorizedException, BadRequestException,
            InternalServerException, ProviderServiceNotAvailableException {
        SoapRequest soapRequest = modelMapper.map(request, SoapRequest.class);
        SoapResponse soapResponse = soapClient.call(soapRequest);

        RestApiResponse response = new RestApiResponse();

        if (soapResponse.is401()) {
            throw new UnauthorizedException("Provider Service");
        } else if (soapResponse.getCifinError() != null) {
            throw new BadRequestException(soapResponse.getCifinError().getError().getMensajeError());
        } else if (soapResponse.isProviderUnreachable()) {
            throw new ProviderServiceNotAvailableException(soapResponse.getErrorMessage());
        } else if (soapResponse.getErrorMessage() != null) {
            throw new InternalServerException(soapResponse.getErrorMessage());
        } else {
            response.setData(modelMapper.map(soapResponse.getCIFIN(), CIFIN.class));
        }

//        UbicaDetail model = new UbicaDetail();
//        model.setDetail(soapResponse.getSoapResponseStr());
//        model.setCreatedAt(new Date());

        try {
//            repository.save(model);
        } catch (Throwable t) {
            throw new InternalServerException(String.format("Could not save data. Reason: %s", t.getMessage()));
        }

        return response;
    }
}
