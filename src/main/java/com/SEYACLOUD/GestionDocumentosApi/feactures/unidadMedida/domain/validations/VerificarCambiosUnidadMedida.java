package com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.domain.validations;

import com.SEYACLOUD.GestionDocumentosApi.common.validations.GlobalVerficarCambios;
import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.application.dto.request.RequestEditarAllUnidadMedida;
import com.SEYACLOUD.GestionDocumentosApi.feactures.unidadMedida.infraestructure.persistence.model.UnidadMedidaModel;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class VerificarCambiosUnidadMedida implements GlobalVerficarCambios<UnidadMedidaModel, RequestEditarAllUnidadMedida> {

    @Override
    public boolean verificarCambios(UnidadMedidaModel modelBD, RequestEditarAllUnidadMedida request) {
        boolean cambioDescripcion =
                !Objects.equals(
                        modelBD.getDescripcion(),
                        request.getDescripcion());

        boolean cambioSiglas =
                !Objects.equals(
                        modelBD.getSiglas(),
                        request.getSiglas());

        boolean cambioEstado =
                modelBD.getEstado() != request.getEstado();

        return cambioDescripcion || cambioSiglas || cambioEstado;

    }

}
