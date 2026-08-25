package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.domain.services;

import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.request.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.response.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.domain.interfaces.ITipoClientesDetalle;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.domain.interfaces.ITipoClientesEdicion;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.domain.interfaces.ITipoClientesListado;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.domain.interfaces.ITipoClientesRegistro;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.infraestructure.persistence.repository.crud.TipoClientesDetalleRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.infraestructure.persistence.repository.crud.TipoClientesEdicionRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.infraestructure.persistence.repository.crud.TipoClientesListadoRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.infraestructure.persistence.repository.crud.TipoClientesRegistroRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class TipoClientesService  implements ITipoClientesDetalle, ITipoClientesEdicion, ITipoClientesListado, ITipoClientesRegistro {

    private final TipoClientesDetalleRepository tipoClientesDetalleRepository;
    private final TipoClientesEdicionRepository tipoClientesEdicionRepository;
    private final TipoClientesListadoRepository tipoClientesListadoRepository;
    private final TipoClientesRegistroRepository tipoClientesRegistroRepository;

    public TipoClientesService(
            TipoClientesDetalleRepository tipoClientesDetalleRepository,
            TipoClientesEdicionRepository tipoClientesEdicionRepository,
            TipoClientesListadoRepository tipoClientesListadoRepository,
            TipoClientesRegistroRepository tipoClientesRegistroRepository
    ) {
        this.tipoClientesDetalleRepository = tipoClientesDetalleRepository;
        this.tipoClientesEdicionRepository = tipoClientesEdicionRepository;
        this.tipoClientesListadoRepository = tipoClientesListadoRepository;
        this.tipoClientesRegistroRepository = tipoClientesRegistroRepository;
    }

    @Override
    @Cacheable(value = "tipoClientes_lista", key = "#request.estado")
    public ResponseListaTipoClientes ListaTipoClientes(RequestListaTipoClientes request) {
        return tipoClientesListadoRepository.ListaTipoClientes(request);
    }

    @Override
    @CacheEvict(value = {"tipoClientes_lista", "tipoClientes_detalle", "tipoClientes_descripcion"}, allEntries = true)
    public ResponseRegistroTipoClientes RegistroTipoClientes(RequestRegistroTipoClientes request) {
        return tipoClientesRegistroRepository.RegistroTipoClientes(request);
    }
    @Override
    @CacheEvict(value = {"tipoClientes_lista", "tipoClientes_detalle", "tipoClientes_descripcion"}, allEntries = true)
    public ResponseEditarAllTipoClientes EditarAllTipoClientes(RequestEditarAllTipoClientes request, long idUserAutenticado) {
        return tipoClientesEdicionRepository.EditarAllTipoClientes(request, idUserAutenticado);
    }
    @Override
    @CacheEvict(value = {"tipoClientes_lista", "tipoClientes_detalle", "tipoClientes_descripcion"}, allEntries = true)
    public ResponseEditarEstadoTipoClientes EditarEstadoTipoClientes(RequestEditarEstadoTipoClientes request, int estado) {
        return tipoClientesEdicionRepository.EditarEstadoTipoClientes(request, estado);
    }
    @Override
    @Cacheable(value = "tipoClientes_detalle", key = "#request.idTipoCliente")
    public ResponseDetalleTipoClientes detalleTipoClientes(RequestDetalleTipoClientes request) {
        return tipoClientesDetalleRepository.detalleTipoClientes(request);
    }

    @Override
    @Cacheable(value = "tipoClientes_descripcion", key = "#request.descripcion")
    public ResponseVerificarDescripcionTipoClientes verificarDescripcion(RequestVerificarDescripcionTipoClientes request) {
        return tipoClientesDetalleRepository.verificarDescripcion(request);
    }
}