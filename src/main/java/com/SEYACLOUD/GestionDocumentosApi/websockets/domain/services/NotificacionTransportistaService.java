package com.SEYACLOUD.GestionDocumentosApi.websockets.domain.services;

import com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto.NotificacionTransportistaDTO;
import com.SEYACLOUD.GestionDocumentosApi.websockets.domain.interfaces.INotificacionTransportista;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificacionTransportistaService implements INotificacionTransportista {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificacionTransportistaService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void enviarNotificacionTransportista_Registro(NotificacionTransportistaDTO notificacion) {
        try {
            messagingTemplate.convertAndSend("/topic/transportista/transportista-registro", notificacion);
        } catch (Exception e) {
            log.error("Error al enviar notificación WebSocket", e);
        }
    }

    @Override
    public void enviarNotificacionTransportista_Edicion(NotificacionTransportistaDTO notificacion) {
        try {
            messagingTemplate.convertAndSend("/topic/transportista/transportista-edicion", notificacion);
        } catch (Exception e) {
            log.error("Error al enviar notificación WebSocket", e);
        }
    }

    @Override
    public void enviarNotificacionTransportista_Anular(NotificacionTransportistaDTO notificacion) {
        try {
            messagingTemplate.convertAndSend("/topic/transportista/transportista-anular", notificacion);
        } catch (Exception e) {
            log.error("Error al enviar notificación WebSocket", e);
        }
    }

    @Override
    public void enviarNotificacionTransportista_Activar(NotificacionTransportistaDTO notificacion) {
        try {
            messagingTemplate.convertAndSend("/topic/transportista/transportista-activar", notificacion);
        } catch (Exception e) {
            log.error("Error al enviar notificación WebSocket", e);
        }
    }
}
