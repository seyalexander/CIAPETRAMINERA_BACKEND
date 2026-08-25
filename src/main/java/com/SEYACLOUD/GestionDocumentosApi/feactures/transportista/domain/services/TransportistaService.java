package com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.domain.services;

import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.request.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.response.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.domain.interfaces.ITransportistaDetalle;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.domain.interfaces.ITransportistaEdicion;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.domain.interfaces.ITransportistaListado;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.domain.interfaces.ITransportistaRegistro;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.infraestructure.persistence.repository.TransportistaDetalleRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.infraestructure.persistence.repository.TransportistaEdicionRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.infraestructure.persistence.repository.TransportistaListadoRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.infraestructure.persistence.repository.TransportistaRegistroRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransportistaService implements ITransportistaListado, ITransportistaDetalle, ITransportistaEdicion, ITransportistaRegistro {

    private final TransportistaListadoRepository transportistaListadoRepository;
    private final TransportistaDetalleRepository transportistaDetalleRepository;
    private final TransportistaEdicionRepository transportistaEdicionRepository;
    private final TransportistaRegistroRepository transportistaRegistroRepository;

    public TransportistaService(
            TransportistaListadoRepository transportistaListadoRepository,
            TransportistaDetalleRepository transportistaDetalleRepository,
            TransportistaEdicionRepository transportistaEdicionRepository,
            TransportistaRegistroRepository transportistaRegistroRepository
    ) {
        this.transportistaListadoRepository = transportistaListadoRepository;
        this.transportistaDetalleRepository = transportistaDetalleRepository;
        this.transportistaEdicionRepository = transportistaEdicionRepository;
        this.transportistaRegistroRepository = transportistaRegistroRepository;
    }

    @Override
    @Cacheable(value = "transportistas_lista", key = "#request.estado")
    public ResponseListaTransportista listaTransportistas(RequestListaTransportista request) {
        return transportistaListadoRepository.listaTransportistas(request);
    }

    @Override
    @Cacheable(value = "transportistas_detalle", key = "#request.idTransportista")
    public ResponseDetalleTransportista detalleTransportista(RequestDetalleTransportista request) {
        return transportistaDetalleRepository.detalleTransportista(request);
    }

    @Override
    @CacheEvict(value = {"transportistas_lista", "transportistas_detalle"}, allEntries = true)
    public ResponseEditarAllTransportista editarAllTransportista(RequestEditarAllTransportista request) {
        return transportistaEdicionRepository.editarAllTransportista(request);
    }

    @Override
    @CacheEvict(value = {"transportistas_lista", "transportistas_detalle"}, allEntries = true)
    public ResponseEditarEstadoTransportista editarEstadoTransportisa(RequestEditarEstadoTransportista request, int estado, long idUserAutenticado) {
        return transportistaEdicionRepository.editarEstadoTransportisa(request, estado, idUserAutenticado);
    }

    @Override
    @CacheEvict(value = {"transportistas_lista", "transportistas_detalle"}, allEntries = true)
    public ResponseRegistroTransportista registroTransportista(RequestRegistroTransportista request, long idUserAutenticado) {
        return transportistaRegistroRepository.registroTransportista(request, idUserAutenticado);
    }
}
