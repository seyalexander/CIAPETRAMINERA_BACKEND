package com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.config.SecurityUtils;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.request.RequestEdicionEstadoEmpleados;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.response.ResponseEdicionEstadoEmpleados;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.domain.services.EmpleadoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EdicionEmpleadoEstadoUseCase {
    private final EmpleadoService empleadoService;

    public EdicionEmpleadoEstadoUseCase(
            EmpleadoService empleadoService
    ) {
        this.empleadoService = empleadoService;
    }

    public ResponseEdicionEstadoEmpleados AnularEmpleado(long idEmpleado) {
        try {

            // Obtener idUsuario del token
            long userId = SecurityUtils.getCurrentUserId();

            // VALIDACIONES
            if (idEmpleado == 0) {
                throw new IllegalArgumentException("El código del empleado es obligatorio.");
            }

            if (idEmpleado < 0) {
                throw new IllegalArgumentException("Código de empleado no válido.");
            }

            RequestEdicionEstadoEmpleados request = new RequestEdicionEstadoEmpleados();
            request.setIdEmpleado(idEmpleado);

            ResponseEdicionEstadoEmpleados response =
                    empleadoService.EditarEstadoEmpleado(request, 0, userId);

            return response;

        } catch (IllegalArgumentException | SecurityException e) {

            ResponseEdicionEstadoEmpleados response = new ResponseEdicionEstadoEmpleados();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;

        } catch (Exception e) {

            String mensajeError = "Error inesperado al anular al empleado: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);

            ResponseEdicionEstadoEmpleados response = new ResponseEdicionEstadoEmpleados();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }

    public ResponseEdicionEstadoEmpleados ActivarEmpleado(long idEmpleado) {
        try {
            // Obtener idUsuario del token
            long userId = SecurityUtils.getCurrentUserId();

            // VALIDACIONES
            if (idEmpleado == 0) {
                throw new IllegalArgumentException("El código del empleado es obligatorio.");
            }

            if (idEmpleado < 0) {
                throw new IllegalArgumentException("Código de empleado no válido.");
            }

            RequestEdicionEstadoEmpleados request = new RequestEdicionEstadoEmpleados();
            request.setIdEmpleado(idEmpleado);
            ResponseEdicionEstadoEmpleados response = empleadoService.EditarEstadoEmpleado(request, 1, userId);
            if (response.isExito()) {
            }

            return response;

        } catch (IllegalArgumentException | SecurityException e) {
            ResponseEdicionEstadoEmpleados response = new ResponseEdicionEstadoEmpleados();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        } catch (Exception e) {
            String mensajeError = "Error inesperado al activar al empleado: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseEdicionEstadoEmpleados response = new ResponseEdicionEstadoEmpleados();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;

        }
    }
}
