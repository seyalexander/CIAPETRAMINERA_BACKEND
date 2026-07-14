package com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto;

import com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.infraestructure.persistence.model.ContactoClienteModel;
import lombok.Data;

@Data
public class NotificacionContactoClienteDTO extends ContactoClienteModel {
    private String tipo;
    private String mensaje;
}
