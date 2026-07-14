package com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.request.RequestEditarAllRol;
import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.response.ResponseEditarAllRol;
import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.domain.services.RolService;
import org.springframework.stereotype.Component;

@Component
public class EdicionRolesUseCase {
    private final RolService rolService;

    public EdicionRolesUseCase(
            RolService rolService
    ){
        this.rolService = rolService;
    }

    public ResponseEditarAllRol EdicionRoles(RequestEditarAllRol request) {
        try {
            ResponseEditarAllRol response = rolService.EditarRol(request);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            ResponseEditarAllRol response = new ResponseEditarAllRol();
            response.setExito(false);
            response.setMessage(e.getMessage());
            return response;
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al editar el rol: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            ResponseEditarAllRol response = new ResponseEditarAllRol();
            response.setExito(false);
            response.setMessage(mensajeError);
            return response;
        }
    }
}
