package com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.domain.interfaces;


import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.request.RequestEditarAllUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.request.RequestEditarEstadoUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.response.ResponseEditarAllUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.response.ResponseEditarEstadoUsuario;

public interface IUsuarioEdicion {
    ResponseEditarAllUsuario EditarUsuario(RequestEditarAllUsuario request, long idUserAutenticado);
    ResponseEditarEstadoUsuario EditarEstadoUsuario(RequestEditarEstadoUsuario request, int estado, long idUserAutenticado);
}
