package com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.domain.services;

import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.application.dto.request.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.application.dto.response.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.domain.interfaces.IClienteDetalle;
import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.domain.interfaces.IClienteEdicion;
import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.domain.interfaces.IClienteListado;
import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.domain.interfaces.IClienteRegistro;
import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.infraestructure.persistence.repository.crud.ClienteDetalleRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.infraestructure.persistence.repository.crud.ClienteEdicionRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.infraestructure.persistence.repository.crud.ClienteListadoRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.infraestructure.persistence.repository.crud.ClienteRegistroRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteDetalle, IClienteEdicion, IClienteListado, IClienteRegistro {
    private final ClienteDetalleRepository clienteDetalleRepository;
    private final ClienteEdicionRepository clienteEdicionRepository;
    private final ClienteListadoRepository clienteListadoRepository;
    private final ClienteRegistroRepository clienteRegistroRepository;

    public ClienteService(ClienteDetalleRepository clienteDetalleRepository, ClienteEdicionRepository clienteEdicionRepository, ClienteListadoRepository clienteListadoRepository, ClienteRegistroRepository clienteRegistroRepository) {
        this.clienteDetalleRepository = clienteDetalleRepository;
        this.clienteEdicionRepository = clienteEdicionRepository;
        this.clienteListadoRepository = clienteListadoRepository;
        this.clienteRegistroRepository = clienteRegistroRepository;
    }

    @Override
    public ResponseDetalleCliente DetalleCliente(RequestDetalleCliente request) {
        return clienteDetalleRepository.DetalleCliente(request);
    }

    @Override
    public ResponseEditarAllCliente EditarAllCliente(RequestEditarAllCliente request) {
        return clienteEdicionRepository.EditarAllCliente(request);
    }

    @Override
    public ResponseEditarEstadoCliente EditarEstadoCliente(RequestEditarEstadoCliente request, int estado) {
        return clienteEdicionRepository.EditarEstadoCliente(request, estado);
    }

    @Override
    public ResponseListaCliente ListaCliente(RequestListaCliente request) {
        return clienteListadoRepository.ListaCliente(request);
    }

    @Override
    public ResponseRegistroCliente RegistroCliente(RequestRegistroCliente request) {
        return clienteRegistroRepository.RegistroCliente(request);
    }
}
