package com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.config.SecurityUtils;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.request.RequestEdicionAllEmpleados;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.response.ResponseEdicionAllEmpleados;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.domain.services.EmpleadoService;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.domain.validations.ValidacionRequest_EditarEmpleados;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.dto.request.RequestDetalleTipoDocumento;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.dto.response.ResponseDetalleTipoDocumento;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.domain.service.TipoDocumentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EdicionEmpleadoUseCase {

    private final EmpleadoService empleadoService;
    private final TipoDocumentoService tipoDocumentoService;

    public EdicionEmpleadoUseCase(
            EmpleadoService empleadoService,
            TipoDocumentoService tipoDocumentoService
    ){
        this.empleadoService = empleadoService;
        this.tipoDocumentoService = tipoDocumentoService;
    }

    public ResponseEdicionAllEmpleados EditarEmpleado(RequestEdicionAllEmpleados request) {
        try {
            long userId = SecurityUtils.getCurrentUserId();

            RequestDetalleTipoDocumento requestTipoDocumento = new RequestDetalleTipoDocumento();
            requestTipoDocumento.setIdTipoDocumentos(request.getIdTipoDocumento());

            ResponseDetalleTipoDocumento responseTipoDocumento =
                    tipoDocumentoService.DetalleTipoDocumento(requestTipoDocumento);

            ValidacionRequest_EditarEmpleados.validarEdicionEmpleado(request, responseTipoDocumento);

            return empleadoService.EditarAllEmpleado(request, userId);

        } catch (IllegalArgumentException | SecurityException e) {

            ResponseEdicionAllEmpleados response = new ResponseEdicionAllEmpleados();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;

        } catch (Exception e) {

            String mensajeError = "Error inesperado al editar el empleado: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);

            ResponseEdicionAllEmpleados response = new ResponseEdicionAllEmpleados();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }
}