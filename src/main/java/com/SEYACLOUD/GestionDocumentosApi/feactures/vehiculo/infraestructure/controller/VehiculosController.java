package com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.infraestructure.controller;

import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.request.RequestListaVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.response.ResponseListaVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.useCase.ListaVehiculosUseCase;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/vehiculo")
public class VehiculosController {

    @Autowired
    ListaVehiculosUseCase listaVehiculosUseCase;

    @GetMapping
    @Operation(summary = "Listar vehículos by estado", description = "Obtiene la lista de vehículos según su estado")
    public ResponseEntity<ResponseListaVehiculo> listaVehiculos(@Validated @ModelAttribute RequestListaVehiculo request) {

        ResponseListaVehiculo response = listaVehiculosUseCase.ListarVehiculo(request);

        return ResponseEntity.ok(response);
    }
}
