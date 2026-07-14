package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.request.RequestRegistroTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.response.ResponseRegistroTipoVehiculo;

public interface ITipoVehiculoRegistro {
    ResponseRegistroTipoVehiculo RegistroTipoVehiculo(RequestRegistroTipoVehiculo request, long idUserAutenticado);
}
