package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.dto.response;

import com.SEYACLOUD.GestionDocumentosApi.common.Response.ResponseGeneral;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.infraestructure.persistence.model.TipoDocumentoModel;
import lombok.Data;

@Data
public class ResponseDetalleTipoDocumento extends ResponseGeneral {
    private TipoDocumentoModel tipoDocumento;
}
