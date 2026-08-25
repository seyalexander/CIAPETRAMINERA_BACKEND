package com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.infraestructure.persistence.repository;

import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.request.RequestListaTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.response.ResponseListaTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.domain.interfaces.ITransportistaListado;
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
public class TransportistaListadoRepository implements ITransportistaListado {

    @Autowired
    @Qualifier("SQLSERVER")
    private DataSource con;


    @Override
    public ResponseListaTransportista listaTransportistas(RequestListaTransportista request) {
        ResponseListaTransportista rpt = new ResponseListaTransportista();
        List<TransportistaModel> transportistas = new ArrayList<>();

        String SQL = "{ call SEGURIDAD.sp_ListarUsuario (?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setInt(1, request.getEstado());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                TransportistaModel transportista = new TransportistaModel();

                transportista.setIdTransportista(rs.getLong("idTransportista"));
                transportista.setIdCliente(rs.getLong("idCliente"));
                transportista.setRazonSocial(rs.getString("razonSocial"));
                transportista.setRuc(rs.getString("ruc"));
                transportista.setTelefono(rs.getString("telefono"));
                transportista.setDireccion(rs.getString("direccion"));
                transportista.setContacto(rs.getString("contacto"));
                transportista.setEstado(rs.getInt("estado"));
                transportista.setFechaCreacion(
                        rs.getTimestamp("fechaCreacion") != null
                                ? rs.getTimestamp("fechaCreacion").toLocalDateTime()
                                : null
                );

                transportista.setFechaEdicion(
                        rs.getTimestamp("fechaEdicion") != null
                                ? rs.getTimestamp("fechaEdicion").toLocalDateTime()
                                : null
                );

                transportista.setFechaAnulacion(
                        rs.getTimestamp("fechaAnulacion") != null
                                ? rs.getTimestamp("fechaAnulacion").toLocalDateTime()
                                : null
                );
                transportista.setIdUsuarioCreacion(rs.getLong("idUsuarioCreacion"));
                transportista.setIdUsuarioEdicion(rs.getLong("idUsuarioEdicion"));
                transportista.setIdUsuarioAnulacion(rs.getLong("idUsuarioAnulacion"));

                transportistas.add(transportista);
            }
            rpt.setExito(true);
            rpt.setTransportistas(transportistas);
            rpt.setMessage("Consulta realizada correctamente.");

        } catch (Exception e) {
            rpt.setExito(false);
            rpt.setMessage(e.getMessage());
        }

        return rpt;
    }
}
