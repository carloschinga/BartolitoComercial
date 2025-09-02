package com.bartolito.comercial.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DashboardComercialRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String listarDashboardJson() {
        try {
            String sql = "EXEC sp_bart_desempenio_meta_venta_dashboard";
            List<String> result = jdbcTemplate.queryForList(sql, String.class);
            return String.join("", result); // concatenar filas si hubiera más de una

        } catch (DataAccessException dae) {
            System.err.println("Error al ejecutar SP dashboard: " + dae.getMessage());
            return "{\"resultado\":\"error\",\"mensaje\":\"Error en la base de datos\",\"data\":[]}";
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            return "{\"resultado\":\"error\",\"mensaje\":\"Error inesperado\",\"data\":[]}";
        }
    }

}
