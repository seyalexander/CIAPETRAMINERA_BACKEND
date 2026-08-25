package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestRegistroTipoClientes {
    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 100, message = "La descripción no debe superar los 100 caracteres")
    private String descripcion;
}