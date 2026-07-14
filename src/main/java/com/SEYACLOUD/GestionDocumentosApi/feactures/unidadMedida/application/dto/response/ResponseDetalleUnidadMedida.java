package com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.application.dto.response;


import com.SEYACLOUD.GestionDocumentosApi.common.Response.ResponseGeneral;
import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.infraestructure.persistence.model.UnidadMedidaModel;
import lombok.Data;

@Data
public class ResponseDetalleUnidadMedida extends ResponseGeneral {

    private UnidadMedidaModel unidadMedida;
}