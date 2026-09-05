package com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.config.SecurityUtils;
import com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.application.dto.request.RequestEditarAllContactoCliente;
import com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.application.dto.response.ResponseEditarAllContactoCliente;
import com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.domain.services.ContactoClienteService;
import org.springframework.stereotype.Component;

@Component
public class EdicionContactoAllClienteUseCase {
    private final ContactoClienteService contactoClienteService;

    public EdicionContactoAllClienteUseCase(ContactoClienteService contactoClienteService) {
        this.contactoClienteService = contactoClienteService;
    }
    public ResponseEditarAllContactoCliente EdicionAllContactoCliente(RequestEditarAllContactoCliente request) {
        try {
            long userId = SecurityUtils.getCurrentUserId();
            ResponseEditarAllContactoCliente response = contactoClienteService.EditarAllContactoCliente(request, userId);

            if (response.isExito()) {
            }

            return response;

        } catch (IllegalArgumentException | SecurityException e) {

            ResponseEditarAllContactoCliente response = new ResponseEditarAllContactoCliente();
            response.setExito(false);
            response.setMessage(e.getMessage());

            return response;

        } catch (Exception e) {

            String mensajeError = "Error inesperado al actualizar el contacto del cliente: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);

            ResponseEditarAllContactoCliente response = new ResponseEditarAllContactoCliente();
            response.setExito(false);
            response.setMessage(mensajeError);

            return response;
        }
    }
}
