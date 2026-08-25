package com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.infraestructure.persistence.model;

import com.SEYACLOUD.GestionDocumentosApi.common.model.AuditableModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class TransportistaModel extends AuditableModel implements Serializable {
    private long idTransportista;
    private long idCliente;
    private String razonSocial;
    private String ruc;
    private String telefono;
    private String direccion;
    private String contacto;
    private int estado;
}
