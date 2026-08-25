package com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.infraestructure.persistence.model;

import com.SEYACLOUD.GestionDocumentosApi.common.model.AuditableModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class ContactoClienteModel extends AuditableModel implements Serializable {
    private long idContactoCliente;
    private long idCliente;
    private String nombreContacto;
    private String telefono;
    private String email;
    private int estado;
}
