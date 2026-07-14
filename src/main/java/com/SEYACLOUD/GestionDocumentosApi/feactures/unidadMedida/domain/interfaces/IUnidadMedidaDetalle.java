package com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.application.dto.request.RequestDetalleUnidadMedida;
import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.application.dto.response.ResponseDetalleUnidadMedida;

public interface IUnidadMedidaDetalle {
    ResponseDetalleUnidadMedida DetalleUnidadMedida(RequestDetalleUnidadMedida request);
}