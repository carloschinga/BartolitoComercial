package com.bartolito.comercial.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class LiquidacionCajaRepository {

    @Autowired
    @Qualifier("lolfarJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    // =========================================
    // LIQUIDACION CAJA - CABECERA
    // =========================================
    public List<Map<String, Object>> obtenerCabeceraLiquidacionCaja(
            String fechaInicio,
            String fechaFin,
            Integer siscod,
            Integer invnumAper
    ) {

        String sql = "EXEC sp_bart_comer_formato_liquidacion_caja_cabecera ?, ?, ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                fechaInicio,
                fechaFin,
                siscod,
                invnumAper
        );
    }

    // =========================================
    // LIQUIDACION CAJA - NOTAS DE CREDITO
    // =========================================
    public List<Map<String, Object>> obtenerNotasCreditoLiquidacionCaja(
            String fechaInicio,
            String fechaFin,
            Integer siscod,
            Integer invnumAper
    ) {

        String sql = "EXEC sp_bart_comer_formato_liquidacion_caja_nota_credito ?, ?, ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                fechaInicio,
                fechaFin,
                siscod,
                invnumAper
        );
    }

    // =========================================
    // LIQUIDACION CAJA - FORMAS DE PAGO
    // =========================================
    public List<Map<String, Object>> obtenerFormasPagoLiquidacionCaja(
            String fechaInicio,
            String fechaFin,
            Integer siscod,
            Integer invnumAper
    ) {

        String sql = "EXEC sp_bart_comer_formato_liquidacion_caja_forma_pago ?, ?, ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                fechaInicio,
                fechaFin,
                siscod,
                invnumAper
        );
    }

    // =========================================
    // LIQUIDACION CAJA - ANULACIONES PINPAD
    // =========================================
    public List<Map<String, Object>> obtenerAnulacionesPinpadLiquidacionCaja(
            String fechaInicio,
            String fechaFin,
            Integer siscod,
            Integer invnumAper
    ) {

        String sql = "EXEC sp_bart_comer_formato_liquidacion_caja_anulaciones_pinpad ?, ?, ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                fechaInicio,
                fechaFin,
                siscod,
                invnumAper
        );
    }

    // =========================================
    // LIQUIDACION CAJA - LISTAR EFECTIVOS RECIBIDOS
    // =========================================
    public List<Map<String, Object>> obtenerEfectivoRecibido(
            String fechaInicio,
            String fechaFin
    ) {

        String sql = "EXEC sp_bart_comer_liquidacion_listar_efectivo_recibido ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                fechaInicio,
                fechaFin
        );
    }

    // =========================================
    // OBTENER LIQUIDACIÓN DE CAJA
    // =========================================
    public List<Map<String, Object>> obtenerLiquidacionCaja(
            Integer invnumAper,
            Integer siscod,
            Integer usecod
    ) {

        String sql = "EXEC sp_bart_comer_liquidacion_caja_obtener ?, ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                invnumAper,
                siscod,
                usecod
        );
    }

    // =========================================
    // VALIDAR CIERRE CAJA
    // =========================================
    public List<Map<String, Object>> validarCierreCaja(
            Integer invnumAper
    ) {

        String sql = "EXEC sp_bart_comer_formato_liquidacion_validar_cierre ?";

        return jdbcTemplate.queryForList(
                sql,
                invnumAper
        );
    }

    // =========================================
    // GUARDAR / ACTUALIZAR LIQUIDACIÓN DE CAJA
    // =========================================
    public List<Map<String, Object>> guardarLiquidacionCaja(
            Map<String, Object> request
    ) {
        String sql =
                "EXEC sp_bart_comer_liquidacion_caja_save_or_update " +
                        String.join(",", Collections.nCopies(68, "?"));

        System.out.println(sql);
        System.out.println(sql.chars().filter(c -> c == '?').count());
        return jdbcTemplate.queryForList(
                sql,
                // CABECERA (8 parámetros)
                request.get("invnumAper"),
                request.get("fechaLiquidacion"),
                request.get("establecimiento"),
                request.get("usenam"),
                request.get("usedoc"),
                request.get("turno"),
                request.get("siscod"),
                request.get("usecod"),

                // VENTAS (12 parámetros)
                request.get("ventasEfectivoCant"),
                request.get("ventasEfectivoImporte"),
                request.get("ventasPinpadCant"),
                request.get("ventasPinpadImporte"),
                request.get("ventasPosCant"),
                request.get("ventasPosImporte"),
                request.get("ventasEmergenciaCant"),
                request.get("ventasEmergenciaImporte"),
                request.get("ventasNotaCant"),
                request.get("ventasNotaImporte"),
                request.get("ventasCreditoCant"),
                request.get("ventasCreditoImporte"),

                // MAGISTRALES (4 parámetros)
                request.get("ventasMagistralEfectivoCant"),
                request.get("ventasMagistralEfectivoImporte"),
                request.get("ventasMagistralPosCant"),
                request.get("ventasMagistralPosImporte"),

                // NOTAS DE CREDITO (6 parámetros)
                request.get("notasCreditoEfectivoCant"),
                request.get("notasCreditoEfectivoImporte"),
                request.get("notasCreditoPinpadCant"),
                request.get("notasCreditoPinpadImporte"),
                request.get("notasCreditoCreditoCant"),
                request.get("notasCreditoCreditoImporte"),

                // NOTAS DE CRÉDITO APLICADAS (2 parámetros)
                request.get("notasCreditoAplicadaCant"),
                request.get("notasCreditoAplicadaImporte"),

                // VALES (2 parámetros)
                request.get("valesSalidaCant"),
                request.get("valesSalidaImporte"),

                // COTIZADOS (4 parámetros)
                request.get("magistralEfectivoCotCant"),
                request.get("magistralEfectivoCotImporte"),
                request.get("magistralPosCotCant"),
                request.get("magistralPosCotImporte"),

                // IMPORTES ENTREGADOS (13 parámetros)
                request.get("importeEntregadoEfectivo"),
                request.get("importeEntregadoPinpad"),
                request.get("importeEntregadoPos"),
                request.get("importeEntregadoEmergencia"),
                request.get("importeEntregadoNota"),
                request.get("importeEntregadoCredito"),
                request.get("importeEntregadoMagEfectFac"),
                request.get("importeEntregadoMagPosFac"),
                request.get("importeEntregadoNotaEfect"),
                request.get("importeEntregadoNotaPinpad"),
                request.get("importeEntregadoNotaCredito"),
                request.get("importeEntregadoVales"),
                request.get("importeEntregadoNotaAplic"),

                // DIFERENCIAS (13 parámetros)
                request.get("ventasEfectivoDif"),
                request.get("ventasPinpadDif"),
                request.get("ventasPosDif"),
                request.get("ventasEmergenciaDif"),
                request.get("ventasNotaDif"),
                request.get("ventasCreditoDif"),
                request.get("ventasEfectivoMagFacDif"),
                request.get("ventasPosMagFacDif"),
                request.get("notaEfectivoDif"),
                request.get("notaPinpadDif"),
                request.get("notaCreditoDif"),
                request.get("notaAplicadaDif"),
                request.get("valesSalidaDif"),

                // ADICIONALES DE CABECERA (4 parámetros)
                request.get("numeroGrabados"),
                request.get("observaciones"),
                request.get("subtotalDiferencia"),
                request.get("usuarioModificacion")
        );
    }

}
