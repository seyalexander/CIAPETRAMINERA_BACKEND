package com.SEYACLOUD.GestionDocumentosApi.feactures.EmpresaClientes.infraestructure.persistence.repository;

import com.SEYACLOUD.GestionDocumentosApi.feactures.EmpresaClientes.application.dto.request.RequestListaEmpresaClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.EmpresaClientes.application.dto.response.ResponseListaEmpresaClientes;
import com.SEYACLOUD.GestionDocumentosApi.feactures.EmpresaClientes.domain.interfaces.IEmpresaClientesListado;
import com.SEYACLOUD.GestionDocumentosApi.feactures.EmpresaClientes.infraestructure.persistence.model.EmpresaClientesModel;
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
public class EmpresaClientesListadoRepository implements IEmpresaClientesListado {

    @Autowired
    @Qualifier("SQLSERVER")
    private DataSource con;


    @Override
    public ResponseListaEmpresaClientes ListaEmpresaClientes(RequestListaEmpresaClientes request) {
        ResponseListaEmpresaClientes rpt = new ResponseListaEmpresaClientes();
        List<EmpresaClientesModel> empresaClientes = new ArrayList<>();

        String SQL = "{ call CLIENTES.sp_ListarEmpresaClientes (?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setInt(1, request.getEstado());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                EmpresaClientesModel empresa = new EmpresaClientesModel();

                empresa.setIdEmpresa(rs.getLong("idRol"));
                empresa.setImagenUrl(rs.getString("imagenUrl"));
                empresa.setRazonSocial(rs.getString("razonSocial"));
                empresa.setRuc(rs.getString("ruc"));
                empresa.setDireccion(rs.getString("direccion"));
                empresa.setTelefono(rs.getString("telefono"));
                empresa.setEmail(rs.getString("email"));
                empresa.setLogoUrl(rs.getString("logoUrl"));
                empresa.setDescripcion(rs.getString("descripcion"));
                empresa.setEstado(rs.getInt("estado"));
                empresa.setFechaCreacion(
                        rs.getTimestamp("fechaCreacion") != null
                                ? rs.getTimestamp("fechaCreacion").toLocalDateTime()
                                : null
                );

                empresa.setFechaEdicion(
                        rs.getTimestamp("fechaEdicion") != null
                                ? rs.getTimestamp("fechaEdicion").toLocalDateTime()
                                : null
                );

                empresa.setFechaAnulacion(
                        rs.getTimestamp("fechaAnulacion") != null
                                ? rs.getTimestamp("fechaAnulacion").toLocalDateTime()
                                : null
                );
                empresa.setIdUsuarioCreacion(rs.getLong("idUsuarioCreacion"));
                empresa.setIdUsuarioEdicion(rs.getLong("idUsuarioEdicion"));
                empresa.setIdUsuarioAnulacion(rs.getLong("idUsuarioAnulacion"));

                empresaClientes.add(empresa);
            }
            rpt.setExito(true);
            rpt.setEmpresaClientes(empresaClientes);
            rpt.setMessage("Consulta realizada correctamente.");

        } catch (Exception e) {
            rpt.setExito(false);
            rpt.setMessage(e.getMessage());
        }

        return rpt;
    }
}
