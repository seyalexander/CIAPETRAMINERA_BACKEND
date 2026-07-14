package com.SEYACLOUD.GestionDocumentosApi.feactures.roles.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.request.RequestRolByUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.response.ResponseRolByUsuario;
import lombok.Data;


public interface IRolUsuario {
    ResponseRolByUsuario obtenerRolesPorUsuario(RequestRolByUsuario request);
}
