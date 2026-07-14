package com.SEYACLOUD.GestionDocumentosApi.common.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGeneral implements Serializable {
    private boolean exito;
    private String message;
    private Map<String, String> errores;
}
