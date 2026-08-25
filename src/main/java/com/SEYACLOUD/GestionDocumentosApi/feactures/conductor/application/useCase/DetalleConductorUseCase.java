package com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.request.RequestDetalleConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.response.ResponseDetalleConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.domain.services.ConductorService;
import org.springframework.stereotype.Component;

@Component
public class DetalleConductorUseCase {

    private final ConductorService conductorService;

    public DetalleConductorUseCase(ConductorService conductorService) {
        this.conductorService = conductorService;
    }

    public ResponseDetalleConductor DetalleConductor(long idConductor) {
        try {
            RequestDetalleConductor request = new RequestDetalleConductor();
            request.setIdConductor(idConductor);
            ResponseDetalleConductor response = conductorService.detalleConductor(request);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseDetalleConductor response = new ResponseDetalleConductor();
            response.setExito(false);
            response.setMessage(e.getMessage());
            response.setConductor(null);
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al obtener el detalle de los conductor: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseDetalleConductor response = new ResponseDetalleConductor();
            response.setExito(false);
            response.setMessage(mensajeError);
            response.setConductor(null);
            return response;
        }
    }
}
