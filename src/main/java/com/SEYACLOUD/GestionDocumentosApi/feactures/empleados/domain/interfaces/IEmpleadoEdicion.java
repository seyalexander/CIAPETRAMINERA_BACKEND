package com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.domain.interfaces;


import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.request.RequestEdicionAllEmpleados;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.request.RequestEdicionEstadoEmpleados;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.response.ResponseEdicionAllEmpleados;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.response.ResponseEdicionEstadoEmpleados;

public interface IEmpleadoEdicion {
    ResponseEdicionAllEmpleados EditarAllEmpleado(RequestEdicionAllEmpleados request, long userAutenticado);
    ResponseEdicionEstadoEmpleados EditarEstadoEmpleado(RequestEdicionEstadoEmpleados request, int estado, long userAutenticado);
}
