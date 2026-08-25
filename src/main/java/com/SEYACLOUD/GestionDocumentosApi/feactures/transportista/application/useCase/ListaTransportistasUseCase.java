package com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.request.RequestListaTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.response.ResponseListaTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.domain.services.TransportistaService;
import org.springframework.stereotype.Component;

@Component
public class ListaTransportistasUseCase{

    private final TransportistaService transportistaService;

    public ListaTransportistasUseCase(
            TransportistaService transportistaService
    ){
        this.transportistaService = transportistaService;
    }

    public ResponseListaTransportista ListarTransportista(RequestListaTransportista request) {
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

            ResponseListaTransportista response = transportistaService.listaTransportistas(request);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseListaTransportista response = new ResponseListaTransportista();
            response.setExito(false);
            response.setMessage(e.getMessage());
            response.setTransportistas(java.util.List.of());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al listar los transportistas: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseListaTransportista response = new ResponseListaTransportista();
            response.setExito(false);
            response.setMessage(mensajeError);
            response.setTransportistas(java.util.List.of());
            return response;
        }
    }
}
