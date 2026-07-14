package com.SEYACLOUD.GestionDocumentosApi.feactures.login.application.useCase;

import com.SEYACLOUD.GestionDocumentosApi.common.Response.ResponseErrorLogin;
import com.SEYACLOUD.GestionDocumentosApi.feactures.login.application.dto.request.RequestLogin;
import com.SEYACLOUD.GestionDocumentosApi.feactures.login.application.dto.response.ResponseLogin;
import com.SEYACLOUD.GestionDocumentosApi.feactures.login.domain.services.LoginService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginUseCase {

    @Autowired
    private final LoginService loginService;
    private final ResponseErrorLogin responseErrorLogin;

    private LoginUseCase (
            LoginService loginService,
            ResponseErrorLogin responseErrorLogin
    ) {
        this.loginService = loginService;
        this.responseErrorLogin = responseErrorLogin;
    }

    public ResponseLogin ejecutar(RequestLogin request) {
        try {
            ResponseLogin response = loginService.Login(request);
            if(response.isExito()){}

            return response;

        }catch (IllegalArgumentException | SecurityException e){
            return responseErrorLogin.crearResponseLoginError(e.getMessage());
        }
        catch (Exception e){
            String mensajeError = "Error inesperado al ingresar el login: " + e.getMessage();
            System.err.println("[ERROR] " + mensajeError);
            return responseErrorLogin.crearResponseLoginError(mensajeError);
        }
    }
}
