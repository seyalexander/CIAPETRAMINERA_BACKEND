package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.infraestructure.persistence.model;

import com.SEYACLOUD.GestionDocumentosApi.common.model.AuditableModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class TipoDocumentoModel extends AuditableModel implements Serializable {
    private long idTipoDocumentos;
    private String descripcion;
    private int estado;
    private int longitudMin;
    private int longitudMax;
    private String codigoSunat;
    private int tipoCaracter;
    private String descripcionTipoCaracter;

}
