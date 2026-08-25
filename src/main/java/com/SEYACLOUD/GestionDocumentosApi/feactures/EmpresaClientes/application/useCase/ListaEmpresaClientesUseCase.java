package com.SEYACLOUD.GestionDocumentosApi.feactures.EmpresaClientes.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.feactures.EmpresaClientes.application.dto.request.RequestListaEmpresaClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.EmpresaClientes.application.dto.response.ResponseListaEmpresaClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.EmpresaClientes.domain.services.EmpresasClientesService;
import org.springframework.stereotype.Component;

@Component
public class ListaEmpresaClientesUseCase {

    private final EmpresasClientesService empresasClientesService;

    public ListaEmpresaClientesUseCase(EmpresasClientesService empresasClientesService) {
        this.empresasClientesService = empresasClientesService;
    }

    public ResponseListaEmpresaClientes ListaEmpresaClientes(RequestListaEmpresaClientes request) {
        try {
            ResponseListaEmpresaClientes response = empresasClientesService.ListaEmpresaClientes(request);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseListaEmpresaClientes response = new ResponseListaEmpresaClientes();
            response.setExito(false);
            response.setMessage(e.getMessage());
            response.setEmpresaClientes(java.util.List.of());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al listar las empresas clientes: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseListaEmpresaClientes response = new ResponseListaEmpresaClientes();
            response.setExito(false);
            response.setMessage(mensajeError);
            response.setEmpresaClientes(java.util.List.of());
            return response;
        }
    }
}
