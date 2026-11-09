package com.bartolito.comercial.repository.insanor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ConciliacionRepository {

    @Autowired
    @Qualifier("sigolhJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    // =========================================
    // LISTADO
    // =========================================
    public List<Map<String, Object>> listadoPrueba(
    ) {

        String sql = "EXEC sp_bart_ins_listar_operaciones";

        return jdbcTemplate.queryForList(
                sql
        );
    }
}
