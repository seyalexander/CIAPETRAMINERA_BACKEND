package com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.request.RequestListaVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.response.ResponseListaVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.domain.services.VehiculosService;
import org.springframework.stereotype.Component;

@Component
public class ListaVehiculosUseCase {
    private final VehiculosService vehiculosService;

    public ListaVehiculosUseCase(
            VehiculosService vehiculosService
    ){
        this.vehiculosService = vehiculosService;
    }

    public ResponseListaVehiculo ListarVehiculo(RequestListaVehiculo request) {
        try {
            // VALIDACIÓN DE CAMPOS
            if (request == null) {
                String mensajeError = "El valor del estado es obligatorio";
                throw new IllegalArgumentException(mensajeError);
            }

            if (request.getEstado() < 0 || request.getEstado() > 2) {
                String mensajeError = "El valor del estado no es el correcto";
                throw new IllegalArgumentException(mensajeError);
            }

            ResponseListaVehiculo response = vehiculosService.ListaVehiculos(request);
            if (response.isExito()) {
            }

            return response;

        } catch (IllegalArgumentException | SecurityException e) {
            ResponseListaVehiculo response = new ResponseListaVehiculo();
            response.setExito(false);
            response.setMessage(e.getMessage());
            response.setVehiculos(java.util.List.of());
            return response;
        } catch (Exception e) {
            String mensajeError = "Error inesperado al listar los usuarios: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseListaVehiculo response = new ResponseListaVehiculo();
            response.setExito(false);
            response.setMessage(mensajeError);
            response.setVehiculos(java.util.List.of());
            return response;
        }
    }
}
