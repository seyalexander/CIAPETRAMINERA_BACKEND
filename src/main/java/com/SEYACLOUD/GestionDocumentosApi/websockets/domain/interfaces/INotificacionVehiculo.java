package com.SEYACLOUD.GestionDocumentosApi.websockets.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto.NotificacionVehiculoDTO;

public interface INotificacionVehiculo {
    void enviarNotificacionVehiculo_Registro(NotificacionVehiculoDTO notificacion);
    void enviarNotificacionVehiculo_Edicion(NotificacionVehiculoDTO notificacion);
    void enviarNotificacionVehiculo_Anular(NotificacionVehiculoDTO notificacion);
    void enviarNotificacionVehiculo_Activar(NotificacionVehiculoDTO notificacion);
}
