package com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.application.dto.request.RequestListaContactoCliente;
import com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.application.dto.response.ResponseListaContactoCliente;

public interface IContactoClienteListado {
    ResponseListaContactoCliente ListaContactoCliente(RequestListaContactoCliente request);
}