package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.request.RequestDetalleTipoClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.response.ResponseDetalleTipoClientes;

public interface ITipoClientesDetalle {
    ResponseDetalleTipoClientes DetalleTipoClientes(RequestDetalleTipoClientes request);
}