package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.infraestructure.persistence.repository;

import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.dto.request.RequestListaTipoDocumento;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.application.dto.response.ResponseListaTipoDocumento;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.domain.interfaces.ITipoDocumentoListado;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoDocumentos.infraestructure.persistence.model.TipoDocumentoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional("sqlServerTransactionManager")
public class TipoDocumentoListadoRepository implements ITipoDocumentoListado {

    @Autowired
    @Qualifier("SQLSERVER")
    private DataSource con;

    @Override
    public ResponseListaTipoDocumento ListaTipoDocumento(RequestListaTipoDocumento request, long userAutenticado) {
        ResponseListaTipoDocumento rpt = new ResponseListaTipoDocumento();
        List<TipoDocumentoModel> tipoDocumentos = new ArrayList<>();

        String SQL = "{ call CONFIGURACION.sp_ListarTipoDocumentos (?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setInt(1, request.getEstado());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                TipoDocumentoModel tipoDocumento = new TipoDocumentoModel();

                tipoDocumento.setIdTipoDocumentos(rs.getLong("idTipoDocumentos"));
                tipoDocumento.setDescripcion(rs.getString("descripcion"));
                tipoDocumento.setEstado(rs.getInt("estado"));
                tipoDocumento.setFechaCreacion(
                        rs.getTimestamp("fechaCreacion") != null
                                ? rs.getTimestamp("fechaCreacion").toLocalDateTime()
                                : null
                );

                tipoDocumento.setFechaEdicion(
                        rs.getTimestamp("fechaEdicion") != null
                                ? rs.getTimestamp("fechaEdicion").toLocalDateTime()
                                : null
                );

                tipoDocumento.setFechaAnulacion(
                        rs.getTimestamp("fechaAnulacion") != null
                                ? rs.getTimestamp("fechaAnulacion").toLocalDateTime()
                                : null
                );
                tipoDocumento.setIdUsuarioCreacion(rs.getLong("idUsuarioCreacion"));
                tipoDocumento.setIdUsuarioEdicion(rs.getLong("idUsuarioEdicion"));
                tipoDocumento.setIdUsuarioAnulacion(rs.getLong("idUsuarioAnulacion"));
                tipoDocumento.setLongitudMin(rs.getInt("longitudMin"));
                tipoDocumento.setLongitudMax(rs.getInt("longitudMax"));
                tipoDocumento.setCodigoSunat(rs.getString("codigoSunat"));
                tipoDocumento.setTipoCaracter(rs.getInt("tipoCaracter"));
                tipoDocumento.setDescripcionTipoCaracter(rs.getString("descripcionTipoCaracter"));

                tipoDocumentos.add(tipoDocumento);
            }
            rpt.setExito(true);
            rpt.setTipoDocumentos(tipoDocumentos);
            rpt.setMessage("Consulta realizada correctamente.");

        } catch (Exception e) {
            rpt.setExito(false);
            rpt.setMessage(e.getMessage());
        }

        return rpt;
    }
}
