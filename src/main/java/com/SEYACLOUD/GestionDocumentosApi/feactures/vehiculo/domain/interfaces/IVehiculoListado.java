package com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.request.RequestListaVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.response.ResponseListaVehiculo;

public interface IVehiculoListado {
    ResponseListaVehiculo ListaVehiculos(RequestListaVehiculo request);
}
