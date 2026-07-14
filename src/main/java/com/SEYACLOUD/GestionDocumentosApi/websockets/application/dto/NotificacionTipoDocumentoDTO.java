package com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto;

import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.infraestructure.persistence.model.TipoDocumentoModel;
import lombok.Data;

@Data
public class NotificacionTipoDocumentoDTO extends TipoDocumentoModel {
    private String tipo;
    private String mensaje;
}
