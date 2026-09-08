package com.bartolito.comercial.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ConsolidadoCajaRepository {

    @Autowired
    @Qualifier("lolfarJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    // =========================================
    // CONSOLIDADO CAJA - BATCH
    // =========================================
    public List<Map<String, Object>> guardarConsolidadoCajaBatch(
            String xml,
            String creadoPor,
            String modificadoPor
    ) {

        String sql = "EXEC sp_bart_comer_consolidad_saveOrUpdateConsolidadoCajaBatch ?,?,?";

        return jdbcTemplate.queryForList(
                sql,
                xml,
                creadoPor,
                modificadoPor
        );
    }

    // =========================================
    // CONSOLIDADO CAJA - LISTAR
    // =========================================
    public List<Map<String, Object>> listarConsolidadoCaja(
            String fechaInicio,
            String fechaFin
    ) {

        String sql = "EXEC sp_bart_comer_listar_consolidado_caja ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                fechaInicio,
                fechaFin
        );
    }

    // =========================================
    // CONSOLIDADO CAJA - CERRAR CONSOLIDADOS
    // =========================================
    public List<Map<String, Object>> cerrarConsolidado(
            String consolidadoCajaIds,
            String usuario
    ) {

        String sql = "EXEC sp_bart_comer_consolidado_cerrar_consolidado ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                consolidadoCajaIds,
                usuario
        );
    }

    // =========================================
    // CONSOLIDADO CAJA - ACTUALIZAR OBSERVACIÓN
    // =========================================
    public List<Map<String, Object>> actualizarObservacion(
            Integer invnumAper,
            String observacion,
            String usuario
    ) {

        String sql = "EXEC sp_bart_consolidado_caja_update_observacion ?, ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                invnumAper,
                observacion,
                usuario
        );
    }
}
