package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.config.SecurityUtils;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.request.RequestEditarAllTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.request.RequestRegistroTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.response.ResponseEditarAllTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.response.ResponseRegistroTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.domain.services.TipoVehiculoService;
import org.springframework.stereotype.Component;

@Component
public class RegistroTipoVehiculoUseCase {
    private final TipoVehiculoService tipoVehiculoService;

    public RegistroTipoVehiculoUseCase(
            TipoVehiculoService tipoVehiculoService
    ){
        this.tipoVehiculoService = tipoVehiculoService;
    }

    public ResponseRegistroTipoVehiculo RegistroTipoVehiculo(RequestRegistroTipoVehiculo request) {
        try {
            long userId = SecurityUtils.getCurrentUserId();
            ResponseRegistroTipoVehiculo response = tipoVehiculoService.RegistroTipoVehiculo(request, userId);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseRegistroTipoVehiculo response = new ResponseRegistroTipoVehiculo();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al editar al usuario: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseRegistroTipoVehiculo response = new ResponseRegistroTipoVehiculo();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }
}
