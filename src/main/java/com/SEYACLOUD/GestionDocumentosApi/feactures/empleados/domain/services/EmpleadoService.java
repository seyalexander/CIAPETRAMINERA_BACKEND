package com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.domain.services;

import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.request.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.response.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.domain.interfaces.IEmpleadoDetalle;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.domain.interfaces.IEmpleadoEdicion;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.domain.interfaces.IEmpleadoListado;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.domain.interfaces.IEmpleadoRegistro;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.infraestructure.persistence.repository.EmpleadoDetalleRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.infraestructure.persistence.repository.EmpleadoEdicionRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.infraestructure.persistence.repository.EmpleadoListadoRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.infraestructure.persistence.repository.EmpleadoRegistroRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmpleadoService implements IEmpleadoListado, IEmpleadoRegistro, IEmpleadoEdicion, IEmpleadoDetalle {

    private final EmpleadoListadoRepository empleadoListadoRepository;
    private final EmpleadoRegistroRepository empleadoRegistroRepository;
    private final EmpleadoEdicionRepository empleadoEdicionRepository;
    private final EmpleadoDetalleRepository empleadoDetalleRepository;

    public EmpleadoService(
            EmpleadoListadoRepository empleadoListadoRepository,
            EmpleadoRegistroRepository empleadoRegistroRepository,
            EmpleadoEdicionRepository empleadoEdicionRepository,
            EmpleadoDetalleRepository empleadoDetalleRepository
    ){
        this.empleadoListadoRepository = empleadoListadoRepository;
        this.empleadoRegistroRepository = empleadoRegistroRepository;
        this.empleadoEdicionRepository = empleadoEdicionRepository;
        this.empleadoDetalleRepository = empleadoDetalleRepository;
    }

    @Override
    @Cacheable(value = "empleados_lista", key = "#request.estado")
    public ResponseListaEmpleados ListaEmpleado(RequestListaEmpleados request) {
        return empleadoListadoRepository.ListaEmpleado(request);
    }

    @Override
    @CacheEvict(value = {"empleados_lista", "empleados_detalle"}, allEntries = true)
    public ResponseRegistroEmpleados RegistroEmpleado(RequestRegistroEmpleados request, long userAutenticado, long idEmpresa) {
        return empleadoRegistroRepository.RegistroEmpleado(request, userAutenticado, idEmpresa);
    }

    @Override
    @CacheEvict(value = {"empleados_lista", "empleados_detalle"}, allEntries = true)
    public ResponseEdicionAllEmpleados EditarAllEmpleado(RequestEdicionAllEmpleados request, long userAutenticado) {
        return empleadoEdicionRepository.EditarAllEmpleado(request, userAutenticado);
    }

    @Override
    @CacheEvict(value = {"empleados_lista", "empleados_detalle"}, allEntries = true)
    public ResponseEdicionEstadoEmpleados EditarEstadoEmpleado(RequestEdicionEstadoEmpleados request, int estado, long userAutenticado) {
        return empleadoEdicionRepository.EditarEstadoEmpleado(request, estado, userAutenticado);
    }

    @Override
    @Cacheable(value = "empleados_detalle", key = "#request.idEmpleado")
    public ResponseDetalleEmpleados DetalleEmpleado(RequestDetalleEmpleados request) {
        return empleadoDetalleRepository.DetalleEmpleado(request);
    }
}
