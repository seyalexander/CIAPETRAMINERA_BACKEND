package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.request.RequestEditarAllTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.request.RequestEditarEstadoTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.response.ResponseEditarAllTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.response.ResponseEditarEstadoTipoVehiculo;

public interface ITipoVehiculoEdicion {
    ResponseEditarAllTipoVehiculo EditarAllTipoVehiculo(RequestEditarAllTipoVehiculo request);
    ResponseEditarEstadoTipoVehiculo EditarEstadoTipoVehiculo(RequestEditarEstadoTipoVehiculo request, int estado, long idUserAutenticado);
}
