package com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.config.SecurityUtils;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.request.RequestRegistroVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.response.ResponseRegistroVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.domain.services.VehiculosService;
import org.springframework.stereotype.Component;

@Component
public class RegistroVehiculoUseCase {

    private final VehiculosService VehiculosService;

    public RegistroVehiculoUseCase(VehiculosService VehiculosService) {
        this.VehiculosService = VehiculosService;
    }

    public ResponseRegistroVehiculo RegistroVehiculo(RequestRegistroVehiculo request) {
        try {

            // VALIDACIÓN DE CAMPOS
            if (request == null) {
                String mensajeError = "No se encontró datos para registrar";
                throw new IllegalArgumentException(mensajeError);
            }

            long userId = SecurityUtils.getCurrentUserId();

            ResponseRegistroVehiculo response = VehiculosService.registroVehiculo(request, userId);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseRegistroVehiculo response = new ResponseRegistroVehiculo();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al registrar al vehiculo: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseRegistroVehiculo response = new ResponseRegistroVehiculo();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }
}
