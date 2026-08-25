package com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.request.RequestDetalleVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.response.ResponseDetalleVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.domain.services.VehiculosService;
import org.springframework.stereotype.Component;

@Component
public class DetalleVehiculoUseCase {

    private final VehiculosService VehiculosService;

    public DetalleVehiculoUseCase(VehiculosService VehiculosService) {
        this.VehiculosService = VehiculosService;
    }

    public ResponseDetalleVehiculo DetalleVehiculo(long idVehiculo) {
        try {
            RequestDetalleVehiculo request = new RequestDetalleVehiculo();
            request.setIdVehiculo(idVehiculo);
            ResponseDetalleVehiculo response = VehiculosService.detalleVehiculo(request);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseDetalleVehiculo response = new ResponseDetalleVehiculo();
            response.setExito(false);
            response.setMessage(e.getMessage());
            response.setVehiculo(null);
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al obtener el detalle de los vehículo: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseDetalleVehiculo response = new ResponseDetalleVehiculo();
            response.setExito(false);
            response.setMessage(mensajeError);
            response.setVehiculo(null);
            return response;
        }
    }
}
