package com.SEYACLOUD.GestionDocumentosApi.feactures.EmpresaClientes.infraestructure.controller;

import com.SEYACLOUD.GestionDocumentosApi.feactures.EmpresaClientes.application.dto.request.RequestListaEmpresaClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.EmpresaClientes.application.dto.response.ResponseListaEmpresaClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.EmpresaClientes.application.useCase.ListaEmpresaClientesUseCase;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/empresaClientes")
public class EmpresaClientesController {

    @Autowired
    private ListaEmpresaClientesUseCase listaEmpresaClientesUseCase;

    @GetMapping
    @Operation(summary = "Listar empresa clientes by estado", description = "Obtiene la lista de empresas clientes según su estado")
    public ResponseEntity<ResponseListaEmpresaClientes> listaFamilia(@Validated @ModelAttribute RequestListaEmpresaClientes request) {

        ResponseListaEmpresaClientes response = listaEmpresaClientesUseCase.ListaEmpresaClientes(request);

        return ResponseEntity.ok(response);
    }

}
