package com.SEYACLOUD.GestionDocumentosApi.websockets.domain.interfaces;


import com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto.NotificacionUsuarioDTO;

public interface INotificacionUsuario {
    void enviarNotificacionUsuario_Registro(NotificacionUsuarioDTO notificacion);
    void enviarNotificacionUsuario_Edicion(NotificacionUsuarioDTO notificacion);
    void enviarNotificacionUsuario_Anular(NotificacionUsuarioDTO notificacion);
    void enviarNotificacionUsuario_Activar(NotificacionUsuarioDTO notificacion);
}
