package com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.infraestructure.persistence.repository;

import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.request.RequestDetalleVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.response.ResponseDetalleVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.domain.interfaces.IVehiculoDetalle;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.infraestructure.persistence.model.VehiculoModel;
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
public class VehiculoDetalleRepository implements IVehiculoDetalle {

    @Autowired
    @Qualifier("SQLSERVER")
    private DataSource con;


    @Override
    public ResponseDetalleVehiculo detalleVehiculo(RequestDetalleVehiculo request) {
        ResponseDetalleVehiculo response = new ResponseDetalleVehiculo();
        VehiculoModel vehiculo = null;

        String SQL = "{ call SEGURIDAD.sp_ObtenerUsuarioPorId(?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setLong(1, request.getIdVehiculo());

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    vehiculo.setIdVehiculo(rs.getLong("idTransportista"));
                    vehiculo.setIdCliente(rs.getLong("idTransportista"));
                    vehiculo.setIdTransportista(rs.getLong("idCliente"));
                    vehiculo.setPlaca(rs.getString("razonSocial"));
                    vehiculo.setMarca(rs.getString("ruc"));
                    vehiculo.setColor(rs.getString("ruc"));
                    vehiculo.setCapacidadToneladas(rs.getFloat("ruc"));
                    vehiculo.setEstado(rs.getInt("estado"));
                    vehiculo.setFechaCreacion(
                            rs.getTimestamp("fechaCreacion") != null
                                    ? rs.getTimestamp("fechaCreacion").toLocalDateTime()
                                    : null
                    );

                    vehiculo.setFechaEdicion(
                            rs.getTimestamp("fechaEdicion") != null
                                    ? rs.getTimestamp("fechaEdicion").toLocalDateTime()
                                    : null
                    );

                    vehiculo.setFechaAnulacion(
                            rs.getTimestamp("fechaAnulacion") != null
                                    ? rs.getTimestamp("fechaAnulacion").toLocalDateTime()
                                    : null
                    );
                    vehiculo.setIdUsuarioCreacion(rs.getLong("idUsuarioCreacion"));
                    vehiculo.setIdUsuarioEdicion(rs.getLong("idUsuarioEdicion"));
                    vehiculo.setIdUsuarioAnulacion(rs.getLong("idUsuarioAnulacion"));


                    response.setExito(true);
                    response.setMessage("Vehículo obtenido correctamente");
                    response.setVehiculo(vehiculo);

                } else {
                    response.setExito(false);
                    response.setMessage("No se encontró al Vehículo");
                }
            }

        } catch (SQLException e) {
            response.setExito(false);
            response.setMessage("Error al obtener el detalle del Vehículo: " + e.getMessage());
            log.error("Error en SEGURIDAD.sp_ObtenerUsuarioPorId", e);
        }

        return response;
    }
}
