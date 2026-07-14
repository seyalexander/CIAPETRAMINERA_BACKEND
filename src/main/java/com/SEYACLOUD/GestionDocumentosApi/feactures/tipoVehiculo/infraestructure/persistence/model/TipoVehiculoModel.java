package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.infraestructure.persistence.model;

import com.SEYACLOUD.GestionDocumentosApi.common.model.AuditableModel;
import lombok.Data;

@Data
public class TipoVehiculoModel extends AuditableModel {
    private Long idTipoVehiculo;
    private String descripcion;
    private int estado;
}
