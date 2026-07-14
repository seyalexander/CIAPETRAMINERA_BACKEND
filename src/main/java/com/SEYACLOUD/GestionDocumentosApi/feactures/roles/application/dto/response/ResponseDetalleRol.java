package com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.response;


import com.SEYACLOUD.GestionDocumentosApi.common.Response.ResponseGeneral;
import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.infraestructure.persistence.model.RolModel;
import lombok.Data;

@Data
public class ResponseDetalleRol extends ResponseGeneral {
    private RolModel rol;
}
