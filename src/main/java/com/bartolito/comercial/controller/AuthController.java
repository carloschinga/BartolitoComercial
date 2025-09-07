package com.bartolito.comercial.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import com.bartolito.comercial.service.AuthService;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // ===========================
    // LOGIN NORMAL
    // ===========================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        try {
            String username = loginData.get("username");
            String password = loginData.get("password");

            // Llamamos al servicio que devuelve JSON con access y refresh tokens
            String response = authService.login(username, password);

            // Convertimos el string JSON a Map para devolverlo como ResponseEntity
            return ResponseEntity.ok(new JSONObject(response).toMap());

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401)
                    .body(Collections.singletonMap("error", "Credenciales inválidas"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body(Collections.singletonMap("error", "Error interno del servidor"));
        }
    }

    @PostMapping("/loginByUsername")
    public ResponseEntity<?> loginByUsername(@RequestBody Map<String, String> loginData) {
        try {
            String username = loginData.get("username");

            String response = authService.loginByUser(username);
            return ResponseEntity.ok(new JSONObject(response).toMap());

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401)
                    .body(Collections.singletonMap("error", "Usuario no encontrado"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body(Collections.singletonMap("error", "Error interno del servidor"));
        }
    }

    // ===========================
    // LOGIN BARTOLITO  CAMBIOS
    // ===========================
    @PostMapping("/loginBartolito")
    public ResponseEntity<?> loginBartolito(@RequestBody Map<String, String> loginData) {
        try {
            String username = loginData.get("username");
            String password = loginData.get("password"); // Texto plano

            // Llamada al servicio (servicio se encarga de la conversión a byte[])
            String jsonString = authService.loginBartolito(username, password);

            JSONObject jsonData = new JSONObject(jsonString);

            Map<String, Object> response = new HashMap<>();
            for (String key : jsonData.keySet()) {
                response.put(key, jsonData.get(key));
            }

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(Collections.singletonMap("error", "Credenciales inválidas"));
        } catch (Exception e) {
            System.err.println("Error en loginBartolito: " + e.getMessage());
            return ResponseEntity.status(500).body(Collections.singletonMap("error", "Error interno del servidor"));
        }
    }

    @PostMapping("/loginBartolitoByUsername")
    public ResponseEntity<?> loginBartolitoByUsername(@RequestBody Map<String, String> loginData) {
        try {
            String username = loginData.get("username");

            String response = authService.loginBartolitoByUser(username);
            return ResponseEntity.ok(new JSONObject(response).toMap());

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401)
                    .body(Collections.singletonMap("error", "Usuario no encontrado"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body(Collections.singletonMap("error", "Error interno del servidor"));
        }
    }

    // ===========================
    // LOGIN INVENTARIO
    // ===========================
    @PostMapping("/loginInventario")
    public ResponseEntity<?> loginInventario(@RequestBody Map<String, String> loginData) {
        try {
            String username = loginData.get("username");
            String password = loginData.get("password"); // Texto plano

            // Llamada al servicio (servicio se encarga de la conversión a byte[])
            String jsonString = authService.loginInventario(username, password);

            JSONObject jsonData = new JSONObject(jsonString);

            Map<String, Object> response = new HashMap<>();
            for (String key : jsonData.keySet()) {
                response.put(key, jsonData.get(key));
            }

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(Collections.singletonMap("error", "Credenciales inválidas"));
        } catch (Exception e) {
            System.err.println("Error en loginBartolito: " + e.getMessage());
            return ResponseEntity.status(500).body(Collections.singletonMap("error", "Error interno del servidor"));
        }
    }

    @PostMapping("/loginInventarioByUsername")
    public ResponseEntity<?> loginInventarioByUsername(@RequestBody Map<String, String> loginData) {
        try {
            String username = loginData.get("username");

            String response = authService.loginInventarioByUser(username);
            return ResponseEntity.ok(new JSONObject(response).toMap());

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401)
                    .body(Collections.singletonMap("error", "Usuario no encontrado"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body(Collections.singletonMap("error", "Error interno del servidor"));
        }
    }

    // ===========================
    // OBTENER USUARIO DESDE TOKEN
    // ===========================
    @PostMapping("/getUser")
    public ResponseEntity<?> getUser(@RequestBody Map<String, String> requestData) {
        try {
            String token = requestData.get("token");
            JSONObject user = authService.getUserFromToken(token);
            return ResponseEntity.ok(user.toMap());
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401)
                    .body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body(Collections.singletonMap("error", "Error interno del servidor"));
        }
    }

    @PostMapping("/getUserBartolito")
    public ResponseEntity<?> getUserBartolito(@RequestBody Map<String, String> requestData) {
        try {
            String token = requestData.get("token");
            JSONObject user = authService.getUserFromTokenBartolito(token);
            return ResponseEntity.ok(user.toMap());
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401)
                    .body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body(Collections.singletonMap("error", "Error interno del servidor"));
        }
    }

    @PostMapping("/getUserInventario")
    public ResponseEntity<?> getUserInventario(@RequestBody Map<String, String> requestData) {
        try {
            String token = requestData.get("token");
            JSONObject user = authService.getUserFromTokenInventario(token);
            return ResponseEntity.ok(user.toMap());
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401)
                    .body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body(Collections.singletonMap("error", "Error interno del servidor"));
        }
    }



}
