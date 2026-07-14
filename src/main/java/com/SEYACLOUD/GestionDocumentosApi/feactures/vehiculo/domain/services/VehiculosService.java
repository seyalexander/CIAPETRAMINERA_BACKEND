package com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.domain.services;

import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.request.RequestListaVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.response.ResponseListaVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.domain.interfaces.IVehiculoListado;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.infraestructure.persistence.repository.VehiculoListaRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class VehiculosService implements IVehiculoListado {

    private final VehiculoListaRepository vehiculoListaRepository;

    public VehiculosService(VehiculoListaRepository vehiculoListaRepository) {
        this.vehiculoListaRepository = vehiculoListaRepository;
    }


    @Override
    @Cacheable(value = "vehiculos_lista", key = "#request.idVehiculo")
    public ResponseListaVehiculo ListaVehiculos(RequestListaVehiculo request) {
        return vehiculoListaRepository.ListaVehiculos(request);
    }
}
