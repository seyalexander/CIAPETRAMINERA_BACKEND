package com.SEYACLOUD.GestionDocumentosApi.websockets.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto.NotificacionClienteDTO;

public interface INotificacionCliente {
    void enviarNotificacionCliente_Registro(NotificacionClienteDTO notificacion);
    void enviarNotificacionCliente_Edicion(NotificacionClienteDTO notificacion);
    void enviarNotificacionCliente_Anular(NotificacionClienteDTO notificacion);
    void enviarNotificacionCliente_Activar(NotificacionClienteDTO notificacion);
}
