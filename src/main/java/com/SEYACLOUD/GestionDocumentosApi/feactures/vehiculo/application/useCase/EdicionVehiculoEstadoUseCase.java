package com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.config.SecurityUtils;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.request.RequestEditarEstadoConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.response.ResponseEditarEstadoConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.request.RequestEditarEstadoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.response.ResponseEditarEstadoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.domain.services.VehiculosService;
import org.springframework.stereotype.Component;

@Component
public class EdicionVehiculoEstadoUseCase {

    private final VehiculosService VehiculosService;

    public EdicionVehiculoEstadoUseCase(VehiculosService VehiculosService) {
        this.VehiculosService = VehiculosService;
    }

    public ResponseEditarEstadoVehiculo AnularVehiculo(long idVehiculo) {
        try {
            RequestEditarEstadoVehiculo request = new RequestEditarEstadoVehiculo();
            request.setIdVehiculo(idVehiculo);

            // ====================================================================
            // OBTENIENDO ID DEL USUARIO LOGUEADO DEL TOKEN
            // ====================================================================
            long userId = SecurityUtils.getCurrentUserId();

            ResponseEditarEstadoVehiculo response = VehiculosService.editarEstadoVehiculo(request, 0, userId);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseEditarEstadoVehiculo response = new ResponseEditarEstadoVehiculo();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al anular al Conducto: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseEditarEstadoVehiculo response = new ResponseEditarEstadoVehiculo();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }

    public ResponseEditarEstadoVehiculo ActivarVehiculo(long idVehiculo) {
        try {
            RequestEditarEstadoVehiculo request = new RequestEditarEstadoVehiculo();
            request.setIdVehiculo(idVehiculo);

            // ====================================================================
            // OBTENIENDO ID DEL USUARIO LOGUEADO DEL TOKEN
            // ====================================================================
            long userId = SecurityUtils.getCurrentUserId();

            ResponseEditarEstadoVehiculo response = VehiculosService.editarEstadoVehiculo(request, 1, userId);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseEditarEstadoVehiculo response = new ResponseEditarEstadoVehiculo();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al activar al conductor: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseEditarEstadoVehiculo response = new ResponseEditarEstadoVehiculo();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }
}
