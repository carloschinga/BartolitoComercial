package com.bartolito.comercial.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TransaccionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // =========================================
    // LISTAR TRANSACCIONES
    // =========================================
    public List<Map<String, Object>> listarTransacciones(
            String fechaInicio,
            String fechaFin,
            Integer invnumAper,
            Integer siscod
    ) {

        String sql = "EXEC sp_bart_comer_listar_transacciones ?, ?, ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                fechaInicio,
                fechaFin,
                invnumAper,
                siscod
        );
    }

    // =========================================
    // LISTAR VENTAS AL CREDITO
    // =========================================
    public List<Map<String, Object>> listarVentasCredito(
            String fechaInicio,
            String fechaFin,
            Integer invnumAper,
            Integer siscod
    ) {

        String sql = "EXEC sp_bart_comer_listar_transacciones_credito ?, ?, ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                fechaInicio,
                fechaFin,
                invnumAper,
                siscod
        );
    }

    // =========================================
    // LISTAR APERTURAS DE CAJA
    // =========================================
    public List<Map<String, Object>> listarAperturasCaja(
            String fechaInicio,
            String fechaFin,
            Integer siscod
    ) {

        String sql = "EXEC sp_bart_comer_listar_aperturas_caja ?, ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                fechaInicio,
                fechaFin,
                siscod
        );
    }

    // =========================================
    // LISTAR METODOS DE PAGO
    // =========================================
    public List<Map<String, Object>> listarMetodosPago(
            String fechaInicio,
            String fechaFin
    ) {

        String sql = "EXEC sp_bart_comer_listar_metodo_pago ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                fechaInicio,
                fechaFin
        );
    }

    // =========================================
    // LISTAR SUMATORIA FORMA PAGO
    // =========================================
    public List<Map<String, Object>> listarSumatoriaFormaPago(
            String fechaInicio,
            String fechaFin,
            Integer invnumAper,
            Integer siscod
    ) {

        String sql = "EXEC sp_bart_comer_sumatoria_forma_pago ?, ?, ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                siscod,
                fechaInicio,
                fechaFin,
                invnumAper
        );
    }

    // =========================================
    // LISTAR NOTAS DE CREDITO
    // =========================================
    public List<Map<String, Object>> listarNotaCredito(
            String fechaInicio,
            String fechaFin,
            Integer invnumAper,
            Integer siscod
    ) {

        String sql = "EXEC sp_bart_comer_listar_nota_credito ?, ?, ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                siscod,
                fechaInicio,
                fechaFin,
                invnumAper
        );
    }

    // =========================================
    // OBTENER CABECERA NOTA CREDITO
    // =========================================
    public List<Map<String, Object>> obtenerCabeceraNotaCredito(
            Integer invnum
    ) {

        String sql = "EXEC sp_bart_comer_cabecera_detalle_nota_credito ?";

        return jdbcTemplate.queryForList(
                sql,
                invnum
        );
    }

    // =========================================
    // OBTENER DETALLE PRODUCTOS NOTA CREDITO
    // =========================================
    public List<Map<String, Object>> obtenerDetalleProductosNotaCredito(
            Integer invnum
    ) {

        String sql = "EXEC sp_bart_comer_detalle_productos_nota_credito ?";

        return jdbcTemplate.queryForList(
                sql,
                invnum
        );
    }

    // =========================================
    // OBTENER DETALLE APLICADO
    // =========================================
    public List<Map<String, Object>> obtenerDetalleAplicado(
            Integer invnum
    ) {

        String sql = "EXEC sp_bart_comer_detalle_aplicacion ?";

        return jdbcTemplate.queryForList(
                sql,
                invnum
        );
    }

    // =========================================
    // OBTENER LISTADO HUERFANOS
    // =========================================
    public List<Map<String, Object>> listarHuerfanos(
            String fechaInicio,
            String fechaFin,
            Integer invnumAper,
            Integer siscod
    ) {

        String sql = "EXEC sp_bart_comer_listar_duplicados_huerfanos ?, ?, ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                fechaInicio,
                fechaFin,
                invnumAper,
                siscod
        );
    }

}
