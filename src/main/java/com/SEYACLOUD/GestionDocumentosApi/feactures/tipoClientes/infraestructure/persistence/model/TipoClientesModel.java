package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.infraestructure.persistence.model;

import com.SEYACLOUD.GestionDocumentosApi.common.model.AuditableModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class TipoClientesModel extends AuditableModel implements Serializable {
    private long idTipoCliente;
    private String descripcion;
    private int estado;
}