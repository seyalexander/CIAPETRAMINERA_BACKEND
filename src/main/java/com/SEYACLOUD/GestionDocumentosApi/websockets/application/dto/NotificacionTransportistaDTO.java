package com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto;

import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.infraestructure.persistence.model.TransportistaModel;
import lombok.Data;

@Data
public class NotificacionTransportistaDTO extends TransportistaModel {
    private String tipo;
    private String mensaje;
}
