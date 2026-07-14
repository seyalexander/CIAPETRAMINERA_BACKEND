package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.request.RequestDetalleTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.response.ResponseDetalleTipoVehiculo;

public interface ITipoVehiculoDetalle {
    ResponseDetalleTipoVehiculo DetalleTipoVehiculo(RequestDetalleTipoVehiculo request);
}
