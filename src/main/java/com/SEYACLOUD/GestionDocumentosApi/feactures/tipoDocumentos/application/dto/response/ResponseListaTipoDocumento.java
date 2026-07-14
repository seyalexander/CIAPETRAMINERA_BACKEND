package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.dto.response;

import com.SEYACLOUD.GestionDocumentosApi.common.Response.ResponseGeneral;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.infraestructure.persistence.model.TipoDocumentoModel;
import lombok.Data;

import java.util.List;

@Data
public class ResponseListaTipoDocumento extends ResponseGeneral {
    private List<TipoDocumentoModel> tipoDocumentos;
}
