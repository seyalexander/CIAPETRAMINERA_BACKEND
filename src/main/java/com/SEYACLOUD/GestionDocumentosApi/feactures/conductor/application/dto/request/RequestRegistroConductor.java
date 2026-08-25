package com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.request;

import lombok.Data;

@Data
public class RequestRegistroConductor {
    private long idTransportista;
    private String nombres;
    private String apellidos;
    private long idTipoDocumento;
    private String documento;
    private String licencia;
    private String telefono;
}
