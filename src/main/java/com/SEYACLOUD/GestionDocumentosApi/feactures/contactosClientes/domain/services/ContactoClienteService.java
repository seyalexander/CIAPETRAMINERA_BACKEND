package com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.domain.services;

import com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.application.dto.request.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.application.dto.response.*;
import com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.domain.interfaces.IContactoClienteDetalle;
import com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.domain.interfaces.IContactoClienteEdicion;
import com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.domain.interfaces.IContactoClienteListado;
import com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.domain.interfaces.IContactoClienteRegistro;
import com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.infraestructure.persistence.repository.crud.ContactoClienteDetalleRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.infraestructure.persistence.repository.crud.ContactoClienteEdicionRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.infraestructure.persistence.repository.crud.ContactoClienteListadoRepository;
import com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.infraestructure.persistence.repository.crud.ContactoClienteRegistroRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ContactoClienteService implements IContactoClienteDetalle, IContactoClienteEdicion, IContactoClienteListado, IContactoClienteRegistro {
    private final ContactoClienteDetalleRepository contactoClienteDetalleRepository;
    private final ContactoClienteEdicionRepository contactoClienteEdicionRepository;
    private final ContactoClienteListadoRepository contactoClienteListadoRepository;
    private final ContactoClienteRegistroRepository contactoClienteRegistroRepository;

    public ContactoClienteService(ContactoClienteDetalleRepository contactoClienteDetalleRepository, ContactoClienteEdicionRepository contactoClienteEdicionRepository, ContactoClienteListadoRepository contactoClienteListadoRepository, ContactoClienteRegistroRepository contactoClienteRegistroRepository) {
        this.contactoClienteDetalleRepository = contactoClienteDetalleRepository;
        this.contactoClienteEdicionRepository = contactoClienteEdicionRepository;
        this.contactoClienteListadoRepository = contactoClienteListadoRepository;
        this.contactoClienteRegistroRepository = contactoClienteRegistroRepository;
    }

    @Override
    @Cacheable(value = "contactoCliente_detalle", key = "#request.idContactoCliente")
    public ResponseDetalleContactoCliente DetalleContactoCliente(RequestDetalleContactoCliente request) {
        return contactoClienteDetalleRepository.DetalleContactoCliente(request);
    }

    @Override
    @CacheEvict(value = {"contactoCliente_detalle", "contactoCliente_listado"}, allEntries = true)
    public ResponseEditarAllContactoCliente EditarAllContactoCliente(RequestEditarAllContactoCliente request) {
        return contactoClienteEdicionRepository.EditarAllContactoCliente(request);
    }

    @Override
    @CacheEvict(value = {"contactoCliente_detalle", "contactoCliente_listado"}, allEntries = true)
    public ResponseEditarEstadoContactoCliente EditarEstadoContactoCliente(RequestEditarEstadoContactoCliente request, int estado) {
        return contactoClienteEdicionRepository.EditarEstadoContactoCliente(request, estado);
    }

    @Override
    @Cacheable(value = "contactoCliente_detalle", key = "#request.estado")
    public ResponseListaContactoCliente ListaContactoCliente(RequestListaContactoCliente request) {
        return contactoClienteListadoRepository.ListaContactoCliente(request);
    }

    @Override
    @CacheEvict(value = {"contactoCliente_detalle", "contactoCliente_listado"}, allEntries = true)
    public ResponseRegistroContactoCliente RegistroContactoCliente(RequestRegistroContactoCliente request) {
        return contactoClienteRegistroRepository.RegistroContactoCliente(request);
    }
}
