package com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.config.SecurityUtils;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.request.RequestEditarEstadoUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.response.ResponseEditarEstadoUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.domain.services.UsuarioService;
import org.springframework.stereotype.Component;

@Component
public class EdicionUsuarioEstadoUseCase {
    private final UsuarioService usuarioService;

    public EdicionUsuarioEstadoUseCase(
            UsuarioService usuarioService
    ){
        this.usuarioService = usuarioService;
    }

    public ResponseEditarEstadoUsuario AnularUsuario(long idUsuario) {
        try {
            RequestEditarEstadoUsuario request = new RequestEditarEstadoUsuario();
            request.setIdUsuario(idUsuario);

            // ====================================================================
            // OBTENIENDO ID DEL USUARIO LOGUEADO DEL TOKEN
            // ====================================================================
            long userId = SecurityUtils.getCurrentUserId();

            ResponseEditarEstadoUsuario response = usuarioService.EditarEstadoUsuario(request, 0, userId);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseEditarEstadoUsuario response = new ResponseEditarEstadoUsuario();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al anular al usuario: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseEditarEstadoUsuario response = new ResponseEditarEstadoUsuario();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }

    public ResponseEditarEstadoUsuario ActivarUsuario(long idUsuario) {
        try {
            RequestEditarEstadoUsuario request = new RequestEditarEstadoUsuario();
            request.setIdUsuario(idUsuario);

            // ====================================================================
            // OBTENIENDO ID DEL USUARIO LOGUEADO DEL TOKEN
            // ====================================================================
            long userId = SecurityUtils.getCurrentUserId();

            ResponseEditarEstadoUsuario response = usuarioService.EditarEstadoUsuario(request, 1, userId);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseEditarEstadoUsuario response = new ResponseEditarEstadoUsuario();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al activar al usuario: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseEditarEstadoUsuario response = new ResponseEditarEstadoUsuario();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }
}
