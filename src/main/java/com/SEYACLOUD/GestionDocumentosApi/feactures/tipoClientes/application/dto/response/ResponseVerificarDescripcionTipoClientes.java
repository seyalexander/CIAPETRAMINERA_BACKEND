package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.response;

import com.SEYACLOUD.GestionDocumentosApi.common.Response.ResponseGeneral;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.infraestructure.persistence.model.TipoClientesModel;
import lombok.Data;

@Data
public class ResponseVerificarDescripcionTipoClientes extends ResponseGeneral {
    private TipoClientesModel tipoClientes;
}
