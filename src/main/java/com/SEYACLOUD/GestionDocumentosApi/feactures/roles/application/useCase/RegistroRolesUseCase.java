package com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.request.RequestRegistroRol;
import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.response.ResponseRegistroRol;
import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.domain.services.RolService;
import org.springframework.stereotype.Component;

@Component
public class RegistroRolesUseCase {
    private final RolService rolService;

    public RegistroRolesUseCase(
            RolService rolService
    ){
        this.rolService = rolService;
    }

    public ResponseRegistroRol RegistroRoles(RequestRegistroRol request) {
        try {
            // VALIDACIÓN DE CAMPOS
            if (request == null) {
                String mensajeError = "No se encontró datos para registrar";
                throw new IllegalArgumentException(mensajeError);
            }

            if (request.getDescripcion() == null || request.getDescripcion().isEmpty()) {
                String mensajeError = "La descripción del rol no puede estar vacío";
                throw new IllegalArgumentException(mensajeError);
            }

            ResponseRegistroRol response = rolService.registrarRol(request);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseRegistroRol response = new ResponseRegistroRol();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al registrar el rol: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseRegistroRol response = new ResponseRegistroRol();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }
}
