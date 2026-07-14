package com.SEYACLOUD.GestionDocumentosApi.feactures.roles.domain.services;

import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.request.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.response.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.domain.interfaces.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.infraestructure.persistence.repository.crud.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RolService implements IRolListado, IRolRegistro, IRolEdicion, IRolDetalle, IRolUsuario {

    private final RolListadoRepository rolListadoRepository;
    private final RolRegistroRepository rolRegistroRepository;
    private final RolEditarRepository rolEditarRepository;
    private final RolDetalleRepository rolDetalleRepository;
    private final RolByUsuarioRepository rolByUsuarioRepository;

    public RolService(
            RolListadoRepository rolListadoRepository,
            RolRegistroRepository rolRegistroRepository,
            RolEditarRepository rolEditarRepository,
            RolDetalleRepository rolDetalleRepository,
            RolByUsuarioRepository rolByUsuarioRepository
    ) {
        this.rolListadoRepository = rolListadoRepository;
        this.rolRegistroRepository = rolRegistroRepository;
        this.rolEditarRepository = rolEditarRepository;
        this.rolDetalleRepository = rolDetalleRepository;
        this.rolByUsuarioRepository= rolByUsuarioRepository;
    }

    @Override
    @Cacheable(value = "roles", key = "#request.estado")
    public ResponseListaRol ListaRol(RequestListaRol request) {
        return rolListadoRepository.ListaRol(request);
    }

    @Override
    @CacheEvict(value = {"roles", "rol_detalle", "roles_usuario"}, allEntries = true)
    public ResponseRegistroRol registrarRol(RequestRegistroRol request) {
        return rolRegistroRepository.registrarRol(request);
    }

    @Override
    @CacheEvict(value = {"roles", "rol_detalle", "roles_usuario"}, allEntries = true)
    public ResponseEditarAllRol EditarRol(RequestEditarAllRol request) {
        return rolEditarRepository.EditarRol(request);
    }

    @Override
    @CacheEvict(value = {"roles", "rol_detalle", "roles_usuario"}, allEntries = true)
    public ResponseEditarEstadoRol EditarEstadoRol(RequestEditarEstadoRol request, int estado) {
        return rolEditarRepository.EditarEstadoRol(request, estado);
    }

    @Override
    @Cacheable(value = "rol_detalle", key = "#request.idRol")
    public ResponseDetalleRol DetalleRol(RequestDetalleRol request) {
        return rolDetalleRepository.DetalleRol(request);
    }

    @Override
    @Cacheable(value = "roles_usuario", key = "#request.idUsuario")
    public ResponseRolByUsuario obtenerRolesPorUsuario(RequestRolByUsuario request) {
        return rolByUsuarioRepository.obtenerRolesPorUsuario(request);
    }
}
