package com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.domain.validations;

import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.request.RequestListaEmpleados;

public class ValidacionRequest_ListarEmpleados {
    public static void validarListarEmpleado(RequestListaEmpleados request) {
        if (request.getEstado()< 0 || request.getEstado() > 2) {
            throw new IllegalArgumentException("El valor del estado no es el correcto.");
        }
    }
}
