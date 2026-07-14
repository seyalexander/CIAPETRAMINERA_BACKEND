package com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.domain.interfaces;


import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.request.RequestRegistroUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.response.ResponseRegistroUsuario;

public interface IUsuarioRegistro {
    ResponseRegistroUsuario registrarUsuario(RequestRegistroUsuario request, long idUserAutenticado);
}
