package com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.request.RequestEditarAllTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.request.RequestEditarEstadoTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.response.ResponseEditarAllTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.response.ResponseEditarEstadoTransportista;

public interface ITransportistaEdicion {
    ResponseEditarAllTransportista editarAllTransportista(RequestEditarAllTransportista request);
    ResponseEditarEstadoTransportista editarEstadoTransportisa(RequestEditarEstadoTransportista request, int estado, long idUserAutenticado);
}
