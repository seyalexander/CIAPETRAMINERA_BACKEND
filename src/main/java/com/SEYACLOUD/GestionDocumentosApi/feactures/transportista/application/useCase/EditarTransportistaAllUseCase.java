package com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.config.SecurityUtils;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.request.RequestEditarAllTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.response.ResponseEditarAllTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.domain.services.TransportistaService;
import org.springframework.stereotype.Component;

@Component
public class EditarTransportistaAllUseCase {

    private final TransportistaService transportistaService;

    public EditarTransportistaAllUseCase(
            TransportistaService transportistaService
    ){
        this.transportistaService = transportistaService;
    }

    public ResponseEditarAllTransportista EditarTransportista(RequestEditarAllTransportista request) {
        try {

            if (request == null) {
                String mensajeError = "No se encontró datos para editar";
                throw new IllegalArgumentException(mensajeError);
            }

            long userId = SecurityUtils.getCurrentUserId();
            ResponseEditarAllTransportista response = transportistaService.editarAllTransportista(request);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseEditarAllTransportista response = new ResponseEditarAllTransportista();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al editar al transportista: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseEditarAllTransportista response = new ResponseEditarAllTransportista();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }

}
