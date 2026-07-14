package com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.application.dto.response;
import com.SEYACLOUD.GestionDocumentosApi.common.Response.ResponseGeneral;
import com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.infraestructure.persistence.model.ContactoClienteModel;
import lombok.Data;

@Data
public class ResponseDetalleContactoCliente extends ResponseGeneral {

    private ContactoClienteModel contactoCliente;
}