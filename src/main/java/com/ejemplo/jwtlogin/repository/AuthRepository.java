package com.ejemplo.jwtlogin.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String loginUsuario(String tipo, String username, String password) {
        String sql = "EXEC sp_bart_login_validar ?, ?, ?";
        return jdbcTemplate.query(sql, new Object[]{tipo, username, password}, rs -> {
            if (rs.next()) {
                return rs.getString(1); // toda la columna JSON
            }
            return "{}";
        });
    }
    public String loginUsuario2(String tipo, String username, byte[] password) {
        String sql = "EXEC sp_bart_login_validar2 ?, ?, ?";
        return jdbcTemplate.query(sql, new Object[]{tipo, username, password}, rs -> {
            if (rs.next()) {
                return rs.getString(1); // toda la columna JSON
            }
            return "{}";
        });
    }

    public String loginUsuarioByUsername(String tipo, String username) {
        String sql = "EXEC sp_bart_login_validar ?, ?, NULL";
        return jdbcTemplate.query(sql, new Object[]{tipo, username}, rs -> {
            if (rs.next()) {
                return rs.getString(1); // toda la columna JSON
            }
            return "{}";
        });
    }
}
