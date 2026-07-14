package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.response;

import com.SEYACLOUD.GestionDocumentosApi.common.Response.ResponseGeneral;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.infraestructure.persistence.model.TipoClientesModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ResponseListaTipoClientes extends ResponseGeneral implements Serializable {

    private List<TipoClientesModel> tipoClientes;
}