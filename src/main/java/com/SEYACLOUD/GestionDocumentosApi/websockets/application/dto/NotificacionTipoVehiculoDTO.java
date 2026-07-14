package com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto;

import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.infraestructure.persistence.model.TipoVehiculoModel;
import lombok.Data;

@Data
public class NotificacionTipoVehiculoDTO extends TipoVehiculoModel {
    private String tipo;
    private String mensaje;
}
