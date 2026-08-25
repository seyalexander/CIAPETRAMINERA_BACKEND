package com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.request.RequestListaConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.response.ResponseListaConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.domain.services.ConductorService;
import org.springframework.stereotype.Component;

@Component
public class ListaConductorUseCase {

    private final ConductorService conductorService;

    public ListaConductorUseCase(ConductorService conductorService) {
        this.conductorService = conductorService;
    }

    public ResponseListaConductor ListarConductor(RequestListaConductor request) {
        try {
            // VALIDACIÓN DE CAMPOS
            if (request == null) {
                String mensajeError = "El valor del estado es obligatorio";
                throw new IllegalArgumentException(mensajeError);
            }

            if (request.getEstado() < 0 || request.getEstado() > 2 ) {
                String mensajeError = "El valor del estado no es el correcto";
                throw new IllegalArgumentException(mensajeError);
            }

            ResponseListaConductor response = conductorService.listaConductores(request);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseListaConductor response = new ResponseListaConductor();
            response.setExito(false);
            response.setMessage(e.getMessage());
            response.setConductores(java.util.List.of());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al listar los transportistas: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseListaConductor response = new ResponseListaConductor();
            response.setExito(false);
            response.setMessage(mensajeError);
            response.setConductores(java.util.List.of());
            return response;
        }
    }
}
