package com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.response;

import com.SEYACLOUD.GestionDocumentosApi.common.Response.ResponseGeneral;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.infraestructure.persistence.model.TransportistaModel;
import lombok.Data;

import java.util.List;

@Data
public class ResponseListaTransportista extends ResponseGeneral {
    private List<TransportistaModel> transportistas;
}
