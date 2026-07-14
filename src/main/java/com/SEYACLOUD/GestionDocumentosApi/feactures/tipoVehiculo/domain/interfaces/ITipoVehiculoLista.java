package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.request.RequestListaTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.response.ResponseListaTipoVehiculo;

public interface ITipoVehiculoLista {
    ResponseListaTipoVehiculo ListaTipoVehiculos(RequestListaTipoVehiculo request);
}
