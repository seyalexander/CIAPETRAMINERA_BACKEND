package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.infraestructure.persistence.repository.crud;


import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.request.RequestListaTipoClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.application.dto.response.ResponseListaTipoClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.domain.interfaces.ITipoClientesListado;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoClientes.infraestructure.persistence.model.TipoClientesModel;
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
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@Transactional("sqlServerTransactionManager")
public class TipoClientesListadoRepository implements ITipoClientesListado {

    @Autowired
    @Qualifier("SQLSERVER")
    private DataSource con;

    @Override
    public ResponseListaTipoClientes ListaTipoClientes(RequestListaTipoClientes request) {
        ResponseListaTipoClientes rpt = new ResponseListaTipoClientes();
        List<TipoClientesModel> registros = new ArrayList<>();
        String SQL = "{ call CLIENTES.sp_ListarTipoCliente(?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setInt(1, request.getEstado());

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    TipoClientesModel item = new TipoClientesModel();
                    item.setIdTipoCliente(rs.getLong("idTipoCliente"));
                    item.setDescripcion(rs.getString("descripcion"));
                    item.setEstado(rs.getInt("estado"));
                    item.setFechaCreacion(
                            rs.getTimestamp("fechaCreacion") != null
                                    ? rs.getTimestamp("fechaCreacion").toLocalDateTime()
                                    : null
                    );

                    item.setFechaEdicion(
                            rs.getTimestamp("fechaEdicion") != null
                                    ? rs.getTimestamp("fechaEdicion").toLocalDateTime()
                                    : null
                    );

                    item.setFechaAnulacion(
                            rs.getTimestamp("fechaAnulacion") != null
                                    ? rs.getTimestamp("fechaAnulacion").toLocalDateTime()
                                    : null
                    );
                    item.setIdUsuarioCreacion(rs.getLong("idUsuarioCreacion"));
                    item.setIdUsuarioEdicion(rs.getLong("idUsuarioEdicion"));
                    item.setIdUsuarioAnulacion(rs.getLong("idUsuarioAnulacion"));
                    registros.add(item);
                }
            }

            rpt.setExito(true);
            rpt.setTipoClientes(registros);
            rpt.setMessage("Consulta realizada correctamente.");
        } catch (SQLException e) {
            rpt.setExito(false);
            rpt.setMessage(e.getMessage());
            log.error("Error en CONFIGURACION.sp_ListarTipoClientes", e);
        }
        return rpt;
    }


}
