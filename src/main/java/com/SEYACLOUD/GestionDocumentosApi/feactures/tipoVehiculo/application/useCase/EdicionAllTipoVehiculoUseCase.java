package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.config.SecurityUtils;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.request.RequestEditarAllTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.response.ResponseEditarAllTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.domain.services.TipoVehiculoService;
import org.springframework.stereotype.Component;

@Component
public class EdicionAllTipoVehiculoUseCase {
    private final TipoVehiculoService tipoVehiculoService;

    public EdicionAllTipoVehiculoUseCase(
            TipoVehiculoService tipoVehiculoService
    ){
        this.tipoVehiculoService = tipoVehiculoService;
    }

    public ResponseEditarAllTipoVehiculo EditarTipoVehiculo(RequestEditarAllTipoVehiculo request) {
        try {
            long userId = SecurityUtils.getCurrentUserId();
            ResponseEditarAllTipoVehiculo response = tipoVehiculoService.EditarAllTipoVehiculo(request);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseEditarAllTipoVehiculo response = new ResponseEditarAllTipoVehiculo();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al editar al usuario: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseEditarAllTipoVehiculo response = new ResponseEditarAllTipoVehiculo();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }
}
