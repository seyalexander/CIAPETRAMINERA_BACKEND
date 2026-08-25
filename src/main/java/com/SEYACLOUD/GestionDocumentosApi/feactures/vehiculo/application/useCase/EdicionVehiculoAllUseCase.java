package com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.config.SecurityUtils;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.request.RequestEditarAllVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.response.ResponseEditarAllVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.domain.services.VehiculosService;
import org.springframework.stereotype.Component;

@Component
public class EdicionVehiculoAllUseCase {

    private final VehiculosService VehiculosService;

    public EdicionVehiculoAllUseCase(VehiculosService VehiculosService) {
        this.VehiculosService = VehiculosService;
    }

    public ResponseEditarAllVehiculo EditarVehiculo(RequestEditarAllVehiculo request) {
        try {

            if (request == null) {
                String mensajeError = "No se encontró datos para editar";
                throw new IllegalArgumentException(mensajeError);
            }

            long userId = SecurityUtils.getCurrentUserId();

            ResponseEditarAllVehiculo response = VehiculosService.editarAllVehiculo(request);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseEditarAllVehiculo response = new ResponseEditarAllVehiculo();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al editar al vehiculo: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseEditarAllVehiculo response = new ResponseEditarAllVehiculo();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }
}
