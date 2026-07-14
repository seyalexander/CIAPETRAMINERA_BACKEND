package com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto;

import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.infraestructure.persistence.model.UnidadMedidaModel;
import lombok.Data;

@Data
public class NotificacionUnidadMedidaDTO extends UnidadMedidaModel {
    private String tipo;
    private String mensaje;
}
