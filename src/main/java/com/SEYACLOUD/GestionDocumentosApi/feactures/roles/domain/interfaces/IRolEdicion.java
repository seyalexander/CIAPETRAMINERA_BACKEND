package com.SEYACLOUD.GestionDocumentosApi.feactures.roles.domain.interfaces;


import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.request.RequestEditarAllRol;
import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.request.RequestEditarEstadoRol;
import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.response.ResponseEditarAllRol;
import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.response.ResponseEditarEstadoRol;

public interface IRolEdicion {
    ResponseEditarAllRol EditarRol(RequestEditarAllRol request);
    ResponseEditarEstadoRol EditarEstadoRol(RequestEditarEstadoRol request, int estado);
}
