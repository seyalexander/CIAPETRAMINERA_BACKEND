package com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.request.RequestEditarAllVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.request.RequestEditarEstadoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.response.ResponseEditarAllVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.response.ResponseEditarEstadoVehiculo;

public interface IVehiculoEdicion {
    ResponseEditarAllVehiculo editarAllVehiculo(RequestEditarAllVehiculo request);
    ResponseEditarEstadoVehiculo editarEstadoVehiculo(RequestEditarEstadoVehiculo request, int estado, long idUserAutenticado );
}
