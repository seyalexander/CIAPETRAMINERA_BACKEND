package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.config.SecurityUtils;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.request.RequestEditarAllTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.request.RequestEditarEstadoTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.response.ResponseEditarAllTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.response.ResponseEditarEstadoTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.domain.services.TipoVehiculoService;
import org.springframework.stereotype.Component;

@Component
public class EditarEstadoTipoVehiculoUseCase {

    private final TipoVehiculoService tipoVehiculoService;

    public EditarEstadoTipoVehiculoUseCase(
            TipoVehiculoService tipoVehiculoService
    ){
        this.tipoVehiculoService = tipoVehiculoService;
    }

    public ResponseEditarEstadoTipoVehiculo AnularTipoVehiculo(long idUsuario) {
        try {
            RequestEditarEstadoTipoVehiculo request = new RequestEditarEstadoTipoVehiculo();
            request.setIdTipoVehiculo(idUsuario);

            long userId = SecurityUtils.getCurrentUserId();
            ResponseEditarEstadoTipoVehiculo response = tipoVehiculoService.EditarEstadoTipoVehiculo(request, 0, userId);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseEditarEstadoTipoVehiculo response = new ResponseEditarEstadoTipoVehiculo();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al editar al usuario: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseEditarEstadoTipoVehiculo response = new ResponseEditarEstadoTipoVehiculo();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }

    public ResponseEditarEstadoTipoVehiculo ActivarTipoVehiculo(long idUsuario) {
        try {
            RequestEditarEstadoTipoVehiculo request = new RequestEditarEstadoTipoVehiculo();
            request.setIdTipoVehiculo(idUsuario);

            long userId = SecurityUtils.getCurrentUserId();
            ResponseEditarEstadoTipoVehiculo response = tipoVehiculoService.EditarEstadoTipoVehiculo(request, 1, userId);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseEditarEstadoTipoVehiculo response = new ResponseEditarEstadoTipoVehiculo();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al editar al usuario: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseEditarEstadoTipoVehiculo response = new ResponseEditarEstadoTipoVehiculo();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }
}
