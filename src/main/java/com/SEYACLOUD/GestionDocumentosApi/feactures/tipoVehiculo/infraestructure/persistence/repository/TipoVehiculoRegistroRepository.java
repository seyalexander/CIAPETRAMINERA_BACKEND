package com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.infraestructure.persistence.repository;

import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.request.RequestRegistroTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.application.dto.response.ResponseRegistroTipoVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.tipoVehiculo.domain.interfaces.ITipoVehiculoRegistro;
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
public class TipoVehiculoRegistroRepository implements ITipoVehiculoRegistro {

    @Autowired
    @Qualifier("SQLSERVER")
    private DataSource con;

    @Override
    public ResponseRegistroTipoVehiculo RegistroTipoVehiculo(RequestRegistroTipoVehiculo request, long idUserAutenticado) {
        ResponseRegistroTipoVehiculo rpt = new ResponseRegistroTipoVehiculo();

        String SQL = "{ call OPERACIONES.sp_RegistroTipoVehiculo(?,?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setString(1, request.getDescripcion());
            pstmt.setLong(2, idUserAutenticado);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                rpt.setExito(true);
                rpt.setMessage("Tipo Vehículo insertado correctamente.");
            } else {
                rpt.setExito(false);
                rpt.setMessage("No se insertó el Tipo Vehículo.");
            }

        } catch (SQLException e) {
            rpt.setExito(false);
            rpt.setMessage(e.getMessage());
        }

        return rpt;
    }
}
