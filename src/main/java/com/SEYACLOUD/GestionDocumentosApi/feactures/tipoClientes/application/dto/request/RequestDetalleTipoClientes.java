package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestDetalleTipoClientes {

    @NotNull(message = "El id de Tipo Cliente es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor a 0")
    private Long idTipoClientes;

}
