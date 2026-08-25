package com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.response;

import com.SEYACLOUD.GestionDocumentosApi.common.Response.ResponseGeneral;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.infraestructure.persistence.model.ConductorModel;
import lombok.Data;

@Data
public class ResponseDetalleConductor extends ResponseGeneral {
    private ConductorModel conductor;
}
