package com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.infraestructure.controller;

import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.request.RequestEditarAllConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.request.RequestRegistroConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.response.ResponseDetalleConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.response.ResponseEditarAllConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.response.ResponseEditarEstadoConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.response.ResponseRegistroConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.useCase.EdicionConductorEstadoUseCase;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.useCase.EditarConductorAllUseCase;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.useCase.RegistroConductorUseCase;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.request.RequestEditarAllVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.request.RequestListaVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.request.RequestRegistroVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.response.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.useCase.*;
import com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto.NotificacionConductorDTO;
import com.SEYACLOUD.GestionDocumentosApi.websockets.application.dto.NotificacionVehiculoDTO;
import com.SEYACLOUD.GestionDocumentosApi.websockets.domain.services.NotificacionVehiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/vehiculo")
public class VehiculosController {

    @Autowired
    private ListaVehiculosUseCase listaVehiculosUseCase;
    @Autowired
    private DetalleVehiculoUseCase detalleVehiculoUseCase;
    @Autowired
    private EdicionVehiculoEstadoUseCase edicionVehiculoEstadoUseCase;
    @Autowired
    private EdicionVehiculoAllUseCase edicionVehiculoAllUseCase;
    @Autowired
    private RegistroVehiculoUseCase registroVehiculoUseCase;
    @Autowired
    private NotificacionVehiculoService notificacionVehiculoService;

    @GetMapping
    @Operation(summary = "Listar vehículos by estado", description = "Obtiene la lista de vehículos según su estado")
    public ResponseEntity<ResponseListaVehiculo> listaVehiculos(@Validated @ModelAttribute RequestListaVehiculo request) {

        ResponseListaVehiculo response = listaVehiculosUseCase.ListarVehiculo(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idVehiculo}")
    @Operation(summary = "Detalle vehículo", description = "Obtiene el detalle de un Vehiculo")
    public ResponseEntity<ResponseDetalleVehiculo> detalleVehiculo(@PathVariable long idVehiculo) {

        ResponseDetalleVehiculo response = detalleVehiculoUseCase.DetalleVehiculo(idVehiculo);

        if (response.isExito()) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{idVehiculo}")
    @Operation(summary = "Anular Vehículo", description = "Cambia el estado del Vehículo a inactivo")
    public ResponseEntity<ResponseEditarEstadoVehiculo> anularVehiculo(@PathVariable long idVehiculo) {

        ResponseEditarEstadoVehiculo response = edicionVehiculoEstadoUseCase.ActivarVehiculo(idVehiculo);

        if (response.isExito()) {
            NotificacionVehiculoDTO notificacion = new NotificacionVehiculoDTO();
            notificacion.setTipo("ANULACION");
            notificacion.setMensaje("Vehículo anulado");
            notificacion.setIdTransportista(idVehiculo);

            notificacionVehiculoService.enviarNotificacionVehiculo_Anular(notificacion);
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PatchMapping("/{idVehiculo}/activar")
    @Operation(summary = "Activar Vehículo by id", description = "Activa nuevamente un Vehículo previamente anulada")
    public ResponseEntity<ResponseEditarEstadoVehiculo> activarVehiculo(@PathVariable long idVehiculo) {

        ResponseEditarEstadoVehiculo response = edicionVehiculoEstadoUseCase.ActivarVehiculo(idVehiculo);

        if (response.isExito()) {
            NotificacionVehiculoDTO notificacion = new NotificacionVehiculoDTO();
            notificacion.setTipo("ACTIVACION");
            notificacion.setMensaje("Vehículo activado");
            notificacion.setIdTransportista(idVehiculo);

            notificacionVehiculoService.enviarNotificacionVehiculo_Activar(notificacion);

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PutMapping
    @Operation(summary = "Editar vehículo", description = "Permite editar todos los datos de un vehículo existente")
    public ResponseEntity<ResponseEditarAllVehiculo> edicionAllTransportista(
            @Validated @RequestBody RequestEditarAllVehiculo request) {

        ResponseEditarAllVehiculo response = edicionVehiculoAllUseCase.EditarVehiculo(request);

        if (response.isExito()) {
            NotificacionVehiculoDTO notificacion = new NotificacionVehiculoDTO();
            notificacion.setTipo("EDICION");
            notificacion.setMensaje("Vehículo editado");

            notificacionVehiculoService.enviarNotificacionVehiculo_Edicion(notificacion);
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PostMapping
    @Operation(summary = "Registrar vehículo", description = "Permite registrar un nuevo vehículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vehículo registrada correctamente"),
            @ApiResponse(responseCode = "400", description = "Error en los datos enviados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<ResponseRegistroVehiculo> registroVehiculo(
            @Validated @RequestBody RequestRegistroVehiculo request) {

        ResponseRegistroVehiculo response = registroVehiculoUseCase.RegistroVehiculo(request);

        if (response.isExito()) {
            NotificacionVehiculoDTO notificacion = new NotificacionVehiculoDTO();
            notificacion.setTipo("REGISTRO");
            notificacion.setMensaje("Nuevo Vehículo registrado");

            notificacionVehiculoService.enviarNotificacionVehiculo_Registro(notificacion);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
