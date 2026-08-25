package com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.request;

import lombok.Data;

@Data
public class RequestRegistroTransportista {
    private long idCliente;
    private String razonSocial;
    private String ruc;
    private String telefono;
    private String direccion;
    private String contacto;
}
