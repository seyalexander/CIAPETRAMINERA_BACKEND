package com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.request.RequestDetalleEmpleados;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.response.ResponseDetalleEmpleados;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.domain.services.EmpleadoService;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.domain.validations.ValdiacionRequest_DetalleEmpleados;
import org.springframework.stereotype.Component;

@Component
public class DetalleEmpleadoUseCase {
    private final EmpleadoService empleadoService;

    public DetalleEmpleadoUseCase(
            EmpleadoService empleadoService
    ){
        this.empleadoService = empleadoService;
    }

    public ResponseDetalleEmpleados DetalleEmpleado(long idEmpleado) {
        try {
            RequestDetalleEmpleados request = new RequestDetalleEmpleados();
            request.setIdEmpleado(idEmpleado);
            ValdiacionRequest_DetalleEmpleados.validarDetalleEmpleado(request);
            ResponseDetalleEmpleados response = empleadoService.DetalleEmpleado(request);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseDetalleEmpleados response = new ResponseDetalleEmpleados();
            response.setExito(false);
            response.setMessage(e.getMessage());
            response.setEmpleado(null);
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al obtener el detalle del empleado: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseDetalleEmpleados response = new ResponseDetalleEmpleados();
            response.setExito(false);
            response.setMessage(mensajeError);
            response.setEmpleado(null);
            return response;
        }
    }
}
