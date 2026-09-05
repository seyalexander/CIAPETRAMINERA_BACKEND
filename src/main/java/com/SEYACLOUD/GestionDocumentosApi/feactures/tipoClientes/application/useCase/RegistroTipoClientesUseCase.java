package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.request.RequestRegistroTipoClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.response.ResponseRegistroTipoClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.response.ResponseVerificarDescripcionTipoClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.domain.services.TipoClientesService;
import org.springframework.stereotype.Component;

@Component
public class RegistroTipoClientesUseCase {

    private final TipoClientesService tipoClientesService;
    private final ValidaDescripcionTipoClienteUseCase ValidaDescripcionTipoClienteUseCase;

    public RegistroTipoClientesUseCase(
            TipoClientesService tipoClientesService,
            ValidaDescripcionTipoClienteUseCase ValidaDescripcionTipoClienteUseCase
    ) {
        this.tipoClientesService = tipoClientesService;
        this.ValidaDescripcionTipoClienteUseCase = ValidaDescripcionTipoClienteUseCase;
    }

    public ResponseRegistroTipoClientes RegistroTipoClientes(RequestRegistroTipoClientes request) {
        try {

            // ===============================================================
            // Validar si la descripción enviada ya existe en la bd
            // ===============================================================
            /*ResponseVerificarDescripcionTipoClientes respDescripcion = ValidaDescripcionTipoClienteUseCase.ValidaDescripcionTipoClientes(request.getDescripcion());
            if (!respDescripcion.isExito()) {
                throw new IllegalArgumentException(respDescripcion.getMessage());
            }

            if (
                    respDescripcion.getTipoClientes()
                            .getDescripcion()
                            .strip()
                            .equalsIgnoreCase(request.getDescripcion().strip())
            ) {
                throw new IllegalArgumentException(
                        "Ya existe un tipo de cliente con esta descripción. Intente con otra descripción o utilice el tipo de cliente existente."
                );
            }*/

            // ===============================================================
            // Enviando los valores a registrar
            // ===============================================================

            ResponseRegistroTipoClientes response = tipoClientesService.RegistroTipoClientes(request);
            if(response.isExito()){}

            return response;
        }catch (IllegalArgumentException | SecurityException e){
            ResponseRegistroTipoClientes response = new ResponseRegistroTipoClientes();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e) {
            String mensajeError = "Error inesperado al registrar el tipo cliente: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseRegistroTipoClientes response = new ResponseRegistroTipoClientes();
            response.setExito(false);
            response.setMessage("Error inesperado: " + e.getMessage());
            return response;
        }
    }
}