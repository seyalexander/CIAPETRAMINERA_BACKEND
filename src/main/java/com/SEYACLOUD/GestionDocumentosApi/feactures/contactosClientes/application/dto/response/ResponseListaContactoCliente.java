package com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.application.dto.response;
import com.SEYACLOUD.GestionDocumentosApi.common.Response.ResponseGeneral;
import com.SEYACLOUD.GestionDocumentosApi.feactures.contactosClientes.infraestructure.persistence.model.ContactoClienteModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ResponseListaContactoCliente extends ResponseGeneral implements Serializable {

    private List<ContactoClienteModel> contactoClientes;
}