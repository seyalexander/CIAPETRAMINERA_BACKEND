package com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.domain.validations;

import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.request.RequestDetalleEmpleados;

public class ValdiacionRequest_DetalleEmpleados {

    public static void validarDetalleEmpleado(RequestDetalleEmpleados request) {
        if (request.getIdEmpleado() == 0) {
            String mensajeError = "El código del empleado es obligatorio.";
            throw new IllegalArgumentException(mensajeError);
        }

        if (request.getIdEmpleado() < 0) {
            String mensajeError = "Código de empleado no válido.";
            throw new IllegalArgumentException(mensajeError);
        }
    }
}
