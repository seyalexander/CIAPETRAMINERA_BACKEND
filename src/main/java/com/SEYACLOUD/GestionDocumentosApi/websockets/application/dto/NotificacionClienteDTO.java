package com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto;

import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.infraestructure.persistence.model.ClienteModel;
import lombok.Data;

@Data
public class NotificacionClienteDTO extends ClienteModel {
    private String tipo;
    private String mensaje;
}
