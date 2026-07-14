package com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestRegistroContactoCliente {
    @Min(value = 1, message = "El id de cliente debe ser mayor a 0")
    private long idCliente;

    @NotBlank(message = "El nombre del contacto es obligatorio")
    @Size(max = 150, message = "El nombre del contacto no debe superar los 150 caracteres")
    private String nombreContacto;

    @Size(max = 20, message = "El teléfono no debe superar los 20 caracteres")
    private String telefono;

    @Email(message = "El email no tiene un formato válido")
    @Size(max = 150, message = "El email no debe superar los 150 caracteres")
    private String email;
}
