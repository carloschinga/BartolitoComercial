package com.ejemplo.jwtlogin.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.jwtlogin.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        try {
            String username = loginData.get("username");
            String password = loginData.get("password");

            String jsonString = authService.login(username, password);

            // Parsear el JSON string a objeto
            JSONObject jsonData = new JSONObject(jsonString);

            // Convertir a Map para que Spring lo serialice correctamente
            Map<String, Object> response = new HashMap<>();
            for (String key : jsonData.keySet()) {
                response.put(key, jsonData.get(key));
            }

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(Collections.singletonMap("error", "Credenciales inválidas"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", "Error interno del servidor"));
        }
    }

    @PostMapping("/loginBartolito")
    public ResponseEntity<?> loginBartolito(@RequestBody Map<String, String> loginData) {
        try {
            String username = loginData.get("username");
            String hexPassword = loginData.get("password");

            // Convertir el password a representación hexadecimal y agregar prefijo 0x
            String hexEncodedPassword = stringToHexWithPrefix(hexPassword);

            // Remove 0x prefix if present and convert hex to bytes
            if (hexEncodedPassword.startsWith("0x")) {
                hexEncodedPassword = hexEncodedPassword.substring(2);
            }

            // Convertir a bytes
            byte[] password = hexStringToByteArray(hexEncodedPassword);

            // Log para verificar el formato correcto
            System.out.println("Contraseña original: " + hexPassword);
            System.out.println("Contraseña convertida: " + hexEncodedPassword);

            String jsonString = authService.loginBartolito(username, password);

            // Parsear el JSON string a objeto
            JSONObject jsonData = new JSONObject(jsonString);

            // Convertir a Map para que Spring lo serialice correctamente
            Map<String, Object> response = new HashMap<>();
            for (String key : jsonData.keySet()) {
                response.put(key, jsonData.get(key));
            }

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(Collections.singletonMap("error", "Credenciales inválidas"));
        } catch (RuntimeException e) {
            // Log de error
            System.err.println("Error en loginBartolito: " + e.getMessage());
            return ResponseEntity.status(500).body(
                    Collections.singletonMap("error", "Error durante el proceso de autenticación: " + e.getMessage()));
        } catch (Exception e) {
            // Log de error
            System.err.println("Error inesperado: " + e.getMessage());
            return ResponseEntity.status(500).body(Collections.singletonMap("error", "Error interno del servidor"));
        }
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

    @PostMapping("/loginBartolitoByUsername")
    public ResponseEntity<?> loginBartolitoByUsername(@RequestBody Map<String, String> loginData) {
        try {
            String username = loginData.get("username");

            String jsonString = authService.loginBartolitoByUser(username);

            // Parsear el JSON string a objeto
            JSONObject jsonData = new JSONObject(jsonString);

            // Convertir a Map para que Spring lo serialice correctamente
            Map<String, Object> response = new HashMap<>();
            for (String key : jsonData.keySet()) {
                response.put(key, jsonData.get(key));
            }

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(Collections.singletonMap("error", "Credenciales inválidas"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", "Error interno del servidor"));
        }
    }

    @PostMapping("/loginInventarioByUsername")
    public ResponseEntity<?> loginInventarioByUsername(@RequestBody Map<String, String> loginData) {
        try {
            String username = loginData.get("username");

            String jsonString = authService.loginInventarioByUser(username);

            // Parsear el JSON string a objeto
            JSONObject jsonData = new JSONObject(jsonString);

            // Convertir a Map para que Spring lo serialice correctamente
            Map<String, Object> response = new HashMap<>();
            for (String key : jsonData.keySet()) {
                response.put(key, jsonData.get(key));
            }

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(Collections.singletonMap("error", "Credenciales inválidas"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", "Error interno del servidor"));
        }
    }

    @PostMapping("/loginByUsername")
    public ResponseEntity<?> loginByUsername(@RequestBody Map<String, String> loginData) {
        try {
            String username = loginData.get("username");

            String jsonString = authService.loginByUser(username);

            // Parsear el JSON string a objeto
            JSONObject jsonData = new JSONObject(jsonString);

            // Convertir a Map para que Spring lo serialice correctamente
            Map<String, Object> response = new HashMap<>();
            for (String key : jsonData.keySet()) {
                response.put(key, jsonData.get(key));
            }

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(Collections.singletonMap("error", "Credenciales inválidas"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", "Error interno del servidor"));
        }
    }

     @PostMapping("/loginInventario")
    public ResponseEntity<?> loginInventario(@RequestBody Map<String, String> loginData) {
        try {
            String username = loginData.get("username");
            String hexPassword = loginData.get("password");

            // Convertir el password a representación hexadecimal y agregar prefijo 0x
            String hexEncodedPassword = stringToHexWithPrefix(hexPassword);

            // Remove 0x prefix if present and convert hex to bytes
            if (hexEncodedPassword.startsWith("0x")) {
                hexEncodedPassword = hexEncodedPassword.substring(2);
            }

            // Convertir a bytes
            byte[] password = hexStringToByteArray(hexEncodedPassword);

            // Log para verificar el formato correcto
            System.out.println("Contraseña original: " + hexPassword);
            System.out.println("Contraseña convertida: " + hexEncodedPassword);

            String jsonString = authService.loginInventario(username, password);

            // Parsear el JSON string a objeto
            JSONObject jsonData = new JSONObject(jsonString);

            // Convertir a Map para que Spring lo serialice correctamente
            Map<String, Object> response = new HashMap<>();
            for (String key : jsonData.keySet()) {
                response.put(key, jsonData.get(key));
            }

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(Collections.singletonMap("error", "Credenciales inválidas"));
        } catch (RuntimeException e) {
            // Log de error
            System.err.println("Error en loginBartolito: " + e.getMessage());
            return ResponseEntity.status(500).body(
                    Collections.singletonMap("error", "Error durante el proceso de autenticación: " + e.getMessage()));
        } catch (Exception e) {
            // Log de error
            System.err.println("Error inesperado: " + e.getMessage());
            return ResponseEntity.status(500).body(Collections.singletonMap("error", "Error interno del servidor"));
        }
    }
}