package com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto;


import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.infraestructure.persistence.model.RolModel;
import lombok.Data;

@Data
public class NotificacionRolDTO extends RolModel {
    private String tipo;
    private String mensaje;
}
