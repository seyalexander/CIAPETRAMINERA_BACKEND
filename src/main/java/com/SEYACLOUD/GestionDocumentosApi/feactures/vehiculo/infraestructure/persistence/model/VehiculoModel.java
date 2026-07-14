package com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.infraestructure.persistence.model;

import com.SEYACLOUD.GestionDocumentosApi.common.model.AuditableModel;
import lombok.Data;

@Data
public class VehiculoModel extends AuditableModel {
    private Long idVehiculo;
    private Long idCliente;
    private Long idTransportista;
    private String placa;
    private String marca;
    private String color;
    private float capacidadToneladas;
    private int estado;
}
