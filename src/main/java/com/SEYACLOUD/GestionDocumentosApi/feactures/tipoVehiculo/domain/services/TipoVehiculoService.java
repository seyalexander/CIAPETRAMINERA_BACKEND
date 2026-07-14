package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.domain.services;

import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.request.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.response.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.domain.interfaces.ITipoVehiculoDetalle;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.domain.interfaces.ITipoVehiculoEdicion;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.domain.interfaces.ITipoVehiculoLista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.domain.interfaces.ITipoVehiculoRegistro;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.infraestructure.persistence.repository.TipoVehiculoDetalleRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.infraestructure.persistence.repository.TipoVehiculoEditarRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.infraestructure.persistence.repository.TipoVehiculoListaRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.infraestructure.persistence.repository.TipoVehiculoRegistroRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TipoVehiculoService implements ITipoVehiculoLista, ITipoVehiculoDetalle, ITipoVehiculoEdicion, ITipoVehiculoRegistro {

    private final TipoVehiculoListaRepository tipoVehiculoListaRepository;
    private final TipoVehiculoDetalleRepository tipoVehiculoDetalleRepository;
    private final TipoVehiculoEditarRepository tipoVehiculoEditarRepository;
    private final TipoVehiculoRegistroRepository tipoVehiculoRegistroRepository;

    public TipoVehiculoService(
            TipoVehiculoListaRepository tipoVehiculoListaRepository,
            TipoVehiculoDetalleRepository tipoVehiculoDetalleRepository,
            TipoVehiculoEditarRepository tipoVehiculoEditarRepository,
            TipoVehiculoRegistroRepository tipoVehiculoRegistroRepository
    ) {
        this.tipoVehiculoListaRepository = tipoVehiculoListaRepository;
        this.tipoVehiculoDetalleRepository = tipoVehiculoDetalleRepository;
        this.tipoVehiculoEditarRepository = tipoVehiculoEditarRepository;
        this.tipoVehiculoRegistroRepository = tipoVehiculoRegistroRepository;
    }

    @Override
    @Cacheable(value = "tipoVehiculo_lista", key = "#request.estado")
    public ResponseListaTipoVehiculo ListaTipoVehiculos(RequestListaTipoVehiculo request) {
        return tipoVehiculoListaRepository.ListaTipoVehiculos(request);
    }

    @Override
    @Cacheable(value = "tipoVehiculo_detalle", key = "#request.idTipoVehiculo")
    public ResponseDetalleTipoVehiculo DetalleTipoVehiculo(RequestDetalleTipoVehiculo request) {
        return tipoVehiculoDetalleRepository.DetalleTipoVehiculo(request);
    }

    @Override
    @CacheEvict(value = {"tipoVehiculo_lista", "tipoVehiculo_detalle"}, allEntries = true)
    public ResponseEditarAllTipoVehiculo EditarAllTipoVehiculo(RequestEditarAllTipoVehiculo request) {
        return tipoVehiculoEditarRepository.EditarAllTipoVehiculo(request);
    }

    @Override
    @CacheEvict(value = {"tipoVehiculo_lista", "tipoVehiculo_detalle"}, allEntries = true)
    public ResponseEditarEstadoTipoVehiculo EditarEstadoTipoVehiculo(RequestEditarEstadoTipoVehiculo request, int estado, long idUserAutenticado) {
        return tipoVehiculoEditarRepository.EditarEstadoTipoVehiculo(request, estado, idUserAutenticado);
    }

    @Override
    @CacheEvict(value = {"tipoVehiculo_lista", "tipoVehiculo_detalle"}, allEntries = true)
    public ResponseRegistroTipoVehiculo RegistroTipoVehiculo(RequestRegistroTipoVehiculo request, long idUserAutenticado) {
        return tipoVehiculoRegistroRepository.RegistroTipoVehiculo(request, idUserAutenticado);
    }
}
