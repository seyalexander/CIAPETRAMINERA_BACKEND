package com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.infraestructure.persistence.model;

import com.SEYACLOUD.GestionDocumentosApi.common.model.AuditableModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UsuariosModel extends AuditableModel implements Serializable {
    private long idUsuario;
    private Long idEmpresa;
    private String usuario;
    private String passowrd;
    private int estado;
    private long idRol;
    private String descripcionRol;
    private long idEmpleado;
    private String nombre;
    private String apellido;
}
