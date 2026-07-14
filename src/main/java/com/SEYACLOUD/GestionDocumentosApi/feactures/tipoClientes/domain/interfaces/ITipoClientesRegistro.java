package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.domain.interfaces;


import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.request.RequestRegistroTipoClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.response.ResponseRegistroTipoClientes;

public interface ITipoClientesRegistro {
    ResponseRegistroTipoClientes RegistroTipoClientes(RequestRegistroTipoClientes request);
}