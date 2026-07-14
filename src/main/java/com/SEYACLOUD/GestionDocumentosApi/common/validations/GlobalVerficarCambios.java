package com.SEYACLOUD.GestionDocumentosApi.common.validations;

public interface GlobalVerficarCambios<MODEL_BD,REQUEST>{

     boolean verificarCambios(MODEL_BD modeloBD, REQUEST request);
}
