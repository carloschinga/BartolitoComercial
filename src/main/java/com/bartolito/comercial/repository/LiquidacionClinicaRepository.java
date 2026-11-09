package com.bartolito.comercial.repository;

import com.bartolito.comercial.util.dto.liquidacionClinica.LiquidacionSaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Repository
public class LiquidacionClinicaRepository {

    @Autowired
    @Qualifier("lolcliJdbcTemplate")
    private JdbcTemplate lolclijdbcTemplate;

    // =========================================
    // CIERRES DE CAJA - LISTAR
    // =========================================
    public List<Map<String, Object>> listarCierresCaja(
            String fechaInicio,
            String fechaFin,
            Integer usecod
    ) {

        String sql = "EXEC sp_bart_comer_listar_cierres_caja ?, ?, ?";

        return lolclijdbcTemplate.queryForList(
                sql,
                fechaInicio,
                fechaFin,
                usecod
        );
    }

    // =========================================
    // CIERRES DE CAJA - METODOS PAGO
    // =========================================
    public List<Map<String, Object>> listarMetodosPago(
            String fechaInicio,
            String fechaFin,
            Integer invnumAper
    ) {

        String sql = "EXEC sp_bart_comer_listar_metodos_pago ?, ?, ?";

        return lolclijdbcTemplate.queryForList(
                sql,
                fechaInicio,
                fechaFin, invnumAper
        );
    }

    // =========================================
    // CIERRE DE CAJA - LISTAR FORMAS DE PAGO
    // =========================================
    public List<Map<String, Object>> listarFormasPago(
            Integer invnum
    ) {

        String sql = "EXEC sp_bart_comer_cierre_listar_formas_pago ?";

        return lolclijdbcTemplate.queryForList(
                sql,
                invnum
        );
    }

    // =========================================
    // CIERRE DE CAJA - LISTAR CABECERA
    // =========================================
    public List<Map<String, Object>> listarCabecera(
            Integer invnumAper
    ) {

        String sql = "EXEC sp_bart_comer_cierre_listar_cabecera ?";

        return lolclijdbcTemplate.queryForList(
                sql,
                invnumAper
        );
    }

    // =========================================
    // LIQUIDACIÓN - LISTAR INGRESOS
    // =========================================
    public Map<String, Object> listarIngresos(Integer invnumAper) {

        String sql = "EXEC sp_bart_ins_comer_liquidacion_listar_ingresos ?";

        List<Map<String, Object>> result = lolclijdbcTemplate.queryForList(sql, invnumAper);

        return result.isEmpty() ? null : result.get(0);
    }

    // =========================================
    // LIQUIDACIÓN - LISTAR EGRESOS
    // =========================================
    public Map<String, Object> listarEgresos(
            Integer invnumAper
    ) {

        String sql = "EXEC sp_bart_ins_comer_liquidacion_listar_egresos ?";

        List<Map<String, Object>> result = lolclijdbcTemplate.queryForList(sql, invnumAper);

        return result.isEmpty() ? null : result.get(0);
    }

    // =========================================
    // LIQUIDACIÓN - LISTAR NOTAS DE CREDITO
    // =========================================
    public Map<String, Object> listarNotasCredito(Integer invnumAper) {

        String sql = "EXEC sp_bart_ins_comer_liquidacion_listar_notas_credito ?";

        List<Map<String, Object>> result = lolclijdbcTemplate.queryForList(sql, invnumAper);

        return result.isEmpty() ? null : result.get(0);
    }

    public Map<String, Object> obtenerLiquidacion(Integer invnumAper) {

        String sql = "EXEC sp_bart_comer_liquidacion_obtener ?";

        List<Map<String, Object>> resultado =
                lolclijdbcTemplate.queryForList(sql, invnumAper);

        if (resultado == null || resultado.isEmpty()) {
            return null;
        }

        return resultado.get(0);
    }

    public Map<String, Object> saveOrUpdate(LiquidacionSaveRequest request) {

        String sql = "EXEC sp_bart_comer_liquidacion_save ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";

        return lolclijdbcTemplate.queryForMap(
                sql,

                request.getLiquidacionId(),
                request.getInvnumAper(),
                Timestamp.valueOf(request.getFechaLiquidacion()),

                request.getEstablecimiento(),
                request.getUsenam(),
                request.getUsedoc(),
                request.getTurno(),

                request.getSiscod(),
                request.getUsecod(),

                request.getCantidadSubtotal(),
                request.getSubtotalCalculado(),
                request.getSubtotalImporte(),
                request.getSubtotalDiferencia(),

                request.getNotaEfectivoImporte(),
                request.getNotaPinpadImporte(),
                request.getNotaCreditoImporte(),

                request.getNotaEfectivoCantidad(),
                request.getNotaPinpadCantidad(),
                request.getNotaCreditoCantidad(),

                request.getIngresosImporte(),
                request.getEgresosImporte(),

                request.getIngresosCantidad(),
                request.getEgresosCantidad(),

                request.getEstado(),
                request.getValidadoPor(),

                request.getFechaValidacion() != null
                        ? Timestamp.valueOf(request.getFechaValidacion())
                        : null,

                request.getNumeroGrabados(),

                request.getObservacion(),
                request.getUsuario()
        );
    }
}
