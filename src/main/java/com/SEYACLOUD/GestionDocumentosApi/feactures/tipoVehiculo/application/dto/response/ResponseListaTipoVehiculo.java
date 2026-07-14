package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.response;

import com.SEYACLOUD.GestionDocumentosApi.common.Response.ResponseGeneral;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.infraestructure.persistence.model.TipoVehiculoModel;
import lombok.Data;

import java.util.List;

@Data
public class ResponseListaTipoVehiculo extends ResponseGeneral {
    private List<TipoVehiculoModel> tipoVehiculos;
}
