package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.config.SecurityUtils;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.dto.request.RequestEditarAllTipoDocumento;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.dto.response.ResponseEditarAllTipoDocumento;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.domain.service.TipoDocumentoService;
import org.springframework.stereotype.Component;

@Component
public class EdicionAllTipoDocumentoUseCase {

    private final TipoDocumentoService tipoDocumentoService;

    public EdicionAllTipoDocumentoUseCase(
            TipoDocumentoService tipoDocumentoService
    ){
        this.tipoDocumentoService = tipoDocumentoService;
    }

    public ResponseEditarAllTipoDocumento EditarAllTipoDocumento(RequestEditarAllTipoDocumento request) {
        try {
            // VALIDACIÓN DE CAMPOS
            if (request == null) {
                String mensajeError = "No se encontró datos para editar";
                throw new IllegalArgumentException(mensajeError);
            }

            if (request.getIdTipoDocumento() == 0) {
                String mensajeError = "No se está enviando correctamente el código del tipo de documento";
                throw new IllegalArgumentException(mensajeError);
            }

            if (request.getDescripcion() == null || request.getDescripcion().isEmpty()) {
                String mensajeError = "La descripción no puede estar vacía";
                throw new IllegalArgumentException(mensajeError);
            }

            if (request.getTipoCaracter() == 0) {
                String mensajeError = "El tipo carácter no se está enviando correctamente";
                throw new IllegalArgumentException(mensajeError);
            }

            if (request.getCodigoSunat() == null || request.getCodigoSunat().isEmpty()) {
                String mensajeError = "El Código SUNAT del documento no puede enviarse en vacío";
                throw new IllegalArgumentException(mensajeError);
            }

            if (request.getLongitudMin() == 0) {
                String mensajeError = "La longitud mínima debe enviarse obligatoriamente";
                throw new IllegalArgumentException(mensajeError);
            }

            if (request.getLongitudMax() == 0) {
                String mensajeError = "La longitud máxima debe enviarse obligatoriamente";
                throw new IllegalArgumentException(mensajeError);
            }

            if (request.getLongitudMin() > request.getLongitudMax()) {
                String mensajeError = "La longitud mínima no debe ser mayor a la longitud máxima";
                throw new IllegalArgumentException(mensajeError);
            }

            if (request.getEstado() > 1 || request.getEstado() < 0) {
                String mensajeError = "El estado no está dentro de los parámetros correctos";
                throw new IllegalArgumentException(mensajeError);
            }

            // ====================================================================
            // OBTENIENDO ID DEL USUARIO LOGUEADO DEL TOKEN
            // ====================================================================
            long userId = SecurityUtils.getCurrentUserId();

            ResponseEditarAllTipoDocumento response = tipoDocumentoService.EditarTipoDocumento(request, userId);
            if(response.isExito()){}
            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseEditarAllTipoDocumento response = new ResponseEditarAllTipoDocumento();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al editar el tipos de documento: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseEditarAllTipoDocumento response = new ResponseEditarAllTipoDocumento();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }


}
