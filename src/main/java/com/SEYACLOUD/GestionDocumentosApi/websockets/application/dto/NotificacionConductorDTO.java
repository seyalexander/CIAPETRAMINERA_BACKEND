package com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto;

import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.infraestructure.persistence.model.ConductorModel;
import lombok.Data;

@Data
public class NotificacionConductorDTO extends ConductorModel {
    private String tipo;
    private String mensaje;
}
