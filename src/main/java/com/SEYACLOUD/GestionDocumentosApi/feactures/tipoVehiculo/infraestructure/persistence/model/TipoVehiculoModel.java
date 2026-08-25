package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.infraestructure.persistence.model;

import com.SEYACLOUD.GestionDocumentosApi.common.model.AuditableModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class TipoVehiculoModel extends AuditableModel implements Serializable {
    private Long idTipoVehiculo;
    private String descripcion;
    private int estado;
}
