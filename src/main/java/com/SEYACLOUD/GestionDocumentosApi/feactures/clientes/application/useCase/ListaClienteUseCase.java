package com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.application.dto.request.RequestListaCliente;
import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.application.dto.response.ResponseListaCliente;
import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.domain.services.ClienteService;
import org.springframework.stereotype.Component;

@Component
public class ListaClienteUseCase {
    private final ClienteService clienteService;

    public ListaClienteUseCase(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    public ResponseListaCliente ListaCliente(RequestListaCliente request) {
        try {
            ResponseListaCliente response = clienteService.ListaCliente(request);
            if(response.isExito()){}
            return response;
        } catch (IllegalArgumentException | SecurityException e) {
            ResponseListaCliente response = new ResponseListaCliente();
            response.setExito(false);
            response.setMessage(e.getMessage());
            response.setClientes(java.util.List.of());
            return response;
        } catch (Exception e) {
            String mensajeError = "Error inesperado al listar los clientes: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseListaCliente response = new ResponseListaCliente();
            response.setExito(false);
            response.setMessage(mensajeError);
            response.setClientes(java.util.List.of());
            return response;
        }
    }
}
