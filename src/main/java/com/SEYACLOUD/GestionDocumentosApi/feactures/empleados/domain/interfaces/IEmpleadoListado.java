package com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.domain.interfaces;


import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.request.RequestListaEmpleados;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.response.ResponseListaEmpleados;

public interface IEmpleadoListado {
    ResponseListaEmpleados ListaEmpleado(RequestListaEmpleados request);
}
