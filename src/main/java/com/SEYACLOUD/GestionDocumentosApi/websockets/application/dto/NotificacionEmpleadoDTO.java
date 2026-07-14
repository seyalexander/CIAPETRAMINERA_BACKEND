package com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.infraestructure.persistence.model.EmpleadosModel;
import lombok.Data;

@Data
public class NotificacionEmpleadoDTO extends EmpleadosModel {
    private String tipo;
    private String mensaje;
}
