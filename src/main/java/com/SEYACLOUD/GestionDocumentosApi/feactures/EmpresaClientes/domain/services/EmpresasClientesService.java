package com.SEYACLOUD.GestionDocumentosApi.feactures.EmpresaClientes.domain.services;

import com.SEYACLOUD.GestionDocumentosApi.feactures.EmpresaClientes.application.dto.request.RequestListaEmpresaClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.EmpresaClientes.application.dto.response.ResponseListaEmpresaClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.EmpresaClientes.domain.interfaces.IEmpresaClientesListado;
import com.SEYACLOUD.GestionDocumentosApi.feactures.EmpresaClientes.infraestructure.persistence.repository.EmpresaClientesListadoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmpresasClientesService implements IEmpresaClientesListado {

    private final EmpresaClientesListadoRepository empresaClientesListadoRepository;

    public EmpresasClientesService(EmpresaClientesListadoRepository empresaClientesListadoRepository) {
        this.empresaClientesListadoRepository = empresaClientesListadoRepository;
    }

    @Override
    @Cacheable(value = "empresaClientes", key = "#request.estado")
    public ResponseListaEmpresaClientes ListaEmpresaClientes(RequestListaEmpresaClientes request) {
        return empresaClientesListadoRepository.ListaEmpresaClientes(request);
    }
}
