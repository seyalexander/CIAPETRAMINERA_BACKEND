package com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class RequestEdicionEstadoEmpleados {
    @Min(value = 1, message = "El id debe ser mayor a 0")
    private Long idEmpleado;
}
