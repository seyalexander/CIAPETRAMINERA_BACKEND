package com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.response;

import com.SEYACLOUD.GestionDocumentosApi.common.Response.ResponseGeneral;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.infraestructure.persistence.model.VehiculoModel;
import lombok.Data;

@Data
public class ResponseDetalleVehiculo extends ResponseGeneral {
    private VehiculoModel vehiculo;
}
