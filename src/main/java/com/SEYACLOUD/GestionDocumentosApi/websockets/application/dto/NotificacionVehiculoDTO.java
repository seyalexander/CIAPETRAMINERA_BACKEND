package com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto;

import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.infraestructure.persistence.model.VehiculoModel;
import lombok.Data;

@Data
public class NotificacionVehiculoDTO extends VehiculoModel {
    private String tipo;
    private String mensaje;
}
