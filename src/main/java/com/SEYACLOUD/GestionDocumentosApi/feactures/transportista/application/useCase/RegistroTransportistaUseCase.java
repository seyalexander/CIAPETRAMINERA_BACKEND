package com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.config.SecurityUtils;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.request.RequestRegistroTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.response.ResponseRegistroTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.domain.services.TransportistaService;
import org.springframework.stereotype.Component;

@Component
public class RegistroTransportistaUseCase {

    private final TransportistaService transportistaService;

    public RegistroTransportistaUseCase(
            TransportistaService transportistaService
    ){
        this.transportistaService = transportistaService;
    }

    public ResponseRegistroTransportista RegistroTransportista(RequestRegistroTransportista request) {
        try {

            // VALIDACIÓN DE CAMPOS
            if (request == null) {
                String mensajeError = "No se encontró datos para registrar";
                throw new IllegalArgumentException(mensajeError);
            }

            long userId = SecurityUtils.getCurrentUserId();

            ResponseRegistroTransportista response = transportistaService.registroTransportista(request, userId);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseRegistroTransportista response = new ResponseRegistroTransportista();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al registrar al transportista: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseRegistroTransportista response = new ResponseRegistroTransportista();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }
}
