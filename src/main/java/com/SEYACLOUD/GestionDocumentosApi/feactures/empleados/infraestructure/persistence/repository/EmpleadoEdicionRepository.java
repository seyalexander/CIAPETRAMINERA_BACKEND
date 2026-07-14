package com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.infraestructure.persistence.repository;

import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.request.RequestEdicionAllEmpleados;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.request.RequestEdicionEstadoEmpleados;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.response.ResponseEdicionAllEmpleados;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.response.ResponseEdicionEstadoEmpleados;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.domain.interfaces.IEmpleadoEdicion;
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
public class EmpleadoEdicionRepository implements IEmpleadoEdicion {

    @Autowired
    @Qualifier("SQLSERVER")
    private DataSource con;


    @Override
    public ResponseEdicionAllEmpleados EditarAllEmpleado(RequestEdicionAllEmpleados request, long userAutenticado) {
        ResponseEdicionAllEmpleados rpt = new ResponseEdicionAllEmpleados();

        String SQL = "{ call SEGURIDAD.sp_EditarEmpleado(?,?,?,?,?,?,?,?,?,?,?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setLong(1, request.getIdEmpleado());
            pstmt.setString(2, request.getNombre());
            pstmt.setString(3, request.getApellido());
            pstmt.setString(4, request.getTelefono());
            pstmt.setString(5, request.getImagenUrl());
            pstmt.setInt(6, request.getEstado());
            pstmt.setString(7, request.getDocumento());
            pstmt.setLong(8, request.getIdTipoDocumento());
            pstmt.setString(9, request.getFechaIngreso());
            pstmt.setString(10, request.getFechaIngreso());
            pstmt.setLong(11, userAutenticado);

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
    public ResponseEdicionEstadoEmpleados EditarEstadoEmpleado(RequestEdicionEstadoEmpleados request, int estado, long userAutenticado) {
        ResponseEdicionEstadoEmpleados rpt = new ResponseEdicionEstadoEmpleados();

        String SQL = "{ call SEGURIDAD.sp_EditarEmpleado_Estado(?,?,?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setLong(1, request.getIdEmpleado());
            pstmt.setInt(2, estado);
            pstmt.setLong(3, userAutenticado);

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
}
