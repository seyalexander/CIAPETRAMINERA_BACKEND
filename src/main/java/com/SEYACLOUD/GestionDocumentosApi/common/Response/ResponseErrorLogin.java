package com.SEYACLOUD.GestionDocumentosApi.common.Response;

import com.SEYACLOUD.GestionDocumentosApi.feactures.login.application.dto.response.ResponseLogin;
import org.springframework.stereotype.Component;

@Component
public class ResponseErrorLogin {

    public ResponseLogin crearResponseLoginError(String mensaje) {
        ResponseLogin response = new ResponseLogin();
        response.setExito(false);
        response.setMessage(mensaje);
        return response;
    }

}
