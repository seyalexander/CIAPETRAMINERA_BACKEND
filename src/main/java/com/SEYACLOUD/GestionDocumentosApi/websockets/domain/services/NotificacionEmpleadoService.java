package com.SEYACLOUD.GestionDocumentosApi.websockets.domain.services;

import com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto.NotificacionEmpleadoDTO;
import com.SEYACLOUD.GestionDocumentosApi.websockets.domain.interfaces.INotificacionEmpleado;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificacionEmpleadoService implements INotificacionEmpleado {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificacionEmpleadoService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void enviarNotificacionEmpleado_Registro(NotificacionEmpleadoDTO notificacion) {
        try {
            messagingTemplate.convertAndSend("/topic/empleado/empleado-registro", notificacion);
        } catch (Exception e) {
            log.error("Error al enviar notificación WebSocket", e);
        }
    }

    @Override
    public void enviarNotificacionEmpleado_Edicion(NotificacionEmpleadoDTO notificacion) {
        try {
            messagingTemplate.convertAndSend("/topic/empleado/empleado-edicion", notificacion);
        } catch (Exception e) {
            log.error("Error al enviar notificación WebSocket", e);
        }
    }

    @Override
    public void enviarNotificacionEmpleado_Anular(NotificacionEmpleadoDTO notificacion) {
        try {
            messagingTemplate.convertAndSend("/topic/empleado/empleado-anular", notificacion);
        } catch (Exception e) {
            log.error("Error al enviar notificación WebSocket", e);
        }
    }

    @Override
    public void enviarNotificacionEmpleado_Activar(NotificacionEmpleadoDTO notificacion) {
        try {
            messagingTemplate.convertAndSend("/topic/empleado/empleado-activar", notificacion);
        } catch (Exception e) {
            log.error("Error al enviar notificación WebSocket", e);
        }
    }
}
