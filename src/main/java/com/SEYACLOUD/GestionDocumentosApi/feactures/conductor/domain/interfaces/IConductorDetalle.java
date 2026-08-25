package com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.request.RequestDetalleConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.response.ResponseDetalleConductor;

public interface IConductorDetalle {
    ResponseDetalleConductor detalleConductor(RequestDetalleConductor request);
}
