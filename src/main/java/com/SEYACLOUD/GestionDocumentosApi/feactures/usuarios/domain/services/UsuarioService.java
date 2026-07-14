package com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.domain.services;


import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.request.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.response.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.domain.interfaces.IUsuarioDetalle;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.domain.interfaces.IUsuarioEdicion;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.domain.interfaces.IUsuarioListado;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.domain.interfaces.IUsuarioRegistro;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.infraestructure.persistence.repository.UsuarioDetalleRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.infraestructure.persistence.repository.UsuarioEdicionRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.infraestructure.persistence.repository.UsuarioListadoRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.infraestructure.persistence.repository.UsuarioRegistroRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UsuarioService implements IUsuarioRegistro, IUsuarioEdicion, IUsuarioListado, IUsuarioDetalle {

    private final UsuarioEdicionRepository usuarioEdicionRepository;
    private final UsuarioListadoRepository usuarioListadoRepository;
    private final UsuarioRegistroRepository usuarioRegistroRepository;
    private final UsuarioDetalleRepository usuarioDetalleRepository;

    public UsuarioService(
            UsuarioEdicionRepository usuarioEdicionRepository,
            UsuarioListadoRepository usuarioListadoRepository,
            UsuarioRegistroRepository usuarioRegistroRepository,
            UsuarioDetalleRepository usuarioDetalleRepository
    ){
        this.usuarioEdicionRepository = usuarioEdicionRepository;
        this.usuarioListadoRepository = usuarioListadoRepository;
        this.usuarioRegistroRepository = usuarioRegistroRepository;
        this.usuarioDetalleRepository = usuarioDetalleRepository;
    }

    @Override
    @Cacheable(value = "usuarios_detalle", key = "#request.idUsuario")
    public ResponseDetalleUsuario DetalleUsuario(RequestDetalleUsuario request) {
        return usuarioDetalleRepository.DetalleUsuario(request);
    }

    @Override
    @CacheEvict(value = {"usuarios_lista", "usuarios_detalle"}, allEntries = true)
    public ResponseEditarAllUsuario EditarUsuario(RequestEditarAllUsuario request, long idUserAutenticado) {
        return usuarioEdicionRepository.EditarUsuario(request, idUserAutenticado);
    }

    @Override
    @CacheEvict(value = {"usuarios_lista", "usuarios_detalle"}, allEntries = true)
    public ResponseEditarEstadoUsuario EditarEstadoUsuario(RequestEditarEstadoUsuario request, int estado, long idUserAutenticado) {
        return usuarioEdicionRepository.EditarEstadoUsuario(request, estado, idUserAutenticado);
    }

    @Override
    @Cacheable(value = "usuarios_lista", key = "#request.estado")
    public ResponseListaUsuario ListaUsuarios(RequestListaUsuario request) {
        return usuarioListadoRepository.ListaUsuarios(request);
    }

    @Override
    @CacheEvict(value = {"usuarios_lista", "usuarios_detalle"}, allEntries = true)
    public ResponseRegistroUsuario registrarUsuario(RequestRegistroUsuario request, long idUserAutenticado) {
        return usuarioRegistroRepository.registrarUsuario(request, idUserAutenticado);
    }
}
