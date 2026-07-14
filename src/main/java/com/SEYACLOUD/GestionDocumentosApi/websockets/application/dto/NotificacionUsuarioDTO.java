package com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto;

import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.infraestructure.persistence.model.UsuariosModel;
import lombok.Data;

@Data
public class NotificacionUsuarioDTO extends UsuariosModel {
    private String tipo;
    private String mensaje;
}
