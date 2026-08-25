package com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.application.dto.request.RequestDetalleUnidadMedida;
import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.application.dto.request.RequestEditarEstadoUnidadMedida;
import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.application.dto.response.ResponseDetalleUnidadMedida;
import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.application.dto.response.ResponseEditarEstadoUnidadMedida;
import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.domain.services.UnidadMedidaService;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EdicionUnidadMedidaEstadoUseCase {
    private final UnidadMedidaService unidadMedidaService;

    public EdicionUnidadMedidaEstadoUseCase(UnidadMedidaService unidadMedidaService) {
        this.unidadMedidaService = unidadMedidaService;
    }

    public ResponseEditarEstadoUnidadMedida AnularUnidadMedida(Long idUnidadMedida) {
        try {

            RequestEditarEstadoUnidadMedida request = new RequestEditarEstadoUnidadMedida();
            request.setIdUnidadMedida(idUnidadMedida);
            ResponseEditarEstadoUnidadMedida response = unidadMedidaService.EditarEstadoUnidadMedida(request,0);;
            if(response.isExito()){}
            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseEditarEstadoUnidadMedida response = new ResponseEditarEstadoUnidadMedida();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al anular la unidad de medida: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseEditarEstadoUnidadMedida response = new ResponseEditarEstadoUnidadMedida();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }

    public ResponseEditarEstadoUnidadMedida ActivarUnidadMedida(Long idUnidadMedida) {
        try {

            RequestEditarEstadoUnidadMedida request = new RequestEditarEstadoUnidadMedida();
            request.setIdUnidadMedida(idUnidadMedida);

            ResponseEditarEstadoUnidadMedida response = unidadMedidaService.EditarEstadoUnidadMedida(request,1);;
            if(response.isExito()){}
            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseEditarEstadoUnidadMedida response = new ResponseEditarEstadoUnidadMedida();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al activar la unidad de medida: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseEditarEstadoUnidadMedida response = new ResponseEditarEstadoUnidadMedida();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }
}