package com.ejemplo.jwtlogin.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.ejemplo.jwtlogin.repository.UsuariosRepository;
import com.ejemplo.jwtlogin.security.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UsuariosRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(String username, String password) {
        try {
            List<Object[]> resultados = usuarioRepository.validarUsuario(username, password);

            if (resultados.isEmpty()) {
                throw new BadCredentialsException("Credenciales inválidas");
            }

            Object[] resultado = resultados.get(0); // Tomar el primer resultado

            // Debug: imprimir cada elemento
            for (int i = 0; i < resultado.length; i++) {
                System.out.println("Índice " + i + ": " + resultado[i]);
            }

            // Si llegamos aquí, las credenciales son correctas
            // Generar token JWT con el username
            String token = jwtUtil.generateToken(username);

            // Crear JSON response con token y datos del usuario
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("resultado", "OK");
            jsonObj.put("token", token);
            jsonObj.put("de", resultado[0]);
            jsonObj.put("usecod", resultado[1]);
            jsonObj.put("useusr", resultado[2]);
            jsonObj.put("grucod", resultado[3]);
            jsonObj.put("grudes", resultado[4]);
            jsonObj.put("siscod", resultado[5]);
            jsonObj.put("sisent", resultado[6]);
            jsonObj.put("codalm_inv", resultado[7]);
            jsonObj.put("central", resultado[8]);
            jsonObj.put("nombre", resultado[9]);

            return jsonObj.toString();

        } catch (NoResultException e) {
            // Usuario no encontrado o credenciales incorrectas
            throw new BadCredentialsException("Credenciales inválidas");
        } catch (Exception e) {
            throw new RuntimeException("Error durante el login: " + e.getMessage());
        }
    }

    public String loginBartolito(String username, byte[] password) {
        try {
            List<Object[]> resultados = usuarioRepository.validarUsuarioBartolito(username, password);

            if (resultados.isEmpty()) {
                throw new BadCredentialsException("Credenciales inválidas");
            }

            Object[] resultado = resultados.get(0); // Tomar el primer resultado

            // Debug: imprimir cada elemento
            for (int i = 0; i < resultado.length; i++) {
                System.out.println("Índice " + i + ": " + resultado[i]);
            }

            // Si llegamos aquí, las credenciales son correctas
            // Generar token JWT con el username
            String token = jwtUtil.generateToken(username);

            // Crear JSON response con token y datos del usuario
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("resultado", "OK");
            jsonObj.put("token", token);
            jsonObj.put("de", resultado[0]);
            jsonObj.put("usecod", resultado[1]);
            jsonObj.put("useusr", resultado[2]);
            jsonObj.put("grucod", resultado[3]);
            jsonObj.put("grudes", resultado[4]);
            jsonObj.put("siscod", resultado[5]);
            jsonObj.put("sisent", resultado[6]);
            jsonObj.put("codalm_inv", resultado[7]);

            return jsonObj.toString();

        } catch (NoResultException e) {
            // Usuario no encontrado o credenciales incorrectas
            throw new BadCredentialsException("Credenciales inválidas");
        } catch (Exception e) {
            throw new RuntimeException("Error durante el login: " + e.getMessage());
        }
    }

    public String loginByUser(String username) {
        try {
            List<Object[]> resultados = usuarioRepository.validarUsuarioByUsername(username);

            if (resultados.isEmpty()) {
                throw new BadCredentialsException("Credenciales inválidas");
            }

            Object[] resultado = resultados.get(0); // Tomar el primer resultado

            // Debug: imprimir cada elemento
            for (int i = 0; i < resultado.length; i++) {
                System.out.println("Índice " + i + ": " + resultado[i]);
            }

            // Si llegamos aquí, las credenciales son correctas
            // Generar token JWT con el username
            String token = jwtUtil.generateToken(username);

            // Crear JSON response con token y datos del usuario
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("resultado", "OK");
            jsonObj.put("token", token);
            jsonObj.put("de", resultado[0]);
            jsonObj.put("usecod", resultado[1]);
            jsonObj.put("useusr", resultado[2]);
            jsonObj.put("grucod", resultado[3]);
            jsonObj.put("grudes", resultado[4]);
            jsonObj.put("siscod", resultado[5]);
            jsonObj.put("sisent", resultado[6]);
            jsonObj.put("codalm_inv", resultado[7]);
            jsonObj.put("central", resultado[8]);
            jsonObj.put("nombre", resultado[9]);

            return jsonObj.toString();

        } catch (NoResultException e) {
            // Usuario no encontrado o credenciales incorrectas
            throw new BadCredentialsException("Credenciales inválidas");
        } catch (Exception e) {
            throw new RuntimeException("Error durante el login: " + e.getMessage());
        }
    }

}