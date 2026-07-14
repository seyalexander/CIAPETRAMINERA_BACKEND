package com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.config.SecurityUtils;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.request.RequestRegistroEmpleados;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.response.ResponseRegistroEmpleados;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.domain.services.EmpleadoService;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.domain.validations.ValidacionRequest_RegistrarEmpleados;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.dto.request.RequestDetalleTipoDocumento;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.dto.response.ResponseDetalleTipoDocumento;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.domain.service.TipoDocumentoService;
import org.springframework.stereotype.Component;


@Component
public class RegistroEmpleadoUseCase {
    private final EmpleadoService empleadoService;
    private final TipoDocumentoService tipoDocumentoService;

    public RegistroEmpleadoUseCase(
            EmpleadoService empleadoService,
            TipoDocumentoService tipoDocumentoService
    ){
        this.empleadoService = empleadoService;
        this.tipoDocumentoService= tipoDocumentoService;
    }

    public ResponseRegistroEmpleados RegistrorEmpleado(RequestRegistroEmpleados request) {
        try {
            // Obtener idUsuario del token
            long userId = SecurityUtils.getCurrentUserId();
            long idEmpresa = SecurityUtils.getCurrentUserId();

            RequestDetalleTipoDocumento requestTipoDocumento = new RequestDetalleTipoDocumento();
            requestTipoDocumento.setIdTipoDocumentos(request.getIdTipoDocumento());

            ResponseDetalleTipoDocumento responseTipoDocumento =
                    tipoDocumentoService.DetalleTipoDocumento(requestTipoDocumento);
            ValidacionRequest_RegistrarEmpleados.validarRegistroEmpleado(request, responseTipoDocumento);

            ResponseRegistroEmpleados response = empleadoService.RegistroEmpleado(request,userId, idEmpresa);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseRegistroEmpleados response = new ResponseRegistroEmpleados();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al registrar un empleado: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseRegistroEmpleados response = new ResponseRegistroEmpleados();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }
}
