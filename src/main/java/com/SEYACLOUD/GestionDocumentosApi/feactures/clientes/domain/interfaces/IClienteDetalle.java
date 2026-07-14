package com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.domain.interfaces;


import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.application.dto.request.RequestDetalleCliente;
import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.application.dto.response.ResponseDetalleCliente;

public interface IClienteDetalle {
    ResponseDetalleCliente DetalleCliente(RequestDetalleCliente request);
}