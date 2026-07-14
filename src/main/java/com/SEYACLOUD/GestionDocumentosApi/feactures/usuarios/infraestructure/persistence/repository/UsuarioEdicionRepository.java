package com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.infraestructure.persistence.repository;

import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.request.RequestEditarAllUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.request.RequestEditarEstadoUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.response.ResponseEditarAllUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.response.ResponseEditarEstadoUsuario;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.domain.interfaces.IUsuarioEdicion;
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
public class UsuarioEdicionRepository implements IUsuarioEdicion {

    @Autowired
    @Qualifier("SQLSERVER")
    private DataSource con;


    @Override
    public ResponseEditarAllUsuario EditarUsuario(RequestEditarAllUsuario request,long idUserAutenticado) {
        ResponseEditarAllUsuario rpt = new ResponseEditarAllUsuario();

        String SQL = "{ call SEGURIDAD.sp_EditarUsuario(?,?,?,?,?,?,?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setLong(1, request.getIdUsuario());
            pstmt.setString(2, request.getUsuario());
            pstmt.setString(3, request.getPassowrd());
            pstmt.setInt(4, request.getEstado());
            pstmt.setLong(5, request.getIdRol());
            pstmt.setLong(6, request.getIdEmpleado());
            pstmt.setLong(7, idUserAutenticado);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                rpt.setExito(true);
                rpt.setMessage("Empleado actualizado correctamente.");
            } else {
                rpt.setExito(false);
                rpt.setMessage("No se actualizó el empleado.");
            }

        } catch (SQLException e) {
            rpt.setExito(false);
            rpt.setMessage(e.getMessage());
        }

        return rpt;
    }

    @Override
    public ResponseEditarEstadoUsuario EditarEstadoUsuario(RequestEditarEstadoUsuario request, int estado, long idUserAutenticado) {
        ResponseEditarEstadoUsuario rpt = new ResponseEditarEstadoUsuario();

        String SQL = "{ call SEGURIDAD.sp_EditarUsuario_Estado(?,?,?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setLong(1, request.getIdUsuario());
            pstmt.setInt(2, estado);
            pstmt.setLong(3, idUserAutenticado);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                rpt.setExito(true);
                rpt.setMessage("Usuario actualizado correctamente.");
            } else {
                rpt.setExito(false);
                rpt.setMessage("No se actualizó el usuario.");
            }

        } catch (SQLException e) {
            rpt.setExito(false);
            rpt.setMessage(e.getMessage());
        }

        return rpt;
    }
}
