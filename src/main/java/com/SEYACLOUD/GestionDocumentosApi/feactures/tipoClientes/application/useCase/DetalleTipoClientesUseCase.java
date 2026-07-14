package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.request.RequestDetalleTipoClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.response.ResponseDetalleTipoClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.domain.services.TipoClientesService;
import org.springframework.stereotype.Component;

@Component
public class DetalleTipoClientesUseCase {
    private final TipoClientesService tipoClientesService;

    public DetalleTipoClientesUseCase(TipoClientesService tipoClientesService) {
        this.tipoClientesService = tipoClientesService;
    }

    public ResponseDetalleTipoClientes DetalleTipoClientes(long idTipoCliente) {
        try {

            RequestDetalleTipoClientes request = new RequestDetalleTipoClientes();
            request.setIdTipoClientes(idTipoCliente);
            ResponseDetalleTipoClientes response = tipoClientesService.DetalleTipoClientes(request);
            if(response.isExito()){}

            return response;
        }catch (IllegalArgumentException | SecurityException e){
            ResponseDetalleTipoClientes response = new ResponseDetalleTipoClientes();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }

        catch (Exception e) {
            String mensajeError = "Error inesperado al ver el detalle de tipo cliente: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseDetalleTipoClientes response = new ResponseDetalleTipoClientes();
            response.setExito(false);
            response.setMessage("Error inesperado: " + e.getMessage());
            return response;
        }
    }
}