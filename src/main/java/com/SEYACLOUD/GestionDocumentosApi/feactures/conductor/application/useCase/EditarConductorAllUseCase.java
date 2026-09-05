package com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.config.SecurityUtils;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.request.RequestEditarAllConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.response.ResponseEditarAllConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.domain.services.ConductorService;
import org.springframework.stereotype.Component;

@Component
public class EditarConductorAllUseCase {

    private final ConductorService conductorService;

    public EditarConductorAllUseCase(ConductorService conductorService) {
        this.conductorService = conductorService;
    }

    public ResponseEditarAllConductor EditarConductor(RequestEditarAllConductor request) {
        try {

            if (request == null) {
                String mensajeError = "No se encontró datos para editar";
                throw new IllegalArgumentException(mensajeError);
            }

            long userId = SecurityUtils.getCurrentUserId();
            ResponseEditarAllConductor response = conductorService.editarAllConductor(request, userId);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseEditarAllConductor response = new ResponseEditarAllConductor();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al editar al conductor: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseEditarAllConductor response = new ResponseEditarAllConductor();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }
}
