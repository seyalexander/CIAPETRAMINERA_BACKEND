package com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.infraestructure.persistence.repository;

import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.response.ResponseRegistroConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.request.RequestRegistroVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.application.dto.response.ResponseRegistroVehiculo;
import com.SEYACLOUD.GestionDocumentosApi.feactures.vehiculo.domain.interfaces.IVehiculoRegistro;
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
public class VehiculoRegistroRepository implements IVehiculoRegistro {

    @Autowired
    @Qualifier("SQLSERVER")
    private DataSource con;


    @Override
    public ResponseRegistroVehiculo registroVehiculo(RequestRegistroVehiculo request, long idUserAutenticado) {
        ResponseRegistroVehiculo rpt = new ResponseRegistroVehiculo();

        String SQL = "{ call OPERACIONES.sp_RegistroVehiculo(?,?,?,?,?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setLong(1, request.getIdCliente());
            pstmt.setLong(1, request.getIdTransportista());
            pstmt.setString(2, request.getPlaca());
            pstmt.setString(3, request.getMarca());
            pstmt.setString(5, request.getColor());
            pstmt.setFloat(4, request.getCapacidadToneladas());
            pstmt.setLong(7, idUserAutenticado);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                rpt.setExito(true);
                rpt.setMessage("Vehiculo insertado correctamente.");
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
