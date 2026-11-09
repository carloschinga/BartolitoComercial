package com.bartolito.comercial.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class NotaCreditoInsanorRepository {

    @Autowired
    @Qualifier("lolcliJdbcTemplate")
    private JdbcTemplate lolclijdbcTemplate;

    // =========================================
    // NOTAS DE CREDITO - LISTAR CABECERA
    // =========================================
    public List<Map<String, Object>> listarNotasCredito(
            Integer invnumAper,
            Integer invnum
    ) {

        String sql = "EXEC sp_bart_ins_comer_listar_cabecera_nota_credito ?, ?";

        return lolclijdbcTemplate.queryForList(
                sql,
                invnumAper,
                invnum
        );
    }

    // =========================================
    // NOTAS DE CREDITO - LISTAR DETALLE
    // =========================================
    public List<Map<String, Object>> listarNotasCreditoDetalle(
            Integer nconum
    ) {

        String sql = "EXEC sp_bart_ins_comer_listar_detalle_nota_credito ?";

        return lolclijdbcTemplate.queryForList(
                sql,
                nconum
        );
    }

    // =========================================
    // CONCEPTOS - LISTAR
    // =========================================
    public List<Map<String, Object>> listarConceptos(
            Integer invnum
    ) {

        String sql = "EXEC sp_bart_ins_comer_listar_detalle_concepto ?";

        return lolclijdbcTemplate.queryForList(
                sql,
                invnum
        );
    }

    // =========================================
    // FACTURAS - LISTAR
    // =========================================
    public List<Map<String, Object>> listarCabeceraFactura(
            Integer invnum
    ) {

        String sql = "EXEC sp_bart_ins_comer_listar_cabecera_factura ?";

        return lolclijdbcTemplate.queryForList(
                sql,
                invnum
        );
    }

}
