package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.infraestructure.persistence.repository;

import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.request.RequestDetalleTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.response.ResponseDetalleTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.domain.interfaces.ITipoVehiculoDetalle;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.infraestructure.persistence.model.TipoVehiculoModel;
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
public class TipoVehiculoDetalleRepository implements ITipoVehiculoDetalle {

    @Autowired
    @Qualifier("SQLSERVER")
    private DataSource con;


    @Override
    public ResponseDetalleTipoVehiculo DetalleTipoVehiculo(RequestDetalleTipoVehiculo request) {
        ResponseDetalleTipoVehiculo response = new ResponseDetalleTipoVehiculo();
        TipoVehiculoModel objeto = null;

        String SQL = "{ call OPERACIONES.sp_DetalleTipoVehiculos(?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setLong(1, request.getIdTipoVehiculo());

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    objeto = new TipoVehiculoModel();
                    objeto.setIdTipoVehiculo(rs.getLong("idTipoVehiculo"));
                    objeto.setDescripcion(rs.getString("descripcion"));
                    objeto.setEstado(rs.getInt("estado"));
                    objeto.setFechaCreacion(
                            rs.getTimestamp("fechaCreacion") != null
                                    ? rs.getTimestamp("fechaCreacion").toLocalDateTime()
                                    : null
                    );

                    objeto.setFechaEdicion(
                            rs.getTimestamp("fechaEdicion") != null
                                    ? rs.getTimestamp("fechaEdicion").toLocalDateTime()
                                    : null
                    );

                    objeto.setFechaAnulacion(
                            rs.getTimestamp("fechaAnulacion") != null
                                    ? rs.getTimestamp("fechaAnulacion").toLocalDateTime()
                                    : null
                    );
                    objeto.setIdUsuarioCreacion(rs.getLong("idUsuarioCreacion"));
                    objeto.setIdUsuarioEdicion(rs.getLong("idUsuarioEdicion"));
                    objeto.setIdUsuarioAnulacion(rs.getLong("idUsuarioAnulacion"));

                    response.setExito(true);
                    response.setMessage("Rol obtenido correctamente");
                    response.setTipoVehiculo(objeto);

                } else {
                    response.setExito(false);
                    response.setMessage("No se encontró el tipo vehículo");
                }
            }

        } catch (SQLException e) {
            response.setExito(false);
            response.setMessage("Error al obtener el Tipo Vehiculo");
            log.error("Error en SEGURIDAD.sp_ObtenerTipoVehiculo", e);
        }

        return response;
    }
}
