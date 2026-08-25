package com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.infraestructure.persistence.repository;

import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.request.RequestEditarAllTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.request.RequestEditarEstadoTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.response.ResponseEditarAllTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.response.ResponseEditarEstadoTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.domain.interfaces.ITransportistaEdicion;
import com.SEYACLOUD.GestionDocumentosApi.feactures.usuarios.application.dto.response.ResponseEditarEstadoUsuario;
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
public class TransportistaEdicionRepository implements ITransportistaEdicion {

    @Autowired
    @Qualifier("SQLSERVER")
    private DataSource con;


    @Override
    public ResponseEditarAllTransportista editarAllTransportista(RequestEditarAllTransportista request) {
        ResponseEditarAllTransportista rpt = new ResponseEditarAllTransportista();

        String SQL = "{ call SEGURIDAD.sp_EditarUsuario(?,?,?,?,?,?,?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setLong(1, request.getIdTransportista());
            pstmt.setLong(2, request.getIdCliente());
            pstmt.setString(3, request.getRazonSocial());
            pstmt.setString(4, request.getRuc());
            pstmt.setString(5, request.getTelefono());
            pstmt.setString(6, request.getDireccion());
            pstmt.setString(7, request.getDireccion());
            pstmt.setInt(8, request.getEstado());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                rpt.setExito(true);
                rpt.setMessage("Transportista actualizado correctamente.");
            } else {
                rpt.setExito(false);
                rpt.setMessage("No se actualizó el transportista.");
            }

        } catch (SQLException e) {
            rpt.setExito(false);
            rpt.setMessage(e.getMessage());
        }

        return rpt;
    }

    @Override
    public ResponseEditarEstadoTransportista editarEstadoTransportisa(RequestEditarEstadoTransportista request, int estado, long idUserAutenticado) {
        ResponseEditarEstadoTransportista rpt = new ResponseEditarEstadoTransportista();

        String SQL = "{ call SEGURIDAD.sp_EditarUsuario_Estado(?,?,?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setLong(1, request.getIdTransportista());
            pstmt.setInt(2, estado);
            pstmt.setLong(3, idUserAutenticado);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                rpt.setExito(true);
                rpt.setMessage("Transportista actualizado correctamente.");
            } else {
                rpt.setExito(false);
                rpt.setMessage("No se actualizó el transportista.");
            }

        } catch (SQLException e) {
            rpt.setExito(false);
            rpt.setMessage(e.getMessage());
        }

        return rpt;
    }
}
