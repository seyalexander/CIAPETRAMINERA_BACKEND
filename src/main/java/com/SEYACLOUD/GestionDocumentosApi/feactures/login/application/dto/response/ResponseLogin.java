package com.SEYACLOUD.GestionDocumentosApi.feactures.login.application.dto.response;

import com.SEYACLOUD.GestionDocumentosApi.common.Response.ResponseGeneral;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResponseLogin extends ResponseGeneral {
    private String usuario;
    private String token;
    private String nombre;
    private String permiso;
    private String descripcionRol;
}
