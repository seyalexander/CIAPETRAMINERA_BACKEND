package com.SEYACLOUD.GestionDocumentosApi.websockets.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto.NotificacionConductorDTO;

public interface INotificacionConductor {
    void enviarNotificacionConductor_Registro(NotificacionConductorDTO notificacion);
    void enviarNotificacionConductor_Edicion(NotificacionConductorDTO notificacion);
    void enviarNotificacionConductor_Anular(NotificacionConductorDTO notificacion);
    void enviarNotificacionConductor_Activar(NotificacionConductorDTO notificacion);
}

