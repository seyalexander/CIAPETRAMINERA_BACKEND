package com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.infraestructure.persistence.repository;

import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.request.RequestRegistroTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.response.ResponseRegistroTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.domain.interfaces.ITransportistaRegistro;
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
public class TransportistaRegistroRepository implements ITransportistaRegistro {

    @Autowired
    @Qualifier("SQLSERVER")
    private DataSource con;


    @Override
    public ResponseRegistroTransportista registroTransportista(RequestRegistroTransportista request, long idUserAutenticado) {
        ResponseRegistroTransportista rpt = new ResponseRegistroTransportista();

        String SQL = "{ call SEGURIDAD.sp_RegistroUsuario(?,?,?,?,?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setLong(1, request.getIdCliente());
            pstmt.setString(2, request.getRazonSocial());
            pstmt.setString(3, request.getRuc());
            pstmt.setString(4, request.getTelefono());
            pstmt.setString(5, request.getDireccion());
            pstmt.setString(6, request.getContacto());
            pstmt.setLong(7, idUserAutenticado);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                rpt.setExito(true);
                rpt.setMessage("Transportista insertado correctamente.");
            } else {
                rpt.setExito(false);
                rpt.setMessage("No se insertó el Transportista.");
            }

        } catch (SQLException e) {
            rpt.setExito(false);
            rpt.setMessage(e.getMessage());
        }

        return rpt;
    }
}
