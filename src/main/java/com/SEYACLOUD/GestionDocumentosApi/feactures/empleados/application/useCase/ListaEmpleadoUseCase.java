package com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.request.RequestListaEmpleados;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.response.ResponseListaEmpleados;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.domain.services.EmpleadoService;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.domain.validations.ValidacionRequest_ListarEmpleados;
import org.springframework.stereotype.Component;

@Component
public class ListaEmpleadoUseCase {
    private final EmpleadoService empleadoService;

    public ListaEmpleadoUseCase(
            EmpleadoService empleadoService
    ){
        this.empleadoService = empleadoService;
    }

    public ResponseListaEmpleados ListarEmpleado(RequestListaEmpleados request) {
        try {
            ValidacionRequest_ListarEmpleados.validarListarEmpleado(request);
            ResponseListaEmpleados response = empleadoService.ListaEmpleado(request);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseListaEmpleados response = new ResponseListaEmpleados();
            response.setExito(false);
            response.setMessage(e.getMessage());
            response.setEmpleados(java.util.List.of());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al listar los empleados: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseListaEmpleados response = new ResponseListaEmpleados();
            response.setExito(false);
            response.setMessage(mensajeError);
            response.setEmpleados(java.util.List.of());
            return response;
        }
    }
}
