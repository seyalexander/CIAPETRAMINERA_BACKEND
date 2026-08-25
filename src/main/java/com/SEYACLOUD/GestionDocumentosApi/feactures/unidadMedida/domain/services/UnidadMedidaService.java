package com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.domain.services;

import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.application.dto.request.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.application.dto.response.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.domain.interfaces.IUnidadMedidaDetalle;
import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.domain.interfaces.IUnidadMedidaEdicion;
import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.domain.interfaces.IUnidadMedidaListado;
import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.domain.interfaces.IUnidadMedidaRegistro;
import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.infraestructure.persistence.repository.crud.UnidadMedidaDetalleRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.infraestructure.persistence.repository.crud.UnidadMedidaEdicionRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.infraestructure.persistence.repository.crud.UnidadMedidaListaRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.infraestructure.persistence.repository.crud.UnidadMedidaRegistroRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UnidadMedidaService implements IUnidadMedidaListado, IUnidadMedidaDetalle, IUnidadMedidaEdicion, IUnidadMedidaRegistro {

    private final UnidadMedidaListaRepository unidadMedidaListaRepository;
    private final UnidadMedidaEdicionRepository unidadMedidaEdicionRepository;
    private final UnidadMedidaRegistroRepository unidadMedidaRegistroRepository;
    private final UnidadMedidaDetalleRepository unidadMedidaDetalleRepository;

    public UnidadMedidaService (UnidadMedidaListaRepository unidadMedidaListaRepository, UnidadMedidaEdicionRepository unidadMedidaEdicionRepository, UnidadMedidaRegistroRepository unidadMedidaRegistroRepository, UnidadMedidaDetalleRepository unidadMedidaDetalleRepository) {
        this.unidadMedidaListaRepository = unidadMedidaListaRepository;
        this.unidadMedidaEdicionRepository = unidadMedidaEdicionRepository;
        this.unidadMedidaRegistroRepository = unidadMedidaRegistroRepository;
        this.unidadMedidaDetalleRepository = unidadMedidaDetalleRepository;
    }

    @Override
    @Cacheable(value = "unidadMedida_lista", key = "#request.estado")
    public ResponseListaUnidadMedida listaUnidadMedida(RequestListaUnidadMedida request) {
        return unidadMedidaListaRepository.listaUnidadMedida(request);
    }

    @Override
    @Cacheable(value = "unidadMedida_detalle", key = "#request.idUnidadMedida")
    public ResponseDetalleUnidadMedida DetalleUnidadMedida(RequestDetalleUnidadMedida request) {
        return unidadMedidaDetalleRepository.DetalleUnidadMedida(request);
    }

    @Override
    @CacheEvict(value = {"unidadMedida_lista", "unidadMedida_detalle"}, allEntries = true)
    public ResponseEditarAllUnidadMedida EditarAllUnidadMedida(RequestEditarAllUnidadMedida request) {
        return unidadMedidaEdicionRepository.EditarAllUnidadMedida(request);
    }

    @Override
    @CacheEvict(value = {"unidadMedida_lista", "unidadMedida_detalle"}, allEntries = true)
    public ResponseEditarEstadoUnidadMedida EditarEstadoUnidadMedida(RequestEditarEstadoUnidadMedida request, int estado) {
        return unidadMedidaEdicionRepository.EditarEstadoUnidadMedida(request, estado);
    }

    @Override
    @CacheEvict(value = {"unidadMedida_lista", "unidadMedida_detalle"}, allEntries = true)
    public ResponseRegistroUnidadMedida RegistroUnidadMedida(RequestRegistroUnidadMedida request) {
        return unidadMedidaRegistroRepository.RegistroUnidadMedida(request);
    }
}
