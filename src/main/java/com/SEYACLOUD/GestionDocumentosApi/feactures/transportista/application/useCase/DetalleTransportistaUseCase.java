package com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.request.RequestDetalleTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.response.ResponseDetalleTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.domain.services.TransportistaService;
import org.springframework.stereotype.Component;

@Component
public class DetalleTransportistaUseCase {

    private final TransportistaService transportistaService;

    public DetalleTransportistaUseCase(TransportistaService transportistaService) {
        this.transportistaService = transportistaService;
    }

    public ResponseDetalleTransportista DetalleTransportista(long idTransportista) {
        try {
            RequestDetalleTransportista request = new RequestDetalleTransportista();
            request.setIdTransportista(idTransportista);
            ResponseDetalleTransportista response = transportistaService.detalleTransportista(request);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseDetalleTransportista response = new ResponseDetalleTransportista();
            response.setExito(false);
            response.setMessage(e.getMessage());
            response.setTransportista(null);
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al obtener el detalle de los transportistas: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseDetalleTransportista response = new ResponseDetalleTransportista();
            response.setExito(false);
            response.setMessage(mensajeError);
            response.setTransportista(null);
            return response;
        }
    }
}
