package com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.application.dto.response;

import com.SEYACLOUD.GestionDocumentosApi.common.Response.ResponseGeneral;
import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.infraestructure.persistence.model.ClienteModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ResponseListaCliente extends ResponseGeneral implements Serializable {

    private List<ClienteModel> clientes;
}