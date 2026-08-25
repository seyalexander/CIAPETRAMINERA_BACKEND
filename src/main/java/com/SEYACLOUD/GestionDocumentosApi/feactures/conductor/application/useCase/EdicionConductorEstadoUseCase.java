package com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.config.SecurityUtils;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.request.RequestEditarEstadoConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.response.ResponseEditarEstadoConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.domain.services.ConductorService;
import org.springframework.stereotype.Component;

@Component
public class EdicionConductorEstadoUseCase {

    private final ConductorService conductorService;

    public EdicionConductorEstadoUseCase(ConductorService conductorService) {
        this.conductorService = conductorService;
    }

    public ResponseEditarEstadoConductor AnularConductor(long idConductor) {
        try {
            RequestEditarEstadoConductor request = new RequestEditarEstadoConductor();
            request.setIdConductor(idConductor);

            // ====================================================================
            // OBTENIENDO ID DEL USUARIO LOGUEADO DEL TOKEN
            // ====================================================================
            long userId = SecurityUtils.getCurrentUserId();

            ResponseEditarEstadoConductor response = conductorService.editarEstadoConductor(request, 0, userId);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseEditarEstadoConductor response = new ResponseEditarEstadoConductor();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al anular al Conducto: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseEditarEstadoConductor response = new ResponseEditarEstadoConductor();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }

    public ResponseEditarEstadoConductor ActivarConductor(long idConductor) {
        try {
            RequestEditarEstadoConductor request = new RequestEditarEstadoConductor();
            request.setIdConductor(idConductor);

            // ====================================================================
            // OBTENIENDO ID DEL USUARIO LOGUEADO DEL TOKEN
            // ====================================================================
            long userId = SecurityUtils.getCurrentUserId();

            ResponseEditarEstadoConductor response = conductorService.editarEstadoConductor(request, 1, userId);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseEditarEstadoConductor response = new ResponseEditarEstadoConductor();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al activar al conductor: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseEditarEstadoConductor response = new ResponseEditarEstadoConductor();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }

}
