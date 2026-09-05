package com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.infraestructure.persistence.repository;

import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.request.RequestRegistroConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.response.ResponseRegistroConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.domain.interfaces.IConductorRegistro;
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
public class ConductorRegistroRepository implements IConductorRegistro {

    @Autowired
    @Qualifier("SQLSERVER")
    private DataSource con;


    @Override
    public ResponseRegistroConductor registroConductor(RequestRegistroConductor request, long idUserAutenticado) {
        ResponseRegistroConductor rpt = new ResponseRegistroConductor();

        String SQL = "{ call OPERACIONES.sp_RegistroConductor(?,?,?,?,?,?,?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setLong(1, request.getIdTransportista());
            pstmt.setString(2, request.getNombres());
            pstmt.setString(3, request.getApellidos());
            pstmt.setLong(4, request.getIdTipoDocumento());
            pstmt.setString(5, request.getDocumento());
            pstmt.setString(6, request.getLicencia());
            pstmt.setString(4, request.getTelefono());
            pstmt.setLong(7, idUserAutenticado);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                rpt.setExito(true);
                rpt.setMessage("Conductor insertado correctamente.");
            } else {
                rpt.setExito(false);
                rpt.setMessage("No se insertó al conductor.");
            }

        } catch (SQLException e) {
            rpt.setExito(false);
            rpt.setMessage(e.getMessage());
        }

        return rpt;
    }
}
