package com.SEYACLOUD.GestionDocumentosApi.websockets.domain.services;

import com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto.NotificacionVehiculoDTO;
import com.SEYACLOUD.GestionDocumentosApi.websockets.domain.interfaces.INotificacionVehiculo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificacionVehiculoService implements INotificacionVehiculo {
    
    private final SimpMessagingTemplate messagingTemplate;

    public NotificacionVehiculoService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void enviarNotificacionVehiculo_Registro(NotificacionVehiculoDTO notificacion) {
        try {
            messagingTemplate.convertAndSend("/topic/vehiculo/vehiculo-registro", notificacion);
        } catch (Exception e) {
            log.error("Error al enviar notificación WebSocket", e);
        }
    }

    @Override
    public void enviarNotificacionVehiculo_Edicion(NotificacionVehiculoDTO notificacion) {
        try {
            messagingTemplate.convertAndSend("/topic/vehiculo/vehiculo-edicion", notificacion);
        } catch (Exception e) {
            log.error("Error al enviar notificación WebSocket", e);
        }
    }

    @Override
    public void enviarNotificacionVehiculo_Anular(NotificacionVehiculoDTO notificacion) {
        try {
            messagingTemplate.convertAndSend("/topic/vehiculo/vehiculo-anular", notificacion);
        } catch (Exception e) {
            log.error("Error al enviar notificación WebSocket", e);
        }
    }

    @Override
    public void enviarNotificacionVehiculo_Activar(NotificacionVehiculoDTO notificacion) {
        try {
            messagingTemplate.convertAndSend("/topic/vehiculo/vehiculo-activar", notificacion);
        } catch (Exception e) {
            log.error("Error al enviar notificación WebSocket", e);
        }
    }
}
