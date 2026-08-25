package com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class RequestDetalleConductor {
    @Min(value = 1, message = "El id debe ser mayor a 0")
    private long idConductor;
}
