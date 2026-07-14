package com.SEYACLOUD.GestionDocumentosApi.websockets.domain.services;

import com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto.NotificacionTipoVehiculoDTO;
import com.SEYACLOUD.GestionDocumentosApi.websockets.domain.interfaces.INotificacionTipoVehiculo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificacionTipoVehiculoService implements INotificacionTipoVehiculo {
    
    private final SimpMessagingTemplate messagingTemplate;

    public NotificacionTipoVehiculoService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void enviarNotificacionTipoVehiculo_Registro(NotificacionTipoVehiculoDTO notificacion) {
        try {
            messagingTemplate.convertAndSend("/topic/tipoVehiculo/tipoVehiculo-registro", notificacion);
        } catch (Exception e) {
            log.error("Error al enviar notificación WebSocket", e);
        }
    }

    @Override
    public void enviarNotificacionTipoVehiculo_Edicion(NotificacionTipoVehiculoDTO notificacion) {
        try {
            messagingTemplate.convertAndSend("/topic/tipoVehiculo/tipoVehiculo-edicion", notificacion);
        } catch (Exception e) {
            log.error("Error al enviar notificación WebSocket", e);
        }
    }

    @Override
    public void enviarNotificacionTipoVehiculo_Anular(NotificacionTipoVehiculoDTO notificacion) {
        try {
            messagingTemplate.convertAndSend("/topic/tipoVehiculo/tipoVehiculo-anular", notificacion);
        } catch (Exception e) {
            log.error("Error al enviar notificación WebSocket", e);
        }
    }

    @Override
    public void enviarNotificacionTipoVehiculo_Activar(NotificacionTipoVehiculoDTO notificacion) {
        try {
            messagingTemplate.convertAndSend("/topic/tipoVehiculo/tipoVehiculo-activar", notificacion);
        } catch (Exception e) {
            log.error("Error al enviar notificación WebSocket", e);
        }
    }
}
