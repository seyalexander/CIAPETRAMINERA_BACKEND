package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.domain.service;

import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.dto.request.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.dto.response.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.domain.interfaces.ITipoDocumentoDetalle;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.domain.interfaces.ITipoDocumentoEdicion;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.domain.interfaces.ITipoDocumentoListado;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.domain.interfaces.ITipoDocumentoRegistro;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.infraestructure.persistence.repository.TipoDocumentoDetalleRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.infraestructure.persistence.repository.TipoDocumentoEdicionRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.infraestructure.persistence.repository.TipoDocumentoListadoRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.infraestructure.persistence.repository.TipoDocumentoRegistroRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TipoDocumentoService implements ITipoDocumentoListado, ITipoDocumentoRegistro, ITipoDocumentoDetalle, ITipoDocumentoEdicion {

    private final TipoDocumentoListadoRepository tipoDocumentoListadoRepository;
    private final TipoDocumentoRegistroRepository tipoDocumentoRegistroRepository;
    private final TipoDocumentoDetalleRepository tipoDocumentoDetalleRepository;
    private final TipoDocumentoEdicionRepository tipoDocumentoEdicionRepository;

    public TipoDocumentoService(
            TipoDocumentoListadoRepository tipoDocumentoListadoRepository,
            TipoDocumentoRegistroRepository tipoDocumentoRegistroRepository,
            TipoDocumentoDetalleRepository tipoDocumentoDetalleRepository,
            TipoDocumentoEdicionRepository tipoDocumentoEdicionRepository
    ){
        this.tipoDocumentoListadoRepository = tipoDocumentoListadoRepository;
        this.tipoDocumentoRegistroRepository = tipoDocumentoRegistroRepository;
        this.tipoDocumentoDetalleRepository = tipoDocumentoDetalleRepository;
        this.tipoDocumentoEdicionRepository = tipoDocumentoEdicionRepository;
    }

    @Override
    @Cacheable(value = "tipoDocumento_lista", key = "#request.estado")
    public ResponseListaTipoDocumento ListaTipoDocumento(RequestListaTipoDocumento request, long userAutenticado) {
        return tipoDocumentoListadoRepository.ListaTipoDocumento(request, userAutenticado);
    }

    @Override
    @Cacheable(value = "tipoDocumentos_detalle", key = "#request.idTipoDocumentos")
    public ResponseDetalleTipoDocumento DetalleTipoDocumento(RequestDetalleTipoDocumento request) {
        return tipoDocumentoDetalleRepository.DetalleTipoDocumento(request);
    }

    @Override
    @CacheEvict(value = {"tipoDocumentos_lista","tipoDocumentos_detalle"}, allEntries = true)
    public ResponseRegistroTipoDocumento RegistroTipoDocumento(RequestRegistroTipoDocumento request, long userAutenticado) {
        return tipoDocumentoRegistroRepository.RegistroTipoDocumento(request, userAutenticado);
    }

    @Override
    @CacheEvict(value = {"tipoDocumentos_lista","tipoDocumentos_detalle"}, allEntries = true)
    public ResponseEditarAllTipoDocumento EditarTipoDocumento(RequestEditarAllTipoDocumento request, long userAutenticado) {
        return tipoDocumentoEdicionRepository.EditarTipoDocumento(request, userAutenticado);
    }

    @Override
    @CacheEvict(value = {"tipoDocumentos_lista","tipoDocumentos_detalle"}, allEntries = true)
    public ResponseEditarEstadoTipoDocumento EditarEstadoTipoDocumento(RequestEditarEstadoTipoDocumento request, int estado, long userAutenticado) {
        return tipoDocumentoEdicionRepository.EditarEstadoTipoDocumento(request, estado, userAutenticado);
    }
}
