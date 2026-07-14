package com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.response;

import com.SEYACLOUD.GestionDocumentosApi.common.Response.ResponseGeneral;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.infraestructure.persistence.model.VehiculoModel;
import lombok.Data;

import java.util.List;

@Data
public class ResponseListaVehiculo extends ResponseGeneral {
    private List<VehiculoModel> vehiculos;
}
