package com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.config.SecurityUtils;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.request.RequestRegistroConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.response.ResponseRegistroConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.domain.services.ConductorService;
import org.springframework.stereotype.Component;

@Component
public class RegistroConductorUseCase {

    private final ConductorService conductorService;

    public RegistroConductorUseCase(ConductorService conductorService) {
        this.conductorService = conductorService;
    }

    public ResponseRegistroConductor RegistroConductor(RequestRegistroConductor request) {
        try {

            // VALIDACIÓN DE CAMPOS
            if (request == null) {
                String mensajeError = "No se encontró datos para registrar";
                throw new IllegalArgumentException(mensajeError);
            }

            long userId = SecurityUtils.getCurrentUserId();

            ResponseRegistroConductor response = conductorService.registroConductor(request, userId);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseRegistroConductor response = new ResponseRegistroConductor();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al registrar al transportista: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseRegistroConductor response = new ResponseRegistroConductor();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }
}
