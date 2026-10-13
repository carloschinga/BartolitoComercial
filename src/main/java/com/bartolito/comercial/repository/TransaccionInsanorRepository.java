package com.bartolito.comercial.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TransaccionInsanorRepository {

    @Autowired
    @Qualifier("lolcliJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    // =========================================
    // SUMATORIA FORMA DE PAGO COMPLETA
    // =========================================
    public List<Map<String, Object>> sumatoriaFormaPagoCompleta(
            String fechaInicio,
            String fechaFin,
            Integer siscod,
            Integer invnumAper
    ) {

        String sql = "EXEC sp_bart_ins_comer_sumatoria_forma_pago_completa ?, ?, ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                fechaInicio,
                fechaFin,
                siscod,
                invnumAper
        );
    }

    // =========================================
    // LISTAR DUPLICADOS HUÉRFANOS
    // =========================================
    public List<Map<String, Object>> listarDuplicadosHuerfanos(
            String fechaInicio,
            String fechaFin,
            Integer siscod,
            Integer invnumAper
    ) {

        String sql = "EXEC sp_bart_ins_comer_listar_duplicados_huerfanos ?, ?, ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                fechaInicio,
                fechaFin,
                siscod,
                invnumAper
        );
    }

    // =========================================
    // LISTAR NOTAS DE CRÉDITO
    // =========================================
    public List<Map<String, Object>> listarNotaCredito(
            String fechaInicio,
            String fechaFin,
            Integer siscod,
            Integer invnumAper
    ) {

        String sql = "EXEC sp_bart_ins_comer_listar_nota_credito ?, ?, ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                fechaInicio,
                fechaFin,
                siscod,
                invnumAper
        );
    }

    // =========================================
    // LISTAR TRANSACCIONES DE CRÉDITO
    // =========================================
    public List<Map<String, Object>> listarTransaccionesCredito(
            String fechaInicio,
            String fechaFin,
            Integer siscod,
            Integer invnumAper
    ) {

        String sql = "EXEC sp_bart_ins_comer_listar_transacciones_credito ?, ?, ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                fechaInicio,
                fechaFin,
                siscod,
                invnumAper
        );
    }

    // =========================================
    // LISTAR NOTAS DE CRÉDITO APLICADAS
    // =========================================
    public List<Map<String, Object>> listarNotaCreditoAplicadas(
            String fechaInicio,
            String fechaFin,
            Integer siscod,
            Integer invnumAper
    ) {

        String sql = "EXEC sp_bart_ins_comer_listar_nota_credito_aplicadas ?, ?, ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                fechaInicio,
                fechaFin,
                siscod,
                invnumAper
        );
    }

    // =========================================
    // COMERCIAL - LISTAR INGRESOS
    // =========================================
    public List<Map<String, Object>> listarIngresos(
            String fechaInicio,
            String fechaFin,
            Integer siscod,
            Integer invnumAper
    ) {

        String sql = "EXEC sp_bart_ins_comer_listar_ingresos ?, ?, ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                fechaInicio,
                fechaFin,
                siscod,
                invnumAper
        );
    }

    // =========================================
// COMERCIAL - LISTAR EGRESOS
// =========================================
    public List<Map<String, Object>> listarEgresos(
            String fechaInicio,
            String fechaFin,
            Integer siscod,
            Integer invnumAper
    ) {

        String sql = "EXEC sp_bart_ins_comer_listar_egresos ?, ?, ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                fechaInicio,
                fechaFin,
                siscod,
                invnumAper
        );
    }
}
