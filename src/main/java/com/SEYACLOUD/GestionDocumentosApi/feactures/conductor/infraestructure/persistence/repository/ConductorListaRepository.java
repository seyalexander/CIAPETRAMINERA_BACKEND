package com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.infraestructure.persistence.repository;

import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.request.RequestListaConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.application.dto.response.ResponseListaConductor;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.domain.interfaces.IConductorLista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.conductor.infraestructure.persistence.model.ConductorModel;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.response.ResponseListaTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.infraestructure.persistence.model.TransportistaModel;
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
public class ConductorListaRepository implements IConductorLista {

    @Autowired
    @Qualifier("SQLSERVER")
    private DataSource con;


    @Override
    public ResponseListaConductor listaConductores(RequestListaConductor request) {
        ResponseListaConductor rpt = new ResponseListaConductor();
        List<ConductorModel> conductores = new ArrayList<>();

        String SQL = "{ call SEGURIDAD.sp_ListarUsuario (?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setInt(1, request.getEstado());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ConductorModel conductor = new ConductorModel();

                conductor.setIdConductor(rs.getLong("idTransportista"));
                conductor.setIdTransportista(rs.getLong("idCliente"));
                conductor.setNombres(rs.getString("razonSocial"));
                conductor.setApellidos(rs.getString("ruc"));
                conductor.setIdTipoDocumento(rs.getLong("idCliente"));
                conductor.setDocumento(rs.getString("ruc"));
                conductor.setLicencia(rs.getString("ruc"));
                conductor.setTelefono(rs.getString("telefono"));
                conductor.setEstado(rs.getInt("estado"));
                conductor.setFechaCreacion(
                        rs.getTimestamp("fechaCreacion") != null
                                ? rs.getTimestamp("fechaCreacion").toLocalDateTime()
                                : null
                );

                conductor.setFechaEdicion(
                        rs.getTimestamp("fechaEdicion") != null
                                ? rs.getTimestamp("fechaEdicion").toLocalDateTime()
                                : null
                );

                conductor.setFechaAnulacion(
                        rs.getTimestamp("fechaAnulacion") != null
                                ? rs.getTimestamp("fechaAnulacion").toLocalDateTime()
                                : null
                );
                conductor.setIdUsuarioCreacion(rs.getLong("idUsuarioCreacion"));
                conductor.setIdUsuarioEdicion(rs.getLong("idUsuarioEdicion"));
                conductor.setIdUsuarioAnulacion(rs.getLong("idUsuarioAnulacion"));

                conductores.add(conductor);
            }
            rpt.setExito(true);
            rpt.setConductores(conductores);
            rpt.setMessage("Consulta realizada correctamente.");

        } catch (Exception e) {
            rpt.setExito(false);
            rpt.setMessage(e.getMessage());
        }

        return rpt;
    }
}
