package com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.domain.interfaces;

import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.request.RequestDetalleTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.response.ResponseDetalleTransportista;

public interface ITransportistaDetalle {
    ResponseDetalleTransportista detalleTransportista(RequestDetalleTransportista request);
}
