package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.infraestructure.controller;

import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.request.RequestListaRol;
import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.response.ResponseDetalleRol;
import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.response.ResponseListaRol;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.useCase.EdicionEstadoTipoDocumentoUseCase;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.request.RequestEditarAllTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.request.RequestListaTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.request.RequestRegistroTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.response.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.useCase.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.request.RequestEditarAllUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.request.RequestRegistroUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.response.ResponseEditarAllUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.response.ResponseEditarEstadoUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.response.ResponseRegistroUsuario;
import com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto.NotificacionTipoVehiculoDTO;
import com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto.NotificacionUsuarioDTO;
import com.SEYACLOUD.GestionDocumentosApi.websockets.domain.services.NotificacionTipoVehiculoService;
import com.SEYACLOUD.GestionDocumentosApi.websockets.domain.services.NotificacionUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/tipoVehiculo")
public class TipoVehiculoController {

    @Autowired
    private ListaTipoVehiculosUseCase listaTipoVehiculosUseCase;

    @Autowired
    private DetalleTipoVehiculoUseCase detalleTipoVehiculoUseCase;

    @Autowired
    private EdicionAllTipoVehiculoUseCase edicionAllTipoVehiculoUseCase;

    @Autowired
    private EditarEstadoTipoVehiculoUseCase editarEstadoTipoVehiculoUseCase;

    @Autowired
    private RegistroTipoVehiculoUseCase registroTipoVehiculoUseCase;

    @Autowired
    private NotificacionTipoVehiculoService notificacionTipoVehiculoService;

    @GetMapping
    @Operation(summary = "Listar tipo de vehículos by estado", description = "Obtiene la lista de tipos de vehículos según su estado")
    public ResponseEntity<ResponseListaTipoVehiculo> listaTipoVehiculo(@Validated @ModelAttribute RequestListaTipoVehiculo request) {

        ResponseListaTipoVehiculo response = listaTipoVehiculosUseCase.ListaTipoVehiculo(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idTipoVehiculo}")
    @Operation(summary = "Detalle Tipo Vehículo", description = "Obtiene el detalle de un tipo vehículo")
    public ResponseEntity<ResponseDetalleTipoVehiculo> detalleTipoVehiculo(@PathVariable long idTipoVehiculo) {

        ResponseDetalleTipoVehiculo response = detalleTipoVehiculoUseCase.DetalleTipoVehiculo(idTipoVehiculo);

        if (response.isExito()) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PutMapping
    @Operation(summary = "Editar Tipo Vehículo", description = "Permite editar todos los datos de un Tipo Vehículo existente")
    public ResponseEntity<ResponseEditarAllTipoVehiculo> edicionAllTipoVehiculo(
            @Validated @RequestBody RequestEditarAllTipoVehiculo request) {

        ResponseEditarAllTipoVehiculo response = edicionAllTipoVehiculoUseCase.EditarTipoVehiculo(request);

        if (response.isExito()) {
            NotificacionTipoVehiculoDTO notificacion = new NotificacionTipoVehiculoDTO();
            notificacion.setTipo("EDICION");
            notificacion.setMensaje("Tipo Vehiculos editado");

            notificacionTipoVehiculoService.enviarNotificacionTipoVehiculo_Edicion(notificacion);
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @DeleteMapping("/{idTipoVehiculo}")
    @Operation(summary = "Anular Tipo Vehiculo", description = "Cambia el estado del Tipo Vehiculo a inactivo")
    public ResponseEntity<ResponseEditarEstadoTipoVehiculo> anularTipoVehiculo(@PathVariable long idTipoVehiculo) {

        ResponseEditarEstadoTipoVehiculo response = editarEstadoTipoVehiculoUseCase.AnularTipoVehiculo(idTipoVehiculo);

        if (response.isExito()) {
            NotificacionTipoVehiculoDTO notificacion = new NotificacionTipoVehiculoDTO();
            notificacion.setTipo("ANULACION");
            notificacion.setMensaje("Usuario anulado");
            notificacion.setIdTipoVehiculo(idTipoVehiculo);

            notificacionTipoVehiculoService.enviarNotificacionTipoVehiculo_Anular(notificacion);
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PatchMapping("/{idTipoVehiculo}/activar")
    @Operation(summary = "Activar Tipo Vehiculo by id", description = "Activa nuevamente un Tipo Vehiculo previamente anulada")
    public ResponseEntity<ResponseEditarEstadoTipoVehiculo> activarTipoVehiculo(@PathVariable long idTipoVehiculo) {

        ResponseEditarEstadoTipoVehiculo response = editarEstadoTipoVehiculoUseCase.ActivarTipoVehiculo(idTipoVehiculo);

        if (response.isExito()) {
            NotificacionTipoVehiculoDTO notificacion = new NotificacionTipoVehiculoDTO();
            notificacion.setTipo("ACTIVACION");
            notificacion.setMensaje("Rol activado");
            notificacion.setIdTipoVehiculo(idTipoVehiculo);

            notificacionTipoVehiculoService.enviarNotificacionTipoVehiculo_Activar(notificacion);

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PostMapping
    @Operation(summary = "Registrar Tipo Vehiculo", description = "Permite registrar un nuevo Tipo Vehiculo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tipo Vehiculo registrada correctamente"),
            @ApiResponse(responseCode = "400", description = "Error en los datos enviados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<ResponseRegistroTipoVehiculo> registroUsuario(
            @Validated @RequestBody RequestRegistroTipoVehiculo request) {

        ResponseRegistroTipoVehiculo response = registroTipoVehiculoUseCase.RegistroTipoVehiculo(request);

        if (response.isExito()) {
            NotificacionTipoVehiculoDTO notificacion = new NotificacionTipoVehiculoDTO();
            notificacion.setTipo("REGISTRO");
            notificacion.setMensaje("Nuevo Tipo Vehiculo registrado");

            notificacionTipoVehiculoService.enviarNotificacionTipoVehiculo_Registro(notificacion);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
