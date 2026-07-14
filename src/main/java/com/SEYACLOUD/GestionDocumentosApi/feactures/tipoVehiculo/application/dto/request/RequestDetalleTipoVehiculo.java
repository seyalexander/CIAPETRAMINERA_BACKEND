package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class RequestDetalleTipoVehiculo {
    @Min(value = 1, message = "El id debe ser mayor a 0")
    private long idTipoVehiculo;
}
