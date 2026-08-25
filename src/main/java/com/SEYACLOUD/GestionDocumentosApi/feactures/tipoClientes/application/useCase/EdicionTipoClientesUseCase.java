package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.config.SecurityUtils;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.request.RequestEditarAllTipoClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.response.ResponseDetalleTipoClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.response.ResponseEditarAllTipoClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.domain.services.TipoClientesService;
import org.springframework.stereotype.Component;

@Component
public class EdicionTipoClientesUseCase {

    private final TipoClientesService tipoClientesService;
    private final DetalleTipoClientesUseCase detalleTipoClientesUseCase;

    public EdicionTipoClientesUseCase(
            TipoClientesService tipoClientesService,
            DetalleTipoClientesUseCase detalleTipoClientesUseCase
    ) {
        this.tipoClientesService = tipoClientesService;
        this.detalleTipoClientesUseCase = detalleTipoClientesUseCase;
    }

    public ResponseEditarAllTipoClientes EdicionAllTipoClientes(RequestEditarAllTipoClientes request) {
        try {

            // =============================================================================
            //  Nota:
            //  La validación de cada campo se realizó por notaciones en el request
            // =============================================================================

            if (request == null) {
                String mensajeError = "No se encontró datos para editar";
                throw new IllegalArgumentException(mensajeError);
            }

            // ===============================================================
            // Validando que el tipo cliente exista
            // ===============================================================

            ResponseDetalleTipoClientes resDetalle = detalleTipoClientesUseCase.DetalleTipoClientes(request.getIdTipoCliente());

            if (!resDetalle.isExito()) {
                throw new IllegalArgumentException(
                        resDetalle.getMessage()
                );
            }

            if (resDetalle.getTipoClientes() == null) {
                throw new IllegalArgumentException(
                        "El tipo de cliente con ID " + request.getIdTipoCliente() + " no existe."
                );
            }

            if (resDetalle.getTipoClientes().getIdTipoCliente() <= 0) {
                throw new IllegalArgumentException(
                        "El tipo de cliente obtenido no es válido."
                );
            }

            // ===============================================================
            // Enviando los valores a editar
            // ===============================================================

            long userId = SecurityUtils.getCurrentUserId();

            ResponseEditarAllTipoClientes response = tipoClientesService.EditarAllTipoClientes(request, userId);

            if (response.isExito()) {
            }
            return response;

        } catch (IllegalArgumentException | SecurityException e) {
            ResponseEditarAllTipoClientes response = new ResponseEditarAllTipoClientes();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        } catch (Exception e) {
            String mensajeError = "Error inesperado al editar el tipo cliente: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseEditarAllTipoClientes response = new ResponseEditarAllTipoClientes();
            response.setExito(false);
            response.setMessage("Error inesperado: " + e.getMessage());
            return response;
        }
    }
}