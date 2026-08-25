package com.SEYACLOUD.GestionDocumentosApi.feactures.EmpresaClientes.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.EmpresaClientes.application.dto.request.RequestListaEmpresaClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.EmpresaClientes.application.dto.response.ResponseListaEmpresaClientes;

public interface IEmpresaClientesListado {
    ResponseListaEmpresaClientes ListaEmpresaClientes(RequestListaEmpresaClientes request);
}
