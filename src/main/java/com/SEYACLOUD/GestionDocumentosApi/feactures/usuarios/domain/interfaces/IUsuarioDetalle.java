package com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.domain.interfaces;


import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.request.RequestDetalleUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.response.ResponseDetalleUsuario;

public interface IUsuarioDetalle {
    ResponseDetalleUsuario DetalleUsuario(RequestDetalleUsuario request);
}
