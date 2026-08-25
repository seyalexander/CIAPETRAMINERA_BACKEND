package com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class RequestDetalleTransportista {
    @Min(value = 1, message = "El id debe ser mayor a 0")
    private long idTransportista;
}
