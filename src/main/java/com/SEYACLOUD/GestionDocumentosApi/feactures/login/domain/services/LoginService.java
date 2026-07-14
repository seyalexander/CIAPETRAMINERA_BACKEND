package com.SEYACLOUD.GestionDocumentosApi.feactures.login.domain.services;

import com.SEYACLOUD.GestionDocumentosApi.feactures.login.application.dto.request.RequestLogin;
import com.SEYACLOUD.GestionDocumentosApi.feactures.login.application.dto.response.ResponseLogin;
import com.SEYACLOUD.GestionDocumentosApi.feactures.login.domain.interfaces.ILogin;
import com.SEYACLOUD.GestionDocumentosApi.feactures.login.infraestructure.persistence.LoginRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginService implements ILogin {

    private final LoginRepository loginRepository;

    private LoginService (LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public ResponseLogin Login(RequestLogin request) {
        return loginRepository.Login(request);
    }
}
