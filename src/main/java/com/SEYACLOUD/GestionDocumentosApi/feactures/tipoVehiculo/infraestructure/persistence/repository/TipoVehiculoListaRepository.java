package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.infraestructure.persistence.repository;

import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.request.RequestListaTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.response.ResponseListaTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.domain.interfaces.ITipoVehiculoLista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.infraestructure.persistence.model.TipoVehiculoModel;
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
public class TipoVehiculoListaRepository implements ITipoVehiculoLista {

    @Autowired
    @Qualifier("SQLSERVER")
    private DataSource con;


    @Override
    public ResponseListaTipoVehiculo ListaTipoVehiculos(RequestListaTipoVehiculo request) {
        ResponseListaTipoVehiculo rpt = new ResponseListaTipoVehiculo();
        List<TipoVehiculoModel> familias = new ArrayList<>();

        String SQL = "{ call OPERACIONES.sp_ListarTipoVehiculos (?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setInt(1, request.getEstado());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                TipoVehiculoModel rol = new TipoVehiculoModel();

                rol.setIdTipoVehiculo(rs.getLong("idTipoVehiculo"));
                rol.setDescripcion(rs.getString("descripcion"));
                rol.setEstado(rs.getInt("estado"));
                rol.setFechaCreacion(
                        rs.getTimestamp("fechaCreacion") != null
                                ? rs.getTimestamp("fechaCreacion").toLocalDateTime()
                                : null
                );

                rol.setFechaEdicion(
                        rs.getTimestamp("fechaEdicion") != null
                                ? rs.getTimestamp("fechaEdicion").toLocalDateTime()
                                : null
                );

                rol.setFechaAnulacion(
                        rs.getTimestamp("fechaAnulacion") != null
                                ? rs.getTimestamp("fechaAnulacion").toLocalDateTime()
                                : null
                );
                rol.setIdUsuarioCreacion(rs.getLong("idUsuarioCreacion"));
                rol.setIdUsuarioEdicion(rs.getLong("idUsuarioEdicion"));
                rol.setIdUsuarioAnulacion(rs.getLong("idUsuarioAnulacion"));

                familias.add(rol);
            }
            rpt.setExito(true);
            rpt.setTipoVehiculos(familias);
            rpt.setMessage("Consulta realizada correctamente.");

        } catch (Exception e) {
            rpt.setExito(false);
            rpt.setMessage(e.getMessage());
        }

        return rpt;
    }
}
