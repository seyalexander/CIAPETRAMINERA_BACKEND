package com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.request.RequestRegistroConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.response.ResponseRegistroConductor;

public interface IConductorRegistro {
    ResponseRegistroConductor registroConductor(RequestRegistroConductor request, long idUserAutenticado);
}
