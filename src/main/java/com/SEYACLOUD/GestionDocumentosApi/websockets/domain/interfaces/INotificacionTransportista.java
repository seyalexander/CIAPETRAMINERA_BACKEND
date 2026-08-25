package com.SEYACLOUD.GestionDocumentosApi.websockets.domain.interfaces;


import com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto.NotificacionTransportistaDTO;

public interface INotificacionTransportista {
    void enviarNotificacionTransportista_Registro(NotificacionTransportistaDTO notificacion);
    void enviarNotificacionTransportista_Edicion(NotificacionTransportistaDTO notificacion);
    void enviarNotificacionTransportista_Anular(NotificacionTransportistaDTO notificacion);
    void enviarNotificacionTransportista_Activar(NotificacionTransportistaDTO notificacion);
}
