package com.SEYACLOUD.GestionDocumentosApi.feactures.roles.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.request.RequestListaRol;
import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.response.ResponseListaRol;

public interface IRolListado {
    ResponseListaRol ListaRol(RequestListaRol request);
}
