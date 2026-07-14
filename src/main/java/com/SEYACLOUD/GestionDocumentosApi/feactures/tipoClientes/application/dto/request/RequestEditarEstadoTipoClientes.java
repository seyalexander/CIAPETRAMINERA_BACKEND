package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class RequestEditarEstadoTipoClientes {
    @Min(value = 1, message = "El id debe ser mayor a 0")
    private Long idTipoClientes;
}
