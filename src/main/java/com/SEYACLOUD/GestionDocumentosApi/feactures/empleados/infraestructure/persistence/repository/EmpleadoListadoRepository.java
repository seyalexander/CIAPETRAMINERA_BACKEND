package com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.infraestructure.persistence.repository;

import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.request.RequestListaEmpleados;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.application.dto.response.ResponseListaEmpleados;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.domain.interfaces.IEmpleadoListado;
import com.SEYACLOUD.GestionDocumentosApi.feactures.empleados.infraestructure.persistence.model.EmpleadosModel;
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
public class EmpleadoListadoRepository implements IEmpleadoListado {

    @Autowired
    @Qualifier("SQLSERVER")
    private DataSource con;

    @Override
    public ResponseListaEmpleados ListaEmpleado(RequestListaEmpleados request) {
        ResponseListaEmpleados rpt = new ResponseListaEmpleados();
        List<EmpleadosModel> empleados = new ArrayList<>();

        String SQL = "{ call SEGURIDAD.sp_ListarEmpleado (?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setInt(1, request.getEstado());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                EmpleadosModel empleado = new EmpleadosModel();

                empleado.setIdEmpleado(rs.getLong("idEmpleado"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido(rs.getString("apellido"));
                empleado.setEstado(rs.getInt("estado"));
                empleado.setTelefono(rs.getString("telefono"));
                empleado.setImagenUrl(rs.getString("imagenUrl"));
                empleado.setIdTipoDocumento(rs.getLong("id_tipoDocumento"));
                empleado.setTipOocumento(rs.getString("tipoDocumento"));
                empleado.setDocumento(rs.getString("documento"));
                empleado.setFechaCreacion(
                        rs.getTimestamp("fechaCreacion") != null
                                ? rs.getTimestamp("fechaCreacion").toLocalDateTime()
                                : null
                );

                empleado.setFechaEdicion(
                        rs.getTimestamp("fechaEdicion") != null
                                ? rs.getTimestamp("fechaEdicion").toLocalDateTime()
                                : null
                );

                empleado.setFechaAnulacion(
                        rs.getTimestamp("fechaAnulacion") != null
                                ? rs.getTimestamp("fechaAnulacion").toLocalDateTime()
                                : null
                );
                empleado.setIdUsuarioCreacion(rs.getLong("idUsuarioCreacion"));
                empleado.setIdUsuarioEdicion(rs.getLong("idUsuarioEdicion"));
                empleado.setIdUsuarioAnulacion(rs.getLong("idUsuarioAnulacion"));

                empleados.add(empleado);
            }
            rpt.setExito(true);
            rpt.setEmpleados(empleados);
            rpt.setMessage("Consulta realizada correctamente.");

        } catch (Exception e) {
            rpt.setExito(false);
            rpt.setMessage(e.getMessage());
        }

        return rpt;
    }
}
