package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.infraestructure.persistence.repository;

import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.request.RequestEditarAllTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.request.RequestEditarEstadoTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.response.ResponseEditarAllTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.response.ResponseEditarEstadoTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.domain.interfaces.ITipoVehiculoEdicion;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.response.ResponseEditarEstadoUsuario;
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
public class TipoVehiculoEditarRepository implements ITipoVehiculoEdicion {

    @Autowired
    @Qualifier("SQLSERVER")
    private DataSource con;

    @Override
    public ResponseEditarAllTipoVehiculo EditarAllTipoVehiculo(RequestEditarAllTipoVehiculo request) {
        ResponseEditarAllTipoVehiculo rpt = new ResponseEditarAllTipoVehiculo();
        String SQL = "{ call SEGURIDAD.sp_EditarTipoVehiculo(?,?,?,?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setLong(1, request.getIdTipoVehiculo());
            pstmt.setString(2, request.getDescripcion());
            pstmt.setInt(3, request.getEstado());
            Long userId = 1L;
            pstmt.setLong(4, userId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                rpt.setExito(true);
                rpt.setMessage("Rol actualizado correctamente.");
            } else {
                rpt.setExito(false);
                rpt.setMessage("No se actualizó Rol.");
            }
        } catch (SQLException e) {
            rpt.setExito(false);
            rpt.setMessage(e.getMessage());
            log.error("Error en SEGURIDAD.sp_EditarRol", e);
        }
        return rpt;
    }

    @Override
    public ResponseEditarEstadoTipoVehiculo EditarEstadoTipoVehiculo(RequestEditarEstadoTipoVehiculo request, int estado, long idUserAutenticado) {
        ResponseEditarEstadoTipoVehiculo rpt = new ResponseEditarEstadoTipoVehiculo();

        String SQL = "{ call OPERACIONES.sp_EditarEstadoTipoVehiculos(?,?,?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setLong(1, request.getIdTipoVehiculo());
            pstmt.setInt(2, estado);
            pstmt.setLong(3, idUserAutenticado);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                rpt.setExito(true);
                rpt.setMessage("Tipo Vehículo actualizado correctamente.");
            } else {
                rpt.setExito(false);
                rpt.setMessage("No se actualizó el Tipo Vehículo.");
            }

        } catch (SQLException e) {
            rpt.setExito(false);
            rpt.setMessage(e.getMessage());
        }

        return rpt;
    }

}
