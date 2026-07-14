package com.SEYACLOUD.GestionDocumentosApi.feactures.login.infraestructure.persistence;

import com.SEYACLOUD.GestionDocumentosApi.common.security.PasswordEncryption;
import com.SEYACLOUD.GestionDocumentosApi.common.security.JwtTokenProvider; // <-- Importamos tu proveedor
import com.SEYACLOUD.GestionDocumentosApi.feactures.login.application.dto.request.RequestLogin;
import com.SEYACLOUD.GestionDocumentosApi.feactures.login.application.dto.response.ResponseLogin;
import com.SEYACLOUD.GestionDocumentosApi.feactures.login.domain.interfaces.ILogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

@Repository
@Transactional
@Slf4j
public class LoginRepository implements ILogin {

    @Autowired
    private DataSource con;

    @Autowired
    private PasswordEncryption passwordEncryption;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public ResponseLogin Login(RequestLogin request) {
        ResponseLogin usuario = null;

        String SQL = "{ call SEGURIDAD.sp_Login(?, ?) }";

        try (Connection conn = con.getConnection();
             CallableStatement pstmt = conn.prepareCall(SQL)) {

            pstmt.setString(1, request.getUsuario());
            String claveEncriptada = passwordEncryption.encrypt(request.getClave());
            pstmt.setString(2, claveEncriptada);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                usuario = new ResponseLogin();
                usuario.setExito(true);

                String idUsuario = rs.getString("idUsuario");
                String nombre = rs.getString("nombre");
                String descripcionRol =rs.getString("descripcionRol");

                usuario.setUsuario(idUsuario);
                usuario.setNombre(nombre);
                usuario.setDescripcionRol(descripcionRol);

                String token = jwtTokenProvider.generateToken(idUsuario, nombre, "", descripcionRol );

                usuario.setToken(token);
                log.info("login exitoso para: " + usuario.getNombre());
            } else {
                usuario = new ResponseLogin();
                usuario.setExito(false);
                usuario.setMessage("Usuario o contraseña incorrectos.");
            }
        } catch (Exception e) {
            log.error("Error al validar credenciales: ", e);
        }

        return usuario;
    }
}