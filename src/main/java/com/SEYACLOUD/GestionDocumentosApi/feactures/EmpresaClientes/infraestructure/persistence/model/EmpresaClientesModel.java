package com.SEYACLOUD.GestionDocumentosApi.feactures.EmpresaClientes.infraestructure.persistence.model;

import com.SEYACLOUD.GestionDocumentosApi.common.model.AuditableModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class EmpresaClientesModel extends AuditableModel implements Serializable {
    private long idEmpresa;
    private String imagenUrl;
    private String razonSocial;
    private String ruc;
    private String direccion;
    private String telefono;
    private String email;
    private String logoUrl;
    private String descripcion;
    private int estado;
}
