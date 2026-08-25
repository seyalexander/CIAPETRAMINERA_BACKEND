package com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.infraestructure.persistence.repository;

import com.SEYACLOUD.GestionDocumentosApi.common.security.PasswordSecurityService;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.request.RequestDetalleUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.response.ResponseDetalleUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.domain.interfaces.IUsuarioDetalle;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.infraestructure.persistence.model.UsuariosModel;
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
public class UsuarioDetalleRepository implements IUsuarioDetalle {

    @Autowired
    @Qualifier("SQLSERVER")
    private DataSource con;

    private final PasswordSecurityService passwordSecurityService;

    public UsuarioDetalleRepository(
            PasswordSecurityService passwordSecurityService
    ){
        this.passwordSecurityService = passwordSecurityService;
    }


    @Override
    public ResponseDetalleUsuario DetalleUsuario(RequestDetalleUsuario request) {
        ResponseDetalleUsuario response = new ResponseDetalleUsuario();
        UsuariosModel usuario = null;

        String SQL = "{ call SEGURIDAD.sp_ObtenerUsuarioPorId(?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setLong(1, request.getIdUsuario());

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    usuario = new UsuariosModel();
                    usuario.setIdUsuario(rs.getLong("idUsuario"));
                    usuario.setUsuario(rs.getString("usuario"));
                    String passwordEncript = rs.getString("password");
                    String passwordDecrypt =  passwordSecurityService.desencriptarPassword(passwordEncript);
                    usuario.setPassowrd(passwordDecrypt);
                    usuario.setIdEmpleado(rs.getLong("idEmpleado"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellido(rs.getString("apellido"));
                    usuario.setIdRol(rs.getInt("idRol"));
                    usuario.setDescripcionRol(rs.getString("descripcionRol"));
                    usuario.setEstado(rs.getInt("estado"));
                    usuario.setFechaCreacion(
                            rs.getTimestamp("fechaCreacion") != null
                                    ? rs.getTimestamp("fechaCreacion").toLocalDateTime()
                                    : null
                    );

                    usuario.setFechaEdicion(
                            rs.getTimestamp("fechaEdicion") != null
                                    ? rs.getTimestamp("fechaEdicion").toLocalDateTime()
                                    : null
                    );

                    usuario.setFechaAnulacion(
                            rs.getTimestamp("fechaAnulacion") != null
                                    ? rs.getTimestamp("fechaAnulacion").toLocalDateTime()
                                    : null
                    );
                    usuario.setIdUsuarioCreacion(rs.getLong("idUsuarioCreacion"));
                    usuario.setIdUsuarioEdicion(rs.getLong("idUsuarioEdicion"));
                    usuario.setIdUsuarioAnulacion(rs.getLong("idUsuarioAnulacion"));

                    usuario.setUsuarioCreacion(rs.getString("usuarioCreacion"));
                    usuario.setUsuarioEdicion(rs.getString("usuarioEdicion"));
                    usuario.setUsuarioAnulacion(rs.getString("usuarioAnulacion"));


                    response.setExito(true);
                    response.setMessage("Usuario obtenido correctamente");
                    response.setUsuario(usuario);

                } else {
                    response.setExito(false);
                    response.setMessage("No se encontró al usuario");
                }
            }

        } catch (SQLException e) {
            response.setExito(false);
            response.setMessage("Error al obtener el detalle del usuario: " + e.getMessage());
            log.error("Error en SEGURIDAD.sp_ObtenerUsuarioPorId", e);
        }

        return response;
    }
}
