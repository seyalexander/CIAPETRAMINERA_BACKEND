package com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.application.dto.request.RequestListaCliente;
import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.application.dto.response.ResponseListaCliente;

public interface IClienteListado {
    ResponseListaCliente ListaCliente(RequestListaCliente request);
}