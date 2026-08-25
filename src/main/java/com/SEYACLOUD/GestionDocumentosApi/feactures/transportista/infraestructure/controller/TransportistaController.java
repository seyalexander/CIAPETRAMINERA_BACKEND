package com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.infraestructure.controller;

import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.request.RequestEditarAllTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.request.RequestListaTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.request.RequestRegistroTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.response.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.useCase.*;
import com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto.NotificacionTransportistaDTO;
import com.SEYACLOUD.GestionDocumentosApi.websockets.domain.services.NotificacionTransportistaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/transportista")
public class TransportistaController {

    @Autowired
    private ListaTransportistasUseCase listaTransportistasUseCase;
    @Autowired
    private DetalleTransportistaUseCase detalleTransportistaUseCase;
    @Autowired
    private EdicionTransportistaEstadoUseCase edicionTransportistaEstadoUseCase;
    @Autowired
    private EditarTransportistaAllUseCase editarTransportistaAllUseCase;
    @Autowired
    private RegistroTransportistaUseCase registroTransportistaUseCase;
    @Autowired
    private NotificacionTransportistaService notificacionTransportistaService;


    @GetMapping
    @Operation(summary = "Listar transportistas by estado", description = "Obtiene la lista de transportistas según su estado")
    public ResponseEntity<ResponseListaTransportista> listaTransportistas(@Validated @ModelAttribute RequestListaTransportista request) {
        ResponseListaTransportista response = listaTransportistasUseCase.ListarTransportista(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idTransportista}")
    @Operation(summary = "Detalle Transportista", description = "Obtiene el detalle de un Transportista")
    public ResponseEntity<ResponseDetalleTransportista> detalleTransportista(@PathVariable long idTransportista) {

        ResponseDetalleTransportista response = detalleTransportistaUseCase.DetalleTransportista(idTransportista);

        if (response.isExito()) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{idTransportista}")
    @Operation(summary = "Anular transportista", description = "Cambia el estado del transportista a inactivo")
    public ResponseEntity<ResponseEditarEstadoTransportista> anularTransportista(@PathVariable long idTransportista) {

        ResponseEditarEstadoTransportista response = edicionTransportistaEstadoUseCase.AnularTransportista(idTransportista);

        if (response.isExito()) {
            NotificacionTransportistaDTO notificacion = new NotificacionTransportistaDTO();
            notificacion.setTipo("ANULACION");
            notificacion.setMensaje("Transportista anulado");
            notificacion.setIdTransportista(idTransportista);

            notificacionTransportistaService.enviarNotificacionTransportista_Anular(notificacion);
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PatchMapping("/{idTransportista}/activar")
    @Operation(summary = "Activar transportista by id", description = "Activa nuevamente un transportista previamente anulada")
    public ResponseEntity<ResponseEditarEstadoTransportista> activarRol(@PathVariable long idTransportista) {

        ResponseEditarEstadoTransportista response = edicionTransportistaEstadoUseCase.ActivarTransportista(idTransportista);

        if (response.isExito()) {
            NotificacionTransportistaDTO notificacion = new NotificacionTransportistaDTO();
            notificacion.setTipo("ACTIVACION");
            notificacion.setMensaje("Transportista activado");
            notificacion.setIdTransportista(idTransportista);

            notificacionTransportistaService.enviarNotificacionTransportista_Activar(notificacion);

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PutMapping
    @Operation(summary = "Editar Transportista", description = "Permite editar todos los datos de un Transportista existente")
    public ResponseEntity<ResponseEditarAllTransportista> edicionAllTransportista(
            @Validated @RequestBody RequestEditarAllTransportista request) {

        ResponseEditarAllTransportista response = editarTransportistaAllUseCase.EditarTransportista(request);

        if (response.isExito()) {
            NotificacionTransportistaDTO notificacion = new NotificacionTransportistaDTO();
            notificacion.setTipo("EDICION");
            notificacion.setMensaje("Transportista editado");

            notificacionTransportistaService.enviarNotificacionTransportista_Edicion(notificacion);
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PostMapping
    @Operation(summary = "Registrar transportista", description = "Permite registrar un nuevo transportista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transportista registrada correctamente"),
            @ApiResponse(responseCode = "400", description = "Error en los datos enviados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<ResponseRegistroTransportista> registroTransportista(
            @Validated @RequestBody RequestRegistroTransportista request) {

        ResponseRegistroTransportista response = registroTransportistaUseCase.RegistroTransportista(request);

        if (response.isExito()) {
            NotificacionTransportistaDTO notificacion = new NotificacionTransportistaDTO();
            notificacion.setTipo("REGISTRO");
            notificacion.setMensaje("Nuevo Transportista registrado");

            notificacionTransportistaService.enviarNotificacionTransportista_Registro(notificacion);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
