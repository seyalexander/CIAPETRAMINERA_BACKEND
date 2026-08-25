package com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.request.RequestListaTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.response.ResponseListaTransportista;

public interface ITransportistaListado {
    ResponseListaTransportista listaTransportistas(RequestListaTransportista request);
}
