package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.response.ResponseListaRol;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.request.RequestListaTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.response.ResponseListaTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.domain.services.TipoVehiculoService;
import org.springframework.stereotype.Component;

@Component
public class ListaTipoVehiculosUseCase {

    private final TipoVehiculoService tipoVehiculoService;

    public ListaTipoVehiculosUseCase(TipoVehiculoService tipoVehiculoService) {
        this.tipoVehiculoService = tipoVehiculoService;
    }

    public ResponseListaTipoVehiculo ListaTipoVehiculo(RequestListaTipoVehiculo request) {
        try {
            ResponseListaTipoVehiculo response = tipoVehiculoService.ListaTipoVehiculos(request);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseListaTipoVehiculo response = new ResponseListaTipoVehiculo();
            response.setExito(false);
            response.setMessage(e.getMessage());
            response.setTipoVehiculos(java.util.List.of());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al listar los tipos de vehículos: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseListaTipoVehiculo response = new ResponseListaTipoVehiculo();
            response.setExito(false);
            response.setMessage(mensajeError);
            response.setTipoVehiculos(java.util.List.of());
            return response;
        }
    }
}
