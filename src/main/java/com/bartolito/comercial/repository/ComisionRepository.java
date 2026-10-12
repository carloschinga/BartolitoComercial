package com.bartolito.comercial.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ComisionRepository {

    @Autowired
    @Qualifier("lolfarJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    // =========================================
    // COMISIONES - LISTAR METAS DE VENTAS
    // =========================================
    public List<Map<String, Object>> listarMetasVentas() {

        String sql = "EXEC sp_bart_comer_comision_listar_metas_ventas";

        return jdbcTemplate.queryForList(sql);
    }

    // =========================================
    // COMISIONES - LISTAR PERSONAL MATRICULADO
    // =========================================
    public List<Map<String, Object>> listarPersonalMatriculado(
            Integer siscod,
            Integer cuotVtaId
    ) {

        String sql = "EXEC sp_bart_comer_comision_listar_personal_matriculado ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                siscod,
                cuotVtaId
        );
    }
}
