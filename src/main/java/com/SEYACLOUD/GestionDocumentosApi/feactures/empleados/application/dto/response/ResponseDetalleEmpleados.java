package com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.response;

import com.SEYACLOUD.GestionDocumentosApi.common.Response.ResponseGeneral;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.infraestructure.persistence.model.EmpleadosModel;
import lombok.Data;

@Data
public class ResponseDetalleEmpleados extends ResponseGeneral {
    EmpleadosModel empleado;
}
