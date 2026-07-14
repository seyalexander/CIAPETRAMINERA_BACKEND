package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.dto.request.RequestRegistroTipoDocumento;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.dto.response.ResponseRegistroTipoDocumento;

public interface ITipoDocumentoRegistro {
    ResponseRegistroTipoDocumento RegistroTipoDocumento(RequestRegistroTipoDocumento request, long userAutenticado );
}
