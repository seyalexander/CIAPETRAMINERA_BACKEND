package com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.infraestructure.persistence.repository;

import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.request.RequestListaVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.response.ResponseListaVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.domain.interfaces.IVehiculoListado;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.infraestructure.persistence.model.VehiculoModel;
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
public class VehiculoListaRepository implements IVehiculoListado {

    @Autowired
    @Qualifier("SQLSERVER")
    private DataSource con;


    @Override
    public ResponseListaVehiculo ListaVehiculos(RequestListaVehiculo request) {
        ResponseListaVehiculo rpt = new ResponseListaVehiculo();
        List<VehiculoModel> vehiculos = new ArrayList<>();

        String SQL = "{ call OPERACIONES.sp_ListarVehiculo (?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setInt(1, request.getEstado());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                VehiculoModel vehiculo = new VehiculoModel();

                vehiculo.setIdVehiculo(rs.getLong("idVehiculo"));
                vehiculo.setIdCliente(rs.getLong("idCliente"));
                vehiculo.setIdTransportista(rs.getLong("idTransportista"));
                vehiculo.setPlaca(rs.getString("placa"));
                vehiculo.setMarca(rs.getString("marca"));
                vehiculo.setColor(rs.getString("color"));
                vehiculo.setCapacidadToneladas(rs.getFloat("capacidadToneladas"));
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

                vehiculos.add(vehiculo);
            }
            rpt.setExito(true);
            rpt.setVehiculos(vehiculos);
            rpt.setMessage("Consulta realizada correctamente.");

        } catch (Exception e) {
            rpt.setExito(false);
            rpt.setMessage(e.getMessage());
        }

        return rpt;
    }
}
