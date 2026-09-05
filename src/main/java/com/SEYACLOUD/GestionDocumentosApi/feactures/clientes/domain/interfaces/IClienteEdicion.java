package com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.application.dto.request.RequestEditarAllCliente;
import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.application.dto.request.RequestEditarEstadoCliente;
import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.application.dto.response.ResponseEditarAllCliente;
import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.application.dto.response.ResponseEditarEstadoCliente;

public interface IClienteEdicion {
    ResponseEditarAllCliente EditarAllCliente(RequestEditarAllCliente request, long userAutenticado);
    ResponseEditarEstadoCliente EditarEstadoCliente(RequestEditarEstadoCliente request, int estado, long userAutenticado);
}