package com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.domain.services;

import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.request.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.response.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.domain.interfaces.IVehiculoDetalle;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.domain.interfaces.IVehiculoEdicion;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.domain.interfaces.IVehiculoListado;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.domain.interfaces.IVehiculoRegistro;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.infraestructure.persistence.repository.VehiculoDetalleRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.infraestructure.persistence.repository.VehiculoEdicionRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.infraestructure.persistence.repository.VehiculoListaRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.infraestructure.persistence.repository.VehiculoRegistroRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class VehiculosService implements IVehiculoListado, IVehiculoDetalle, IVehiculoEdicion, IVehiculoRegistro {

    private final VehiculoListaRepository vehiculoListaRepository;
    private final VehiculoDetalleRepository vehiculoDetalleRepository;
    private final VehiculoEdicionRepository vehiculoEdicionRepository;
    private final VehiculoRegistroRepository vehiculoRegistroRepository;

    public VehiculosService(
            VehiculoListaRepository vehiculoListaRepository,
            VehiculoDetalleRepository vehiculoDetalleRepository,
            VehiculoEdicionRepository vehiculoEdicionRepository,
            VehiculoRegistroRepository vehiculoRegistroRepository
    ) {
        this.vehiculoListaRepository = vehiculoListaRepository;
        this.vehiculoDetalleRepository= vehiculoDetalleRepository;
        this.vehiculoEdicionRepository = vehiculoEdicionRepository;
        this.vehiculoRegistroRepository= vehiculoRegistroRepository;
    }


    @Override
    @Cacheable(value = "vehiculos_lista", key = "#request.idVehiculo")
    public ResponseListaVehiculo ListaVehiculos(RequestListaVehiculo request) {
        return vehiculoListaRepository.ListaVehiculos(request);
    }

    @Override
    @Cacheable(value = "vehiculos_detalle", key = "#request.idVehiculo")
    public ResponseDetalleVehiculo detalleVehiculo(RequestDetalleVehiculo request) {
        return vehiculoDetalleRepository.detalleVehiculo(request);
    }

    @Override
    @CacheEvict(value = {"vehiculos_lista", "vehiculos_detalle"}, allEntries = true)
    public ResponseEditarAllVehiculo editarAllVehiculo(RequestEditarAllVehiculo request) {
        return vehiculoEdicionRepository.editarAllVehiculo(request);
    }

    @Override
    @CacheEvict(value = {"vehiculos_lista", "vehiculos_detalle"}, allEntries = true)
    public ResponseEditarEstadoVehiculo editarEstadoVehiculo(RequestEditarEstadoVehiculo request, int estado, long idUserAutenticado) {
        return vehiculoEdicionRepository.editarEstadoVehiculo(request, estado, idUserAutenticado);
    }

    @Override
    @CacheEvict(value = {"vehiculos_lista", "vehiculos_detalle"}, allEntries = true)
    public ResponseRegistroVehiculo registroVehiculo(RequestRegistroVehiculo request, long idUserAutenticado) {
        return vehiculoRegistroRepository.registroVehiculo(request, idUserAutenticado);
    }
}
