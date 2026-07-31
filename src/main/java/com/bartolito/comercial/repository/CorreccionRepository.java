package com.bartolito.comercial.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public class CorreccionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // =========================================
    // CORRECCION - CABECERA
    // =========================================
    public List<Map<String, Object>> obtenerCabeceraCorreccion(
            Integer scaja,
            Integer sventa,
            Integer siscod
    ) {

        String sql = "EXEC sp_bart_comer_correccion_listar_cabecera ?, ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                scaja,
                sventa,
                siscod
        );
    }

    // =========================================
    // CORRECCION - DETALLE
    // =========================================
    public List<Map<String, Object>> obtenerDetalleCorreccion(
            Integer invnum
    ) {

        String sql = "EXEC sp_bart_comer_correccion_listar_detalle ?";

        return jdbcTemplate.queryForList(
                sql,
                invnum
        );
    }

    // =========================================
    // CORRECCION - METODOS DE PAGO
    // =========================================
    public List<Map<String, Object>> obtenerMetodosPagoCorreccion(
            String fechaInicio,
            String fechaFin
    ) {

        String sql = "EXEC sp_bart_comer_correccion_listar_metodo_pago ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                fechaInicio,
                fechaFin
        );
    }

    // =========================================
    // CORRECCION - TRANSACCIONES PINPAD
    // =========================================
    public List<Map<String, Object>> obtenerTransaccionesPinpad(
            String fechaInicio,
            String fechaFin,
            String operacion,
            Integer siscod
    ) {

        String sql = "EXEC sp_bart_comer_correccion_listar_transacciones_pinpad ?, ?, ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                fechaInicio,
                fechaFin,
                operacion,
                siscod
        );
    }


    // =========================================
    // GUARDAR SOLICITUD
    // =========================================
    public List<Map<String, Object>> guardarSolicitudCorreccion(
            Integer siscod,
            Integer secuenciaCaja,
            Integer secuenciaVenta,
            Integer secuenciaApertura,
            String numComprobante,
            BigDecimal totalComprobante,
            String fechaCaja,
            Integer usecodSolicita,
            String detalleXml
    ) {

        String sql = "EXEC sp_bart_comer_correccion_save_solicitud ?, ?, ?, ?, ?, ?, ?, ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                siscod,
                secuenciaCaja,
                secuenciaVenta,
                secuenciaApertura,
                numComprobante,
                totalComprobante,
                fechaCaja,
                usecodSolicita,
                detalleXml
        );
    }


    // =========================================
    // APROBAR SOLICITUD
    // =========================================
    public List<Map<String, Object>> aprobarSolicitud(
            Integer solicitudId,
            Integer usecodAprueba
    ) {

        String sql = "EXEC sp_bart_comer_correccion_aprobar_solicitud ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                solicitudId,
                usecodAprueba
        );

    }

    // =========================================
    // RECHAZAR SOLICITUD
    // =========================================
    public List<Map<String, Object>> rechazarSolicitud(
            Integer solicitudId,
            String motivoRechazo,
            Integer usecodAprueba
    ) {

        String sql = "EXEC sp_bart_comer_correccion_rechazar_solicitud ?, ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                solicitudId,
                motivoRechazo,
                usecodAprueba
        );

    }


    // =========================================
    // HISTORICO CABECERA
    // =========================================
    public List<Map<String, Object>> listarHistoricoCabecera(
            String fechaInicio,
            String fechaFin,
            Integer usecod,
            Integer siscod,
            String estado
    ) {

        String sql = "EXEC sp_bart_comer_correccion_listar_historico_cabecera_solicitud ?, ?, ?, ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                fechaInicio,
                fechaFin,
                usecod,
                siscod,
                estado
        );

    }


    // =========================================
    // HISTORICO DETALLE
    // =========================================
    public List<Map<String, Object>> listarHistoricoDetalle(
            Integer solicitudId
    ) {

        String sql = "EXEC sp_bart_comer_correccion_listar_historico_detalle_solicitud ?";

        return jdbcTemplate.queryForList(
                sql,
                solicitudId
        );

    }

    // =========================================
    // GET SOLICITUD
    // =========================================
    public List<Map<String, Object>> obtenerSolicitud(
            Integer solicitudId
    ) {

        String sql = "EXEC sp_bart_comer_correccion_get_solicitud ?";

        return jdbcTemplate.queryForList(
                sql,
                solicitudId
        );

    }

    // =========================================
    // LISTAR CABECERA SOLICITUD
    // =========================================
    public List<Map<String, Object>> listarCabeceraSolicitud(
            String fechaInicio,
            String fechaFin,
            String estado
    ) {

        String sql = "EXEC sp_bart_comer_correccion_listar_cabecera_solicitud ?, ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                fechaInicio,
                fechaFin,
                estado
        );

    }

    // =========================================
    // LISTAR DETALLE SOLICITUD
    // =========================================
    public List<Map<String, Object>> listarDetalleSolicitud(
            Integer solicitudId
    ) {

        String sql = "EXEC sp_bart_comer_correccion_listar_detalle_solicitud ?";

        return jdbcTemplate.queryForList(
                sql,
                solicitudId
        );

    }

    // =========================================
    // LISTAR DETALLE ORIGINAL
    // =========================================
    public List<Map<String, Object>> listarDetalleOriginal(
            Integer invnum
    ) {

        String sql = "EXEC sp_bart_comer_correccion_listar_detalle_original ?";

        return jdbcTemplate.queryForList(
                sql,
                invnum
        );

    }

    // =========================================
    // ANULAR SOLICITUD
    // =========================================
    public List<Map<String, Object>> anularSolicitud(
            Integer solicitudId,
            Integer usecodSolicita
    ) {

        String sql = "EXEC sp_bart_comer_correccion_anular_solicitud ?, ?";

        return jdbcTemplate.queryForList(
                sql,
                solicitudId,
                usecodSolicita
        );

    }
}
