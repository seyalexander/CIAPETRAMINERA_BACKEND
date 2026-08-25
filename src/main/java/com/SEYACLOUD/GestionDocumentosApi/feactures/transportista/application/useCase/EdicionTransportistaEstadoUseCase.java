package com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.config.SecurityUtils;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.request.RequestEditarEstadoTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.response.ResponseEditarEstadoTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.domain.services.TransportistaService;
import org.springframework.stereotype.Component;

@Component
public class EdicionTransportistaEstadoUseCase {
    private final TransportistaService transportistaService;

    public EdicionTransportistaEstadoUseCase(TransportistaService transportistaService) {
        this.transportistaService = transportistaService;
    }

    public ResponseEditarEstadoTransportista AnularTransportista(long idTransportista) {
        try {
            RequestEditarEstadoTransportista request = new RequestEditarEstadoTransportista();
            request.setIdTransportista(idTransportista);

            // ====================================================================
            // OBTENIENDO ID DEL USUARIO LOGUEADO DEL TOKEN
            // ====================================================================
            long userId = SecurityUtils.getCurrentUserId();

            ResponseEditarEstadoTransportista response = transportistaService.editarEstadoTransportisa(request, 0, userId);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseEditarEstadoTransportista response = new ResponseEditarEstadoTransportista();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al anular al transportista: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseEditarEstadoTransportista response = new ResponseEditarEstadoTransportista();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }

    public ResponseEditarEstadoTransportista ActivarTransportista(long idTransportista) {
        try {
            RequestEditarEstadoTransportista request = new RequestEditarEstadoTransportista();
            request.setIdTransportista(idTransportista);

            // ====================================================================
            // OBTENIENDO ID DEL USUARIO LOGUEADO DEL TOKEN
            // ====================================================================
            long userId = SecurityUtils.getCurrentUserId();

            ResponseEditarEstadoTransportista response = transportistaService.editarEstadoTransportisa(request, 1, userId);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseEditarEstadoTransportista response = new ResponseEditarEstadoTransportista();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al activar al transportista: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseEditarEstadoTransportista response = new ResponseEditarEstadoTransportista();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }
}
