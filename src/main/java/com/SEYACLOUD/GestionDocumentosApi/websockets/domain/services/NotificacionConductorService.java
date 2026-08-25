package com.SEYACLOUD.GestionDocumentosApi.websockets.domain.services;

import com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto.NotificacionConductorDTO;
import com.SEYACLOUD.GestionDocumentosApi.websockets.domain.interfaces.INotificacionConductor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificacionConductorService implements INotificacionConductor {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificacionConductorService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }


    @Override
    public void enviarNotificacionConductor_Registro(NotificacionConductorDTO notificacion) {
        try {
            messagingTemplate.convertAndSend("/topic/conductor/conductor-registro", notificacion);
        } catch (Exception e) {
            log.error("Error al enviar notificación WebSocket", e);
        }
    }

    @Override
    public void enviarNotificacionConductor_Edicion(NotificacionConductorDTO notificacion) {
        try {
            messagingTemplate.convertAndSend("/topic/conductor/conductor-edicion", notificacion);
        } catch (Exception e) {
            log.error("Error al enviar notificación WebSocket", e);
        }
    }

    @Override
    public void enviarNotificacionConductor_Anular(NotificacionConductorDTO notificacion) {
        try {
            messagingTemplate.convertAndSend("/topic/conductor/conductor-anular", notificacion);
        } catch (Exception e) {
            log.error("Error al enviar notificación WebSocket", e);
        }
    }

    @Override
    public void enviarNotificacionConductor_Activar(NotificacionConductorDTO notificacion) {
        try {
            messagingTemplate.convertAndSend("/topic/conductor/conductor-activar", notificacion);
        } catch (Exception e) {
            log.error("Error al enviar notificación WebSocket", e);
        }
    }
}
