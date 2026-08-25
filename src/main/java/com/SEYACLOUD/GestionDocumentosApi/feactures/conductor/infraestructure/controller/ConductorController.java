package com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.infraestructure.controller;

import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.request.RequestEditarAllConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.request.RequestListaConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.request.RequestRegistroConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.response.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.useCase.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.response.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.useCase.*;
import com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto.NotificacionConductorDTO;
import com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto.NotificacionTransportistaDTO;
import com.SEYACLOUD.GestionDocumentosApi.websockets.domain.services.NotificacionConductorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/conductores")
public class ConductorController {

    @Autowired
    private ListaConductorUseCase listaConductorUseCase;
    @Autowired
    private DetalleConductorUseCase detalleConductorUseCase;
    @Autowired
    private EdicionConductorEstadoUseCase edicionConductorEstadoUseCase;
    @Autowired
    private EditarConductorAllUseCase editarConductorAllUseCase;
    @Autowired
    private RegistroConductorUseCase registroConductorUseCase;
    @Autowired
    private NotificacionConductorService notificacionConductorService;

    @GetMapping
    @Operation(summary = "Listar Conductor by estado", description = "Obtiene la lista de Conductores según su estado")
    public ResponseEntity<ResponseListaConductor> listaConductores(@Validated @ModelAttribute RequestListaConductor request) {
        ResponseListaConductor response = listaConductorUseCase.ListarConductor(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idConductor}")
    @Operation(summary = "Detalle Conductor", description = "Obtiene el detalle de un Conductor")
    public ResponseEntity<ResponseDetalleConductor> detalleConductor(@PathVariable long idConductor) {

        ResponseDetalleConductor response = detalleConductorUseCase.DetalleConductor(idConductor);

        if (response.isExito()) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{idConductor}")
    @Operation(summary = "Anular conductor", description = "Cambia el estado del conductor a inactivo")
    public ResponseEntity<ResponseEditarEstadoConductor> anularConductor(@PathVariable long idConductor) {

        ResponseEditarEstadoConductor response = edicionConductorEstadoUseCase.AnularConductor(idConductor);

        if (response.isExito()) {
            NotificacionConductorDTO notificacion = new NotificacionConductorDTO();
            notificacion.setTipo("ANULACION");
            notificacion.setMensaje("Condcutor anulado");
            notificacion.setIdTransportista(idConductor);

            notificacionConductorService.enviarNotificacionConductor_Anular(notificacion);
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PatchMapping("/{idConductor}/activar")
    @Operation(summary = "Activar conductor by id", description = "Activa nuevamente un conductor previamente anulada")
    public ResponseEntity<ResponseEditarEstadoConductor> activarConductor(@PathVariable long idConductor) {

        ResponseEditarEstadoConductor response = edicionConductorEstadoUseCase.ActivarConductor(idConductor);

        if (response.isExito()) {
            NotificacionConductorDTO notificacion = new NotificacionConductorDTO();
            notificacion.setTipo("ACTIVACION");
            notificacion.setMensaje("Conductor activado");
            notificacion.setIdTransportista(idConductor);

            notificacionConductorService.enviarNotificacionConductor_Activar(notificacion);

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PutMapping
    @Operation(summary = "Editar Conductor", description = "Permite editar todos los datos de un Conductor existente")
    public ResponseEntity<ResponseEditarAllConductor> edicionAllTransportista(
            @Validated @RequestBody RequestEditarAllConductor request) {

        ResponseEditarAllConductor response = editarConductorAllUseCase.EditarConductor(request);

        if (response.isExito()) {
            NotificacionConductorDTO notificacion = new NotificacionConductorDTO();
            notificacion.setTipo("EDICION");
            notificacion.setMensaje("Conductor editado");

            notificacionConductorService.enviarNotificacionConductor_Edicion(notificacion);
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PostMapping
    @Operation(summary = "Registrar conductor", description = "Permite registrar un nuevo conductor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Conductor registrada correctamente"),
            @ApiResponse(responseCode = "400", description = "Error en los datos enviados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<ResponseRegistroConductor> registroTransportista(
            @Validated @RequestBody RequestRegistroConductor request) {

        ResponseRegistroConductor response = registroConductorUseCase.RegistroConductor(request);

        if (response.isExito()) {
            NotificacionConductorDTO notificacion = new NotificacionConductorDTO();
            notificacion.setTipo("REGISTRO");
            notificacion.setMensaje("Nuevo conductor registrado");

            notificacionConductorService.enviarNotificacionConductor_Registro(notificacion);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
