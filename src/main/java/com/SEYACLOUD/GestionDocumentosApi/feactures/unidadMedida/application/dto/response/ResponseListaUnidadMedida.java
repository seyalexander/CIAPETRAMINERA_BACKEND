package com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.application.dto.response;

import com.SEYACLOUD.GestionDocumentosApi.common.Response.ResponseGeneral;
import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.infraestructure.persistence.model.UnidadMedidaModel;
import lombok.Data;

import java.util.List;

@Data
public class ResponseListaUnidadMedida extends ResponseGeneral {
    private List<UnidadMedidaModel> unidadesMedida;
}
