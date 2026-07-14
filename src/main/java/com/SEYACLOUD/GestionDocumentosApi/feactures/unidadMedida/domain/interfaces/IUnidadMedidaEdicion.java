package com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.domain.interfaces;


import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.application.dto.request.RequestEditarAllUnidadMedida;
import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.application.dto.request.RequestEditarEstadoUnidadMedida;
import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.application.dto.response.ResponseEditarAllUnidadMedida;
import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.application.dto.response.ResponseEditarEstadoUnidadMedida;

public interface IUnidadMedidaEdicion {
    ResponseEditarAllUnidadMedida EditarAllUnidadMedida(RequestEditarAllUnidadMedida request);
    ResponseEditarEstadoUnidadMedida EditarEstadoUnidadMedida(RequestEditarEstadoUnidadMedida request, int estado);
}