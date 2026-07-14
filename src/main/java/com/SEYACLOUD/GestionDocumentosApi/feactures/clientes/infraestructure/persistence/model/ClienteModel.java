package com.SEYACLOUD.GestionDocumentosApi.feactures.clientes.infraestructure.persistence.model;

import com.SEYACLOUD.GestionDocumentosApi.common.model.AuditableModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class ClienteModel  extends AuditableModel implements Serializable {
    private long idCliente;
    private String nombres;
    private String apellidos;
    private String razonSocial;
    private String numeroDocumento;
    private long idTipoDocumento;
    private long idTipoCliente;
    private String telefono;
    private String email;
    private int estado;
}
