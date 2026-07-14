package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.dto.request.RequestListaTipoDocumento;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.dto.response.ResponseListaTipoDocumento;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.domain.service.TipoDocumentoService;
import org.springframework.stereotype.Component;

@Component
public class ListaTipoDocumentoUseCase {
    private final TipoDocumentoService tipoDocumentoService;

    public ListaTipoDocumentoUseCase(
            TipoDocumentoService tipoDocumentoService
    ){
        this.tipoDocumentoService = tipoDocumentoService;
    }

    public ResponseListaTipoDocumento ListarTipoDocumento(RequestListaTipoDocumento request) {
        try {
            long userId = 1L;
            ResponseListaTipoDocumento response = tipoDocumentoService.ListaTipoDocumento(request, userId);
            if(response.isExito()){

            }

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseListaTipoDocumento response = new ResponseListaTipoDocumento();
            response.setExito(false);
            response.setMessage(e.getMessage());
            response.setTipoDocumentos(java.util.List.of());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al listar los tipos de documentos: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseListaTipoDocumento response = new ResponseListaTipoDocumento();
            response.setExito(false);
            response.setMessage(mensajeError);
            response.setTipoDocumentos(java.util.List.of());
            return response;
        }
    }
}
