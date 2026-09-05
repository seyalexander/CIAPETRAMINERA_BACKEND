package com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.request.RequestEditarAllConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.request.RequestEditarEstadoConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.response.ResponseEditarAllConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.response.ResponseEditarEstadoConductor;

public interface IConductorEdicion {
    ResponseEditarAllConductor editarAllConductor(RequestEditarAllConductor request, long idUserAutenticado);
    ResponseEditarEstadoConductor editarEstadoConductor(RequestEditarEstadoConductor request, int estado, long idUserAutenticado);
}
