package com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.domain.interfaces;


import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.request.RequestRegistroEmpleados;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.response.ResponseRegistroEmpleados;

public interface IEmpleadoRegistro {
    ResponseRegistroEmpleados RegistroEmpleado(RequestRegistroEmpleados request, long userAutenticado, long idEmpresa);
}
