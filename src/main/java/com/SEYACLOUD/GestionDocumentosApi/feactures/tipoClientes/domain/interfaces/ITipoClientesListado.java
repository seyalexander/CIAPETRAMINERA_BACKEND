package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.domain.interfaces;


import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.request.RequestListaTipoClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.response.ResponseListaTipoClientes;

public interface ITipoClientesListado {
    ResponseListaTipoClientes ListaTipoClientes(RequestListaTipoClientes request);
}