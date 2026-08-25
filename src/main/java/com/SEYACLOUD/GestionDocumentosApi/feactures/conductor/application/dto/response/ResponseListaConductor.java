package com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.response;

import com.SEYACLOUD.GestionDocumentosApi.common.Response.ResponseGeneral;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.infraestructure.persistence.model.ConductorModel;
import lombok.Data;

import java.util.List;

@Data
public class ResponseListaConductor extends ResponseGeneral {
    private List<ConductorModel> conductores;
}
