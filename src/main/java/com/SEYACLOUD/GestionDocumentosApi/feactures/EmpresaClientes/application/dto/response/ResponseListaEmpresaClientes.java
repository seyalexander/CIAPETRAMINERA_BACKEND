package com.SEYACLOUD.GestionDocumentosApi.feactures.EmpresaClientes.application.dto.response;

import com.SEYACLOUD.GestionDocumentosApi.common.Response.ResponseGeneral;
import com.SEYACLOUD.GestionDocumentosApi.feactures.EmpresaClientes.infraestructure.persistence.model.EmpresaClientesModel;
import lombok.Data;

import java.util.List;

@Data
public class ResponseListaEmpresaClientes extends ResponseGeneral {
    private List<EmpresaClientesModel> empresaClientes;
}
