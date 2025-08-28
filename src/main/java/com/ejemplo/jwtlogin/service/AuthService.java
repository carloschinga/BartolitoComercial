package com.ejemplo.jwtlogin.service;

import java.nio.charset.StandardCharsets;

import com.ejemplo.jwtlogin.repository.AuthRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.ejemplo.jwtlogin.util.JwtUtil;
@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // ===========================
    // LOGIN NORMAL
    // ===========================
    public String login(String username, String password) {
        String response = authRepository.loginUsuario("N", username, password);

        JSONObject result = new JSONObject(response);
        if (result.isEmpty() || !result.has("usecod")) {
            throw new BadCredentialsException("Credenciales inválidas");
        }

        // Generar access token
        String token = jwtUtil.generateToken(username);

        // Generar refresh token
       // String refreshToken = jwtUtil.generateRefreshToken(username);

        result.put("resultado", "OK");
        result.put("token", token);
       // result.put("refreshToken", refreshToken);

        return result.toString();
    }

    public String loginByUser(String username) {
        String response = authRepository.loginUsuarioByUsername("N", username);

        JSONObject result = new JSONObject(response);
        if (result.isEmpty() || !result.has("usecod")) {
            throw new BadCredentialsException("Usuario no encontrado");
        }

        String token = jwtUtil.generateToken(username);
        result.put("resultado", "OK");
        result.put("token", token);

        return result.toString();
    }

    // ===========================
    // LOGIN BARTOLITO
    // ===========================
    public String loginBartolito(String username, String passwordTexto) {
        try {
            // Convertir la contraseña a bytes tal como espera el procedimiento
            String hexEncoded = stringToHexWithPrefix(passwordTexto);
            if (hexEncoded.startsWith("0x")) {
                hexEncoded = hexEncoded.substring(2);
            }
            byte[] password = hexStringToByteArray(hexEncoded);

            String response = authRepository.loginUsuario2("B", username, password);

            JSONObject result = new JSONObject(response);
            if (result.isEmpty() || !result.has("usecod")) {
                throw new BadCredentialsException("Credenciales inválidas");
            }

            String token = jwtUtil.generateToken(username);
            result.put("resultado", "OK");
            result.put("token", token);

            return result.toString();

        } catch (Exception e) {
            throw new RuntimeException("Error en loginBartolito: " + e.getMessage());
        }
    }

    public String loginBartolitoByUser(String username) {
        String response = authRepository.loginUsuarioByUsername("B", username);

        JSONObject result = new JSONObject(response);
        if (result.isEmpty() || !result.has("usecod")) {
            throw new BadCredentialsException("Usuario Bartolito no encontrado");
        }

        String token = jwtUtil.generateToken(username);
        result.put("resultado", "OK");
        result.put("token", token);

        return result.toString();
    }

    // ===========================
    // LOGIN INVENTARIO
    // ===========================
    public String loginInventario(String username,String passwordTexto) {
        try {
            // Convertir la contraseña a bytes tal como espera el procedimiento
            String hexEncoded = stringToHexWithPrefix(passwordTexto);
            if (hexEncoded.startsWith("0x")) {
                hexEncoded = hexEncoded.substring(2);
            }
            byte[] password = hexStringToByteArray(hexEncoded);

            String response = authRepository.loginUsuario2("I", username, password);

            JSONObject result = new JSONObject(response);
            if (result.isEmpty() || !result.has("usecod")) {
                throw new BadCredentialsException("Credenciales inválidas");
            }

            String token = jwtUtil.generateToken(username);
            result.put("resultado", "OK");
            result.put("token", token);

            return result.toString();

        } catch (Exception e) {
            throw new RuntimeException("Error en loginBartolito: " + e.getMessage());
        }
    }

    public String loginInventarioByUser(String username) {
        String response = authRepository.loginUsuarioByUsername("I", username);

        JSONObject result = new JSONObject(response);
        if (result.isEmpty() || !result.has("usecod")) {
            throw new BadCredentialsException("Usuario Inventario no encontrado");
        }

        String token = jwtUtil.generateToken(username);
        result.put("resultado", "OK");
        result.put("token", token);

        return result.toString();
    }

    // ===========================
    // GET USER FROM TOKEN
    // ===========================
    public JSONObject getUserFromToken(String token) {
        String username = jwtUtil.extractUsername(token);
        String response = authRepository.loginUsuarioByUsername("N", username);

        JSONObject result = new JSONObject(response);
        if (result.isEmpty() || !result.has("usecod")) {
            throw new BadCredentialsException("Token inválido o usuario no encontrado");
        }
        return result;
    }

    public JSONObject getUserFromTokenBartolito(String token) {
        String username = jwtUtil.extractUsername(token);
        String response = authRepository.loginUsuarioByUsername("B", username);

        JSONObject result = new JSONObject(response);
        if (result.isEmpty() || !result.has("usecod")) {
            throw new BadCredentialsException("Token inválido o usuario Bartolito no encontrado");
        }
        return result;
    }

    public JSONObject getUserFromTokenInventario(String token) {
        String username = jwtUtil.extractUsername(token);
        String response = authRepository.loginUsuarioByUsername("I", username);

        JSONObject result = new JSONObject(response);
        if (result.isEmpty() || !result.has("usecod")) {
            throw new BadCredentialsException("Token inválido o usuario Inventario no encontrado");
        }
        return result;
    }


    // Método para convertir string a representación hexadecimal con prefijo 0x
    private static String stringToHexWithPrefix(String input) {
        StringBuilder hexString = new StringBuilder("0x");
        byte[] bytes = input.getBytes();

        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }

    // Helper method to convert hex string to byte array
    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
