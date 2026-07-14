package com.SEYACLOUD.GestionDocumentosApi.feactures.roles.domain.interfaces;


import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.request.RequestRegistroRol;
import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.response.ResponseRegistroRol;

public interface IRolRegistro {
    ResponseRegistroRol registrarRol(RequestRegistroRol request);
}
