package com.SEYACLOUD.GestionDocumentosApi.feactures.login.infraestructure.persistence.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioModel {
    private int idUsuario;
    private String nombres;
    private String permiso;
}
