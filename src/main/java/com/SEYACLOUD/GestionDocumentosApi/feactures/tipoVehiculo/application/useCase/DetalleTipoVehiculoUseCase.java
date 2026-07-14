package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.request.RequestDetalleRol;
import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.response.ResponseDetalleRol;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.request.RequestDetalleTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.response.ResponseDetalleTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.domain.services.TipoVehiculoService;
import org.springframework.stereotype.Component;

@Component
public class DetalleTipoVehiculoUseCase {

    private final TipoVehiculoService tipoVehiculoService;

    public DetalleTipoVehiculoUseCase(TipoVehiculoService tipoVehiculoService) {
        this.tipoVehiculoService= tipoVehiculoService;
    }

    public ResponseDetalleTipoVehiculo DetalleTipoVehiculo(long idTipoVehiculo){
        try {
            RequestDetalleTipoVehiculo request = new RequestDetalleTipoVehiculo();
            request.setIdTipoVehiculo(idTipoVehiculo);
            ResponseDetalleTipoVehiculo response = tipoVehiculoService.DetalleTipoVehiculo(request);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseDetalleTipoVehiculo response = new ResponseDetalleTipoVehiculo();
            response.setExito(false);
            response.setMessage(e.getMessage());
            response.setTipoVehiculo(null);
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado ver el detalle del tipo vehículo: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseDetalleTipoVehiculo response = new ResponseDetalleTipoVehiculo();
            response.setExito(false);
            response.setMessage(mensajeError);
            response.setTipoVehiculo(null);
            return response;
        }
    }
}
