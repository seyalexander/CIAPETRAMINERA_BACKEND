package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.dto.request.RequestListaTipoDocumento;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.dto.response.ResponseListaTipoDocumento;

public interface ITipoDocumentoListado {
    ResponseListaTipoDocumento ListaTipoDocumento(RequestListaTipoDocumento request, long userAutenticado);
}
