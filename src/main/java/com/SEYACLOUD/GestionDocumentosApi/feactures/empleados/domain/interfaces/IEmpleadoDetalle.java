package com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.request.RequestDetalleEmpleados;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.response.ResponseDetalleEmpleados;

public interface IEmpleadoDetalle {
    ResponseDetalleEmpleados DetalleEmpleado(RequestDetalleEmpleados request);
}
