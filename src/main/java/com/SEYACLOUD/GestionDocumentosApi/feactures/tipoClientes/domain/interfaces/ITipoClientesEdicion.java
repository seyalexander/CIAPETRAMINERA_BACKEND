package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.request.RequestEditarAllTipoClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.request.RequestEditarEstadoTipoClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.response.ResponseEditarAllTipoClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.response.ResponseEditarEstadoTipoClientes;

public interface ITipoClientesEdicion {
    ResponseEditarAllTipoClientes EditarAllTipoClientes(RequestEditarAllTipoClientes request, long idUserAutenticado);
    ResponseEditarEstadoTipoClientes EditarEstadoTipoClientes(RequestEditarEstadoTipoClientes request, int estado);
}