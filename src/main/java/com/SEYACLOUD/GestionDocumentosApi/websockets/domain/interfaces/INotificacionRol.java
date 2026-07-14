package com.SEYACLOUD.GestionDocumentosApi.websockets.domain.interfaces;


import com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto.NotificacionRolDTO;

public interface INotificacionRol {
    void enviarNotificacionRol_Registro(NotificacionRolDTO notificacion);
    void enviarNotificacionRol_Edicion(NotificacionRolDTO notificacion);
    void enviarNotificacionRol_Anular(NotificacionRolDTO notificacion);
    void enviarNotificacionRol_Activar(NotificacionRolDTO notificacion);
}
