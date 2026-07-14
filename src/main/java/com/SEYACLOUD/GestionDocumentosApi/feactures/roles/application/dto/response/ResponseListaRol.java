package com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.response;

import com.SEYACLOUD.GestionDocumentosApi.common.Response.ResponseGeneral;
import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.infraestructure.persistence.model.RolModel;
import lombok.Data;

import java.util.List;

@Data
public class ResponseListaRol extends ResponseGeneral {
    List<RolModel> roles;
}
