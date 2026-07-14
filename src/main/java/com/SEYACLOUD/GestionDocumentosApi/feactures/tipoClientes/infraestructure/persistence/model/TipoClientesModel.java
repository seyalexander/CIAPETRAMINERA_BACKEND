package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.infraestructure.persistence.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TipoClientesModel implements Serializable {
    private long idTipoCliente;
    private String descripcion;
    private int estado;
}