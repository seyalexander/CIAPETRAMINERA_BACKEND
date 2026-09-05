package com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.infraestructure.persistence.repository;

import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.request.RequestEditarAllConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.request.RequestEditarEstadoConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.response.ResponseEditarAllConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.response.ResponseEditarEstadoConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.domain.interfaces.IConductorEdicion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@Repository
@Transactional("sqlServerTransactionManager")
public class ConductorEdicionRepository implements IConductorEdicion {

    @Autowired
    @Qualifier("SQLSERVER")
    private DataSource con;

    @Override
    public ResponseEditarAllConductor editarAllConductor(RequestEditarAllConductor request, long idUserAutenticado) {
        ResponseEditarAllConductor rpt = new ResponseEditarAllConductor();

        String SQL = "{ call OPERACIONES.sp_EditarConductor(?,?,?,?,?,?,?,?,?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setLong(1, request.getIdTransportista());
            pstmt.setLong(2, request.getIdTransportista());
            pstmt.setString(3, request.getNombres());
            pstmt.setString(4, request.getApellidos());
            pstmt.setLong(5, request.getIdTipoDocumento());
            pstmt.setString(6, request.getDocumento());
            pstmt.setString(7, request.getLicencia());
            pstmt.setString(8, request.getTelefono());
            pstmt.setInt(9, request.getEstado());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                rpt.setExito(true);
                rpt.setMessage("Conductor actualizado correctamente.");
            } else {
                rpt.setExito(false);
                rpt.setMessage("No se actualizó el conductor.");
            }

        } catch (SQLException e) {
            rpt.setExito(false);
            rpt.setMessage(e.getMessage());
        }

        return rpt;
    }

    @Override
    public ResponseEditarEstadoConductor editarEstadoConductor(RequestEditarEstadoConductor request, int estado, long idUserAutenticado) {
        ResponseEditarEstadoConductor rpt = new ResponseEditarEstadoConductor();

        String SQL = "{ call OPERACIONES.sp_EditarEstadoConductor(?,?,?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setLong(1, request.getIdConductor());
            pstmt.setInt(2, estado);
            pstmt.setLong(3, idUserAutenticado);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                rpt.setExito(true);
                rpt.setMessage("Conductor actualizado correctamente.");
            } else {
                rpt.setExito(false);
                rpt.setMessage("No se actualizó el Conductor.");
            }

        } catch (SQLException e) {
            rpt.setExito(false);
            rpt.setMessage(e.getMessage());
        }

        return rpt;
    }
}
