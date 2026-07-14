package com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.application.dto.request.RequestRegistroCliente;
import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.application.dto.response.ResponseRegistroCliente;
import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.domain.services.ClienteService;
import org.springframework.stereotype.Component;

@Component
public class RegistroClienteUseCase {
    private final ClienteService clienteService;

    public RegistroClienteUseCase(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    public ResponseRegistroCliente RegistroCliente(RequestRegistroCliente request) {
        try {
            ResponseRegistroCliente response = clienteService.RegistroCliente(request);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseRegistroCliente response = new ResponseRegistroCliente();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al registrar el cliente: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseRegistroCliente response = new ResponseRegistroCliente();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }
}
