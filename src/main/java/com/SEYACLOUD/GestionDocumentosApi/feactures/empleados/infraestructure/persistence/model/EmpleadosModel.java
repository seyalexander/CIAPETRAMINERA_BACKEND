package com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.infraestructure.persistence.model;

import com.SEYACLOUD.GestionDocumentosApi.common.model.AuditableModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class EmpleadosModel extends AuditableModel implements Serializable {
    private long idEmpleado;
    private String nombre;
    private String apellido;
    private String telefono;
    private String imagenUrl;
    private long idTipoDocumento;
    private String tipOocumento;
    private String documento;
    private String fechaNacimiento;
    private String fechaIngreso;
    private int estado;
}
