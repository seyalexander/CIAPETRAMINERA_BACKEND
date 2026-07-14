package com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.response;


import com.SEYACLOUD.GestionDocumentosApi.common.Response.ResponseGeneral;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.infraestructure.persistence.model.UsuariosModel;
import lombok.Data;

@Data
public class ResponseDetalleUsuario extends ResponseGeneral {
    UsuariosModel usuario;
}
