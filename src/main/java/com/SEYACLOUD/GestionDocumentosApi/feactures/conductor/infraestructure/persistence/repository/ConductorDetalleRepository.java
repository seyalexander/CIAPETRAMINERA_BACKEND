package com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.infraestructure.persistence.repository;

import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.request.RequestDetalleConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.response.ResponseDetalleConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.domain.interfaces.IConductorDetalle;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.infraestructure.persistence.model.ConductorModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Repository
@Transactional("sqlServerTransactionManager")
public class ConductorDetalleRepository implements IConductorDetalle {

    @Autowired
    @Qualifier("SQLSERVER")
    private DataSource con;


    @Override
    public ResponseDetalleConductor detalleConductor(RequestDetalleConductor request) {
        ResponseDetalleConductor response = new ResponseDetalleConductor();
        ConductorModel conductor = null;

        String SQL = "{ call SEGURIDAD.sp_ObtenerUsuarioPorId(?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setLong(1, request.getIdConductor());

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    conductor.setIdConductor(rs.getLong("idTransportista"));
                    conductor.setIdTransportista(rs.getLong("idCliente"));
                    conductor.setNombres(rs.getString("razonSocial"));
                    conductor.setApellidos(rs.getString("ruc"));
                    conductor.setIdTipoDocumento(rs.getLong("idCliente"));
                    conductor.setDocumento(rs.getString("ruc"));
                    conductor.setLicencia(rs.getString("ruc"));
                    conductor.setTelefono(rs.getString("telefono"));
                    conductor.setEstado(rs.getInt("estado"));
                    conductor.setFechaCreacion(
                            rs.getTimestamp("fechaCreacion") != null
                                    ? rs.getTimestamp("fechaCreacion").toLocalDateTime()
                                    : null
                    );

                    conductor.setFechaEdicion(
                            rs.getTimestamp("fechaEdicion") != null
                                    ? rs.getTimestamp("fechaEdicion").toLocalDateTime()
                                    : null
                    );

                    conductor.setFechaAnulacion(
                            rs.getTimestamp("fechaAnulacion") != null
                                    ? rs.getTimestamp("fechaAnulacion").toLocalDateTime()
                                    : null
                    );
                    conductor.setIdUsuarioCreacion(rs.getLong("idUsuarioCreacion"));
                    conductor.setIdUsuarioEdicion(rs.getLong("idUsuarioEdicion"));
                    conductor.setIdUsuarioAnulacion(rs.getLong("idUsuarioAnulacion"));


                    response.setExito(true);
                    response.setMessage("Conductor obtenido correctamente");
                    response.setConductor(conductor);

                } else {
                    response.setExito(false);
                    response.setMessage("No se encontró al Conductor");
                }
            }

        } catch (SQLException e) {
            response.setExito(false);
            response.setMessage("Error al obtener el detalle del conductor: " + e.getMessage());
            log.error("Error en SEGURIDAD.sp_ObtenerUsuarioPorId", e);
        }

        return response;
    }
}
