package com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto;

import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.infraestructure.persistence.model.TipoClientesModel;
import lombok.Data;

@Data
public class NotificacionTipoClienteDTO extends TipoClientesModel {
    private String tipo;
    private String mensaje;
}
