package com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.request;

import lombok.Data;

@Data
public class RequestRegistroVehiculo {
    private Long idCliente;
    private Long idTransportista;
    private String placa;
    private String marca;
    private String color;
    private float capacidadToneladas;
}
