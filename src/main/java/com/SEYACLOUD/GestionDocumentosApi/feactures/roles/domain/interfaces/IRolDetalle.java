package com.SEYACLOUD.GestionDocumentosApi.feactures.roles.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.request.RequestDetalleRol;
import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.response.ResponseDetalleRol;

public interface IRolDetalle {
    ResponseDetalleRol DetalleRol (RequestDetalleRol request);
}
