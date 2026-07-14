package com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.application.dto.request.RequestRegistroCliente;
import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.application.dto.response.ResponseRegistroCliente;

public interface IClienteRegistro {
    ResponseRegistroCliente RegistroCliente(RequestRegistroCliente request);
}