package com.SEYACLOUD.GestionDocumentosApi.websockets.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto.NotificacionEmpleadoDTO;

public interface INotificacionEmpleado {
    void enviarNotificacionEmpleado_Registro(NotificacionEmpleadoDTO notificacion);
    void enviarNotificacionEmpleado_Edicion(NotificacionEmpleadoDTO notificacion);
    void enviarNotificacionEmpleado_Anular(NotificacionEmpleadoDTO notificacion);
    void enviarNotificacionEmpleado_Activar(NotificacionEmpleadoDTO notificacion);
}
