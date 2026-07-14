package com.SEYACLOUD.GestionDocumentosApi.feactures.auditoria.infraestructure.persistence.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AuditoriaModel implements Serializable {

    private long idUsuario;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime fechaAccion;
    private String usuario;
    private String modulo;
    private String accion;
    private String documento;
    private String ip;

}
