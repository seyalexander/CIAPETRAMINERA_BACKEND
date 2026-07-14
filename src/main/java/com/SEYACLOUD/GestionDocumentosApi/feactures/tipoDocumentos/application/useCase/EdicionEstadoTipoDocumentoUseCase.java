package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.config.SecurityUtils;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.dto.request.RequestEditarEstadoTipoDocumento;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.dto.response.ResponseEditarEstadoTipoDocumento;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.domain.service.TipoDocumentoService;
import org.springframework.stereotype.Component;

@Component
public class EdicionEstadoTipoDocumentoUseCase {

    private final TipoDocumentoService tipoDocumentoService;

    public EdicionEstadoTipoDocumentoUseCase(
            TipoDocumentoService tipoDocumentoService
    ){
        this.tipoDocumentoService = tipoDocumentoService;
    }

    public ResponseEditarEstadoTipoDocumento AnularTipoDocumento(long idTipoDocumentos) {
        try {

            RequestEditarEstadoTipoDocumento request  = new RequestEditarEstadoTipoDocumento();
            request.setIdTipoDocumento(idTipoDocumentos);

            // ====================================================================
            // VALIDACIÓN DE CAMPOS
            // ====================================================================
            if (request == null) {
                String mensajeError = "No se encontró datos para editar";
                throw new IllegalArgumentException(mensajeError);
            }

            if (request.getIdTipoDocumento() == 0) {
                String mensajeError = "No se está enviando correctamente el código del tipo de documento";
                throw new IllegalArgumentException(mensajeError);
            }

            // ====================================================================
            // OBTENIENDO ID DEL USUARIO LOGUEADO DEL TOKEN
            // ====================================================================
            long userId = SecurityUtils.getCurrentUserId();


            // ====================================================================
            // CONSUMIENDO SERVICIO
            // ====================================================================
            ResponseEditarEstadoTipoDocumento response = tipoDocumentoService.EditarEstadoTipoDocumento(request, 0, userId);
            if(response.isExito()){

            }
            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseEditarEstadoTipoDocumento response = new ResponseEditarEstadoTipoDocumento();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al editar el tipos de documento: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseEditarEstadoTipoDocumento response = new ResponseEditarEstadoTipoDocumento();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }

    public ResponseEditarEstadoTipoDocumento ActivarTipoDocumento(long idTipoDocumentos) {
        try {

            RequestEditarEstadoTipoDocumento request  = new RequestEditarEstadoTipoDocumento();
            request.setIdTipoDocumento(idTipoDocumentos);

            // ====================================================================
            // VALIDACIÓN DE CAMPOS
            // ====================================================================
            if (request == null) {
                String mensajeError = "No se encontró datos para editar";
                throw new IllegalArgumentException(mensajeError);
            }

            if (request.getIdTipoDocumento() == 0) {
                String mensajeError = "No se está enviando correctamente el código del tipo de documento";
                throw new IllegalArgumentException(mensajeError);
            }


            // ====================================================================
            // OBTENIENDO ID DEL USAURIO LOGUEADO DEL TOKEN
            // ====================================================================
            long userId = SecurityUtils.getCurrentUserId();

            // ====================================================================
            // CONSUMIENDO SERVICIO
            // ====================================================================
            ResponseEditarEstadoTipoDocumento response = tipoDocumentoService.EditarEstadoTipoDocumento(request, 1, userId);
            if(response.isExito()){

            }
            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseEditarEstadoTipoDocumento response = new ResponseEditarEstadoTipoDocumento();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al editar el tipos de documento: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseEditarEstadoTipoDocumento response = new ResponseEditarEstadoTipoDocumento();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }

}
