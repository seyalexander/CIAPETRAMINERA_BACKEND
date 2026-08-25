package com.SEYACLOUD.GestionDocumentosApi.feactures.choferes.infraestructure.persistence.model;

import com.SEYACLOUD.GestionDocumentosApi.common.model.AuditableModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class ChoferModel extends AuditableModel implements Serializable {
    private long idConductor;
}
