package com.ejemplo.jwtlogin.service;

import com.ejemplo.jwtlogin.repository.AuthRepository;
import com.ejemplo.jwtlogin.repository.MenuRepository;
import com.ejemplo.jwtlogin.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MenuRepository menuRepository;

    public String obtenerPaginasPorGrupo(String tipo,String token) {
        try {
            if (tipo == null || tipo.isEmpty()) {
                tipo = "N";
            }
        String username = jwtUtil.extractUsername(token);
        // loginUsuarioByUsername devuelve un String JSON
        String resultadoJson = authRepository.loginUsuarioByUsername(tipo, username);

        if (resultadoJson == null || resultadoJson.isEmpty() || resultadoJson.equals("{}")) {
            throw new BadCredentialsException("Token inválido o usuario no encontrado");
        }

        // Parseamos el JSON
        JSONObject json = new JSONObject(resultadoJson);
        String grucod = json.getString("grucod"); // aquí obtienes SUPERV

        // Ahora llamas a tu repo de menú
        return menuRepository.getPaginasPorGrupo(grucod);
        } catch (JwtException | IllegalArgumentException e) {
            return "Token no válido";
        }
    }

}
