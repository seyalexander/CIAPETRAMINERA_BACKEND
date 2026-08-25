package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.request.RequestVerificarDescripcionTipoClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.response.ResponseVerificarDescripcionTipoClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.domain.services.TipoClientesService;
import org.springframework.stereotype.Component;

@Component
public class ValidaDescripcionTipoClienteUseCase {

    private final TipoClientesService tipoClientesService;

    public ValidaDescripcionTipoClienteUseCase(TipoClientesService tipoClientesService) {
        this.tipoClientesService = tipoClientesService;
    }

    public ResponseVerificarDescripcionTipoClientes ValidaDescripcionTipoClientes(String descripcion) {
        try {

            RequestVerificarDescripcionTipoClientes request = new RequestVerificarDescripcionTipoClientes();
            request.setDescripcion(descripcion);

            ResponseVerificarDescripcionTipoClientes response = tipoClientesService.verificarDescripcion(request);
            if(response.isExito()){}

            return response;
        }catch (IllegalArgumentException | SecurityException e){
            ResponseVerificarDescripcionTipoClientes response = new ResponseVerificarDescripcionTipoClientes();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }

        catch (Exception e) {
            String mensajeError = "Error inesperado al obtener la descripción de tipo cliente: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseVerificarDescripcionTipoClientes response = new ResponseVerificarDescripcionTipoClientes();
            response.setExito(false);
            response.setMessage("Error inesperado: " + e.getMessage());
            return response;
        }
    }
}
