package com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.request.RequestListaConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.response.ResponseListaConductor;

public interface IConductorLista {
    ResponseListaConductor listaConductores(RequestListaConductor request);
}
