package com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.application.dto.request.RequestRegistroUnidadMedida;
import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.application.dto.response.ResponseRegistroUnidadMedida;

public interface IUnidadMedidaRegistro {
    ResponseRegistroUnidadMedida RegistroUnidadMedida(RequestRegistroUnidadMedida request);
}