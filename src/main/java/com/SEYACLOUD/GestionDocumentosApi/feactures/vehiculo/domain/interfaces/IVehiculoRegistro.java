package com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.request.RequestRegistroVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.response.ResponseRegistroVehiculo;

public interface VehiculoRegistro {
    ResponseRegistroVehiculo registroVehiculo(RequestRegistroVehiculo request);
}
