package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.request.RequestEditarEstadoTipoClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.response.ResponseDetalleTipoClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.response.ResponseEditarEstadoTipoClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.domain.services.TipoClientesService;
import org.springframework.stereotype.Component;

@Component
public class EdicionTipoClientesEstadoUseCase {

    private final TipoClientesService tipoClientesService;
    private final DetalleTipoClientesUseCase detalleTipoClientesUseCase;

    public EdicionTipoClientesEstadoUseCase(
            TipoClientesService tipoClientesService,
            DetalleTipoClientesUseCase detalleTipoClientesUseCase) {
        this.tipoClientesService = tipoClientesService;
        this.detalleTipoClientesUseCase = detalleTipoClientesUseCase;
    }

    public ResponseEditarEstadoTipoClientes AnularTipoCliente(long idTipoCliente) {
        try {

            if (idTipoCliente <= 0) {
                String mensajeError = "El código para anular no es válido";
                throw new IllegalArgumentException(mensajeError);
            }

            // ===============================================================
            // Validando que el tipo cliente exista
            // ===============================================================

            ResponseDetalleTipoClientes resDetalle = detalleTipoClientesUseCase.DetalleTipoClientes(idTipoCliente);

            if (!resDetalle.isExito()) {
                throw new IllegalArgumentException(resDetalle.getMessage());
            }

            if (resDetalle.getTipoClientes() == null) {
                throw new IllegalArgumentException("El tipo de cliente con ID " + idTipoCliente + " no existe.");
            }

            if (resDetalle.getTipoClientes().getIdTipoCliente() <= 0) {
                throw new IllegalArgumentException("El tipo de cliente obtenido no es válido.");
            }

            // ===============================================================
            // Enviando los valores a editar
            // ===============================================================

            RequestEditarEstadoTipoClientes request = new RequestEditarEstadoTipoClientes();
            request.setIdTipoClientes(idTipoCliente);
            ResponseEditarEstadoTipoClientes response = tipoClientesService.EditarEstadoTipoClientes(request,0);
            if(response.isExito()){}

            return response;
        } catch (IllegalArgumentException | SecurityException e) {
            ResponseEditarEstadoTipoClientes response = new ResponseEditarEstadoTipoClientes();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        } catch (Exception e) {
            String mensajeError = "Error inesperado al editar el estado del tipo cliente: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseEditarEstadoTipoClientes response = new ResponseEditarEstadoTipoClientes();
            response.setExito(false);
            response.setMessage("Error inesperado: " + e.getMessage());
            return response;
        }
    }

    public ResponseEditarEstadoTipoClientes ActivarTipoCliente(long idTipoCliente) {
        try {

            if (idTipoCliente <= 0) {
                String mensajeError = "El código para activar no es válido";
                throw new IllegalArgumentException(mensajeError);
            }

            // ===============================================================
            // Validando que el tipo cliente exista
            // ===============================================================

            ResponseDetalleTipoClientes resDetalle = detalleTipoClientesUseCase.DetalleTipoClientes(idTipoCliente);

            if (!resDetalle.isExito()) {
                throw new IllegalArgumentException(resDetalle.getMessage());
            }

            if (resDetalle.getTipoClientes() == null) {
                throw new IllegalArgumentException("El tipo de cliente con ID " + idTipoCliente + " no existe.");
            }

            if (resDetalle.getTipoClientes().getIdTipoCliente() <= 0) {
                throw new IllegalArgumentException("El tipo de cliente obtenido no es válido.");
            }

            // ===============================================================
            // Enviando los valores a editar
            // ===============================================================

            RequestEditarEstadoTipoClientes request = new RequestEditarEstadoTipoClientes();
            request.setIdTipoClientes(idTipoCliente);
            ResponseEditarEstadoTipoClientes response = tipoClientesService.EditarEstadoTipoClientes(request,1);
            if(response.isExito()){}

            return response;
        } catch (IllegalArgumentException | SecurityException e) {
            ResponseEditarEstadoTipoClientes response = new ResponseEditarEstadoTipoClientes();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        } catch (Exception e) {
            String mensajeError = "Error inesperado al editar el estado del tipo cliente: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseEditarEstadoTipoClientes response = new ResponseEditarEstadoTipoClientes();
            response.setExito(false);
            response.setMessage("Error inesperado: " + e.getMessage());
            return response;
        }
    }
}