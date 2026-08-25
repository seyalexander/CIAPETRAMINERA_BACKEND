package com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.request.RequestRegistroTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.response.ResponseRegistroTransportista;

public interface ITransportistaRegistro {
    ResponseRegistroTransportista registroTransportista(RequestRegistroTransportista request, long idUserAutenticado);
}
