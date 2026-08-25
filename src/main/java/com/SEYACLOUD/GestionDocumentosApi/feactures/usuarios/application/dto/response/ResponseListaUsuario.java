package com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.response;

import com.SEYACLOUD.GestionDocumentosApi.common.Response.ResponseGeneral;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.infraestructure.persistence.model.UsuariosModel;
import lombok.Data;

import java.util.List;

@Data
public class ResponseListaUsuario extends ResponseGeneral {
    private List<UsuariosModel> usuarios;
}
