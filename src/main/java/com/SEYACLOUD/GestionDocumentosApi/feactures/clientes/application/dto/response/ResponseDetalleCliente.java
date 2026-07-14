package com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.application.dto.response;

import com.SEYACLOUD.GestionDocumentosApi.common.Response.ResponseGeneral;
import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.infraestructure.persistence.model.ClienteModel;
import lombok.Data;

@Data
public class ResponseDetalleCliente extends ResponseGeneral {
    private ClienteModel cliente;
}