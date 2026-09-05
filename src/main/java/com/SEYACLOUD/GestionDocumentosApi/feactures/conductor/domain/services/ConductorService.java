package com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.domain.services;

import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.request.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.response.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.domain.interfaces.IConductorDetalle;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.domain.interfaces.IConductorEdicion;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.domain.interfaces.IConductorLista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.domain.interfaces.IConductorRegistro;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.infraestructure.persistence.repository.ConductorDetalleRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.infraestructure.persistence.repository.ConductorEdicionRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.infraestructure.persistence.repository.ConductorListaRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.infraestructure.persistence.repository.ConductorRegistroRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConductorService implements IConductorLista, IConductorDetalle, IConductorEdicion, IConductorRegistro {

    private final ConductorListaRepository conductorListaRepository;
    private final ConductorDetalleRepository conductorDetalleRepository;
    private final ConductorEdicionRepository conductorEdicionRepository;
    private final ConductorRegistroRepository conductorRegistroRepository;

    public ConductorService(
            ConductorListaRepository conductorListaRepository,
            ConductorDetalleRepository conductorDetalleRepository,
            ConductorEdicionRepository conductorEdicionRepository,
            ConductorRegistroRepository conductorRegistroRepository
    ) {
        this.conductorListaRepository = conductorListaRepository;
        this.conductorDetalleRepository = conductorDetalleRepository;
        this.conductorEdicionRepository = conductorEdicionRepository;
        this.conductorRegistroRepository = conductorRegistroRepository;
    }


    @Override
    @Cacheable(value = "conductores_lista", key = "#request.estado")
    public ResponseListaConductor listaConductores(RequestListaConductor request) {
        return conductorListaRepository.listaConductores(request);
    }

    @Override
    @Cacheable(value = "conductores_detalle", key = "#request.idConductor")
    public ResponseDetalleConductor detalleConductor(RequestDetalleConductor request) {
        return conductorDetalleRepository.detalleConductor(request);
    }

    @Override
    @CacheEvict(value = {"conductores_lista", "conductores_detalle"}, allEntries = true)
    public ResponseEditarAllConductor editarAllConductor(RequestEditarAllConductor request, long idUserAutenticado) {
        return conductorEdicionRepository.editarAllConductor(request, idUserAutenticado);
    }

    @Override
    @CacheEvict(value = {"conductores_lista", "conductores_detalle"}, allEntries = true)
    public ResponseEditarEstadoConductor editarEstadoConductor(RequestEditarEstadoConductor request, int estado, long idUserAutenticado) {
        return conductorEdicionRepository.editarEstadoConductor(request, estado, idUserAutenticado);
    }

    @Override
    @CacheEvict(value = {"conductores_lista", "conductores_detalle"}, allEntries = true)
    public ResponseRegistroConductor registroConductor(RequestRegistroConductor request, long idUserAutenticado) {
        return conductorRegistroRepository.registroConductor(request, idUserAutenticado);
    }
}
