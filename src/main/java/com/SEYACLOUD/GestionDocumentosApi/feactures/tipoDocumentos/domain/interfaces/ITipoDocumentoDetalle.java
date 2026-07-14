package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.dto.request.RequestDetalleTipoDocumento;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.dto.response.ResponseDetalleTipoDocumento;

public interface ITipoDocumentoDetalle {
    ResponseDetalleTipoDocumento DetalleTipoDocumento(RequestDetalleTipoDocumento request);
}
