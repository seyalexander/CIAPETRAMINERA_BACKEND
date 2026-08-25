package com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.request;

import lombok.Data;

@Data
public class RequestEditarAllConductor {
    private long idConductor;
    private long idTransportista;
    private String nombres;
    private String apellidos;
    private long idTipoDocumento;
    private String documento;
    private String licencia;
    private String telefono;
    private int estado;
}
