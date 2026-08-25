package com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.infraestructure.persistence.model;

import com.SEYACLOUD.GestionDocumentosApi.common.model.AuditableModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class ConductorModel extends AuditableModel implements Serializable {
    private long idConductor;
    private long idTransportista;
    private String nombres;
    private String apellidos;
    private long idTipoDocumento;
    private String documento;
    private String licencia;
    private String telefono;
    private int estado;
}
