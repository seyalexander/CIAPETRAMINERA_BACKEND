package com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.common.security.PasswordSecurityService;
import com.SEYACLOUD.GestionDocumentosApi.config.SecurityUtils;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.request.RequestEditarAllUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.response.ResponseEditarAllUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.domain.services.UsuarioService;
import org.springframework.stereotype.Component;

@Component
public class EdicionUsuarioUseCase {
    private final UsuarioService usuarioService;

    private final PasswordSecurityService passwordSecurityService;

    public EdicionUsuarioUseCase(
            UsuarioService usuarioService,
            PasswordSecurityService passwordSecurityService
    ){
        this.usuarioService = usuarioService;
        this.passwordSecurityService = passwordSecurityService;
    }

    public ResponseEditarAllUsuario EditarUsuario(RequestEditarAllUsuario request) {
        try {

            if (request == null) {
                String mensajeError = "No se encontró datos para editar";
                throw new IllegalArgumentException(mensajeError);
            }

            if (request.getPassowrd() == null || request.getPassowrd().isEmpty()) {
                String mensajeError = "La contraseña no puede estar vacía";
                throw new IllegalArgumentException(mensajeError);
            }

            if (request.getUsuario() == null || request.getUsuario().isEmpty()) {
                String mensajeError = "El usuario no puede estar vacío";
                throw new IllegalArgumentException(mensajeError);
            }

            if (request.getIdRol() == 0) {
                String mensajeError = "Le debe asignar un rol al usuario";
                throw new IllegalArgumentException(mensajeError);
            }

            String passwordEncriptada = passwordSecurityService.encriptarPassword(request.getPassowrd());
            request.setPassowrd(passwordEncriptada);

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
