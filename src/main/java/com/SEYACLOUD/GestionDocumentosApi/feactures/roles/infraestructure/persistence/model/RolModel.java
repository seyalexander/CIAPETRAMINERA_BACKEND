package com.SEYACLOUD.GestionDocumentosApi.feactures.roles.infraestructure.persistence.model;

import com.SEYACLOUD.GestionDocumentosApi.common.model.AuditableModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class RolModel extends AuditableModel implements Serializable {
    private long idRol;
    private String descripcion;
    private int estado;
}
