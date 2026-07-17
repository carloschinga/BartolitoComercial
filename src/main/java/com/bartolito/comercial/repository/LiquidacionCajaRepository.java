package com.bartolito.comercial.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class LiquidacionCajaRepository {

    @Autowired
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
    // OBTENER LIQUIDACIÓN DE CAJA
    // =========================================
    public List<Map<String, Object>> obtenerLiquidacionCaja(
            Integer invnumAper,
            Integer siscod
    ) {

        String sql = "EXEC sp_bart_comer_liquidacion_caja_obtener ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                invnumAper,
                siscod
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
                        String.join(",", Collections.nCopies(51, "?"));

        System.out.println(sql);
        System.out.println(sql.chars().filter(c -> c == '?').count());
        return jdbcTemplate.queryForList(
                sql,
                // CABECERA
                request.get("invnumAper"),
                request.get("fechaLiquidacion"),
                request.get("establecimiento"),
                request.get("usenam"),
                request.get("usedoc"),
                request.get("turno"),
                request.get("siscod"),
                request.get("usecod"),

                // VENTAS
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

                // MAGISTRALES
                request.get("ventasMagistralEfectivoCant"),
                request.get("ventasMagistralEfectivoImporte"),
                request.get("ventasMagistralPosCant"),
                request.get("ventasMagistralPosImporte"),

                // NOTAS DE CREDITO
                request.get("notasCreditoEfectivoCant"),
                request.get("notasCreditoEfectivoImporte"),
                request.get("notasCreditoPinpadCant"),
                request.get("notasCreditoPinpadImporte"),
                request.get("notasCreditoCreditoCant"),
                request.get("notasCreditoCreditoImporte"),

                // VALES
                request.get("valesSalidaCant"),
                request.get("valesSalidaImporte"),

                // COTIZADOS
                request.get("magistralEfectivoCotCant"),
                request.get("magistralEfectivoCotImporte"),
                request.get("magistralPosCotCant"),
                request.get("magistralPosCotImporte"),

                // IMPORTES ENTREGADOS
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

                // ADICIONALES
                request.get("numeroGrabados"),
                request.get("observaciones"),
                request.get("usuarioModificacion")
        );
    }

}
