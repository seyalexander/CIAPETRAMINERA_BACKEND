package com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.response;

import com.SEYACLOUD.GestionDocumentosApi.common.Response.ResponseGeneral;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.infraestructure.persistence.model.EmpleadosModel;
import lombok.Data;

import java.util.List;

@Data
public class ResponseListaEmpleados extends ResponseGeneral {
    private List<EmpleadosModel> empleados;
}
