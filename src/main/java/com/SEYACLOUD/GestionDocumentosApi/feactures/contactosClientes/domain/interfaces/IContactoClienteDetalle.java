package com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.application.dto.request.RequestDetalleContactoCliente;
import com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.application.dto.response.ResponseDetalleContactoCliente;

public interface IContactoClienteDetalle {
    ResponseDetalleContactoCliente DetalleContactoCliente(RequestDetalleContactoCliente request);
}