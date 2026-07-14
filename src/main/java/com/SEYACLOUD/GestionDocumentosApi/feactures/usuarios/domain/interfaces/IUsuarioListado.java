package com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.domain.interfaces;


import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.request.RequestListaUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.response.ResponseListaUsuario;

public interface IUsuarioListado {
    ResponseListaUsuario ListaUsuarios(RequestListaUsuario request);
}
