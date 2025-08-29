package com.bartolito.comercial.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MenuRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getPaginasPorGrupo(String grucod){
        String sql = "EXEC sp_bart_menu_paginas_x_grupo_sel ?";
        // queryForObject devuelve un String completo, no filas separadas
        return jdbcTemplate.query(sql, new Object[]{grucod}, rs -> {
            if(rs.next()){
                return rs.getString(1); // toda la columna JSON
            }
            return "{}"; // si no hay resultados
        });
    }
}
