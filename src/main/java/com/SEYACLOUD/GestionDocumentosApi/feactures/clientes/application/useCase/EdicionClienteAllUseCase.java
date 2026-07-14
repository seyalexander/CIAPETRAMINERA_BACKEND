package com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.application.dto.request.RequestEditarAllCliente;
import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.application.dto.response.ResponseEditarAllCliente;
import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.domain.services.ClienteService;
import org.springframework.stereotype.Component;

@Component
public class EdicionClienteAllUseCase {
    private final ClienteService clienteService;

    public EdicionClienteAllUseCase(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    public ResponseEditarAllCliente EdicionAllCliente(RequestEditarAllCliente request) {
        try {
            ResponseEditarAllCliente response = clienteService.EditarAllCliente(request);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseEditarAllCliente response = new ResponseEditarAllCliente();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al actualizar el estado del cliente: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseEditarAllCliente response = new ResponseEditarAllCliente();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }
}
