package com.SEYACLOUD.GestionDocumentosApi.common.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


@Slf4j
@Component
public class PasswordEncryption {

    private static final String ALGORITHM = "AES";
    private static final int KEY_SIZE = 256;

    @Value("${app.security.password-seed:DBPERUSYSTEM}")
    private String passwordSeed;

    public String encrypt(String passwordToEncrypt) {
        try {
            if (passwordToEncrypt == null || passwordToEncrypt.isEmpty()) {
                throw new IllegalArgumentException("La contraseña no puede estar vacía");
            }

            log.debug("[encrypt] Iniciando encriptación de contraseña");

            SecretKey secretKey = generateKeyFromSeed(passwordSeed);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encryptedPassword = cipher.doFinal(passwordToEncrypt.getBytes());
            log.debug("[encrypt] Cifrado AES-256 completado ({} bytes)", encryptedPassword.length);

            String encryptedPasswordBase64 = Base64.getEncoder().encodeToString(encryptedPassword);
            log.debug("[encrypt] Codificación Base64 completada");

            log.info("[encrypt] Contraseña encriptada exitosamente");
            return encryptedPasswordBase64;

        } catch (IllegalArgumentException e) {
            log.warn("[encrypt] Validación fallida: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("[encrypt] Error crítico al encriptar: {}", e.getMessage(), e);
            throw new RuntimeException("Error al encriptar la contraseña: " + e.getMessage());
        }
    }

    public String decrypt(String encryptedPassword) {
        try {
            // VALIDACIÓN: Evitar desencriptación de datos inválidos
//            if (encryptedPassword == null || encryptedPassword.isEmpty()) {
//                throw new IllegalArgumentException("La contraseña encriptada no puede estar vacía");
//            }

            if (encryptedPassword == null) {
                throw new IllegalArgumentException("La contraseña encriptada no puede ser null");
            }

            log.debug("[decrypt] Iniciando desencriptación de contraseña");

            SecretKey secretKey = generateKeyFromSeed(passwordSeed);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] decodedPassword = Base64.getDecoder().decode(encryptedPassword);
            log.debug("[decrypt] Decodificación Base64 completada ({} bytes)", decodedPassword.length);

            byte[] decryptedPassword = cipher.doFinal(decodedPassword);
            log.debug("[decrypt] Descifrado AES-256 completado");

            String resultado = new String(decryptedPassword);
//            log.info("[decrypt] Contraseña desencriptada exitosamente");
            return resultado;

        } catch (IllegalArgumentException e) {
            // Errores de validación
            log.warn("[decrypt] Validación fallida: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("[decrypt] Error crítico al desencriptar: {}", e.getMessage(), e);
            throw new RuntimeException("Error al desencriptar la contraseña: " + e.getMessage());
        }
    }

    private SecretKey generateKeyFromSeed(String seed) throws Exception {
        log.debug("[generateKeyFromSeed] Generando clave AES-256 desde semilla");

        byte[] seedBytes = new byte[32];

        byte[] seedData = seed.getBytes();
        log.debug("[generateKeyFromSeed] Semilla convertida a {} bytes", seedData.length);

        System.arraycopy(seedData, 0, seedBytes, 0, Math.min(seedData.length, seedBytes.length));
        log.debug("[generateKeyFromSeed] Copya de semilla a array de clave completada");

        // PASO 4 & 5: Crear SecretKeySpec e imediatamente retornarla
        // SecretKeySpec(bytes, offset, longitud, algoritmo)
        // Crea una clave que implementa SecretKey para algoritmo AES
        SecretKey clave = new SecretKeySpec(seedBytes, 0, seedBytes.length, ALGORITHM);
        log.debug("[generateKeyFromSeed] Clave AES-256 generada exitosamente");

        return clave;
    }

    public String getCurrentSeed() {
        return passwordSeed;
    }
}
