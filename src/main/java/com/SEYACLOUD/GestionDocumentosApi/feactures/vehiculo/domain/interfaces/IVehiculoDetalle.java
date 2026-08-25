package com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.request.RequestDetalleVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.response.ResponseDetalleVehiculo;

public interface IVehiculoDetalle {
    ResponseDetalleVehiculo detalleVehiculo(RequestDetalleVehiculo request);
}
