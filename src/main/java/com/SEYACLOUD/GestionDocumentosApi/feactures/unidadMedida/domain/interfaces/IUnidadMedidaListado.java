package com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.domain.interfaces;


import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.application.dto.request.RequestListaUnidadMedida;
import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.application.dto.response.ResponseListaUnidadMedida;

public interface IUnidadMedidaListado {
    ResponseListaUnidadMedida listaUnidadMedida(RequestListaUnidadMedida request);
}
