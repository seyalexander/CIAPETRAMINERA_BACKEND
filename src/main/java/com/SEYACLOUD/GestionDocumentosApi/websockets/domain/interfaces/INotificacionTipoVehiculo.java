package com.SEYACLOUD.GestionDocumentosApi.websockets.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto.NotificacionTipoVehiculoDTO;

public interface INotificacionTipoVehiculo {
    void enviarNotificacionTipoVehiculo_Registro(NotificacionTipoVehiculoDTO notificacion);
    void enviarNotificacionTipoVehiculo_Edicion(NotificacionTipoVehiculoDTO notificacion);
    void enviarNotificacionTipoVehiculo_Anular(NotificacionTipoVehiculoDTO notificacion);
    void enviarNotificacionTipoVehiculo_Activar(NotificacionTipoVehiculoDTO notificacion);
}
