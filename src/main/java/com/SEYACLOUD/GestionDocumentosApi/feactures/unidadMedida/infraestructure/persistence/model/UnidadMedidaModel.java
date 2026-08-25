package com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.infraestructure.persistence.model;

import com.SEYACLOUD.GestionDocumentosApi.common.model.AuditableModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class UnidadMedidaModel extends AuditableModel implements Serializable {

    private long idUnidadMedida;
    private String descripcion;
    private String  siglas;
    private int estado;

}
