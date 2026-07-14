package com.SEYACLOUD.GestionDocumentosApi.feactures.login.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.login.application.dto.request.RequestLogin;
import com.SEYACLOUD.GestionDocumentosApi.feactures.login.application.dto.response.ResponseLogin;

public interface ILogin {
    ResponseLogin Login(RequestLogin request);
}
