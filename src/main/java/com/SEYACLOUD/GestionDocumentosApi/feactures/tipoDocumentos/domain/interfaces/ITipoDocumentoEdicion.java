package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.dto.request.RequestEditarAllTipoDocumento;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.dto.request.RequestEditarEstadoTipoDocumento;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.dto.response.ResponseEditarAllTipoDocumento;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.dto.response.ResponseEditarEstadoTipoDocumento;

public interface ITipoDocumentoEdicion {
    ResponseEditarAllTipoDocumento EditarTipoDocumento(RequestEditarAllTipoDocumento request, long userAutenticado);
    ResponseEditarEstadoTipoDocumento EditarEstadoTipoDocumento(RequestEditarEstadoTipoDocumento request, int estado, long userAutenticado);
}
