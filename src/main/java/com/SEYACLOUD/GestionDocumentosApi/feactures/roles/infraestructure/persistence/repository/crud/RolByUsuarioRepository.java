package com.SEYACLOUD.GestionDocumentosApi.feactures.roles.infraestructure.persistence.repository.crud;

import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.request.RequestRolByUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.response.ResponseDetalleRol;
import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.response.ResponseListaRol;
import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.application.dto.response.ResponseRolByUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.domain.interfaces.IRolUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.roles.infraestructure.persistence.model.RolModel;
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
public class RolByUsuarioRepository implements IRolUsuario {

    @Autowired
    @Qualifier("SQLSERVER")
    private DataSource con;


    @Override
    public ResponseRolByUsuario obtenerRolesPorUsuario(RequestRolByUsuario request) {
        ResponseRolByUsuario rpt = new ResponseRolByUsuario();
        List<RolModel> familias = new ArrayList<>();

        String SQL = "{ call SEGURIDAD.sp_ListarRol (?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setString(1, request.getIdUsuario());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                RolModel rol = new RolModel();

                rol.setIdRol(rs.getLong("idRol"));
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
            rpt.setRol(familias);
            rpt.setMessage("Consulta realizada correctamente.");

        } catch (Exception e) {
            rpt.setExito(false);
            rpt.setMessage(e.getMessage());
        }

        return rpt;
    }
}
