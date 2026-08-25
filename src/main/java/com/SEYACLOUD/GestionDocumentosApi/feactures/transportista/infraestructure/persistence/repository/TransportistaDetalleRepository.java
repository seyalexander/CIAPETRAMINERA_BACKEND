package com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.infraestructure.persistence.repository;

import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.request.RequestDetalleTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.application.dto.response.ResponseDetalleTransportista;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.domain.interfaces.ITransportistaDetalle;
import com.SEYACLOUD.GestionDocumentosApi.feactures.transportista.infraestructure.persistence.model.TransportistaModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Repository
@Transactional("sqlServerTransactionManager")
public class TransportistaDetalleRepository implements ITransportistaDetalle {

    @Autowired
    @Qualifier("SQLSERVER")
    private DataSource con;


    @Override
    public ResponseDetalleTransportista detalleTransportista(RequestDetalleTransportista request) {
        ResponseDetalleTransportista response = new ResponseDetalleTransportista();
        TransportistaModel transportista = null;

        String SQL = "{ call SEGURIDAD.sp_ObtenerUsuarioPorId(?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setLong(1, request.getIdTransportista());

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
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

                    transportista.setUsuarioCreacion(rs.getString("usuarioCreacion"));
                    transportista.setUsuarioEdicion(rs.getString("usuarioEdicion"));
                    transportista.setUsuarioAnulacion(rs.getString("usuarioAnulacion"));


                    response.setExito(true);
                    response.setMessage("Transportista obtenido correctamente");
                    response.setTransportista(transportista);

                } else {
                    response.setExito(false);
                    response.setMessage("No se encontró al usuario");
                }
            }

        } catch (SQLException e) {
            response.setExito(false);
            response.setMessage("Error al obtener el detalle del usuario: " + e.getMessage());
            log.error("Error en SEGURIDAD.sp_ObtenerUsuarioPorId", e);
        }

        return response;
    }
}
