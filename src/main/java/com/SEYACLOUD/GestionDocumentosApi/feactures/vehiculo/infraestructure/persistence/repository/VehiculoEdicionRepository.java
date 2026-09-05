package com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.infraestructure.persistence.repository;

import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.response.ResponseEditarAllConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.response.ResponseEditarEstadoConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.request.RequestEditarAllVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.request.RequestEditarEstadoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.response.ResponseEditarAllVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.response.ResponseEditarEstadoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.domain.interfaces.IVehiculoEdicion;
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
public class VehiculoEdicionRepository implements IVehiculoEdicion {

    @Autowired
    @Qualifier("SQLSERVER")
    private DataSource con;


    @Override
    public ResponseEditarAllVehiculo editarAllVehiculo(RequestEditarAllVehiculo request) {
        ResponseEditarAllVehiculo rpt = new ResponseEditarAllVehiculo();

        String SQL = "{ call OPERACIONES.sp_EditarVehiculo(?,?,?,?,?,?,?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setLong(1, request.getIdVehiculo());
            pstmt.setLong(2, request.getIdCliente());
            pstmt.setLong(3, request.getIdTransportista());
            pstmt.setString(4, request.getPlaca());
            pstmt.setString(5, request.getMarca());
            pstmt.setString(6, request.getColor());
            pstmt.setFloat(7, request.getCapacidadToneladas());
            pstmt.setInt(8, request.getEstado());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                rpt.setExito(true);
                rpt.setMessage("Vehículo actualizado correctamente.");
            } else {
                rpt.setExito(false);
                rpt.setMessage("No se actualizó el vehículo.");
            }

        } catch (SQLException e) {
            rpt.setExito(false);
            rpt.setMessage(e.getMessage());
        }

        return rpt;
    }

    @Override
    public ResponseEditarEstadoVehiculo editarEstadoVehiculo(RequestEditarEstadoVehiculo request, int estado, long idUserAutenticado) {
        ResponseEditarEstadoVehiculo rpt = new ResponseEditarEstadoVehiculo();

        String SQL = "{ call OPERACIONES.sp_EditarVehiculo_Estado(?,?,?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setLong(1, request.getIdVehiculo());
            pstmt.setInt(2, estado);
            pstmt.setLong(3, idUserAutenticado);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                rpt.setExito(true);
                rpt.setMessage("Vehículo actualizado correctamente.");
            } else {
                rpt.setExito(false);
                rpt.setMessage("No se actualizó el vehículo.");
            }

        } catch (SQLException e) {
            rpt.setExito(false);
            rpt.setMessage(e.getMessage());
        }

        return rpt;
    }
}
