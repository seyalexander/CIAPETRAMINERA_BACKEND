package com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.config.SecurityUtils;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.request.RequestEditarAllUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.response.ResponseEditarAllUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.domain.services.UsuarioService;
import org.springframework.stereotype.Component;

@Component
public class EdicionUsuarioUseCase {
    private final UsuarioService usuarioService;

    public EdicionUsuarioUseCase(
            UsuarioService usuarioService
    ){
        this.usuarioService = usuarioService;
    }

    public ResponseEditarAllUsuario EditarUsuario(RequestEditarAllUsuario request) {
        try {
            long userId = SecurityUtils.getCurrentUserId();
            ResponseEditarAllUsuario response = usuarioService.EditarUsuario(request, userId);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseEditarAllUsuario response = new ResponseEditarAllUsuario();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al editar al usuario: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseEditarAllUsuario response = new ResponseEditarAllUsuario();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }
}
