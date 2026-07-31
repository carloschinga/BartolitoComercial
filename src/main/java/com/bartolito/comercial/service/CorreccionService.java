package com.bartolito.comercial.service;

import com.bartolito.comercial.repository.CorreccionRepository;
import com.bartolito.comercial.repository.LiquidacionCajaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class CorreccionService {

    @Autowired
    private CorreccionRepository repository;

    // =========================================
    // CORRECCION - CABECERA
    // =========================================
    public List<Map<String, Object>> obtenerCabeceraCorreccion(
            Integer scaja,
            Integer sventa,
            Integer siscod
    ) {

        return repository.obtenerCabeceraCorreccion(
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

        return repository.obtenerDetalleCorreccion(
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

        return repository.obtenerMetodosPagoCorreccion(
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

        return repository.obtenerTransaccionesPinpad(
                fechaInicio,
                fechaFin,
                operacion,
                siscod
        );
    }

    // =========================================
    // SAVE SOLICITUD
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

        return repository.guardarSolicitudCorreccion(
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

        return repository.aprobarSolicitud(
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

        return repository.rechazarSolicitud(
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

        return repository.listarHistoricoCabecera(
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

        return repository.listarHistoricoDetalle(
                solicitudId
        );

    }

    // =========================================
    // GET SOLICITUD
    // =========================================
    public List<Map<String, Object>> obtenerSolicitud(
            Integer solicitudId
    ) {

        return repository.obtenerSolicitud(
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

        return repository.listarCabeceraSolicitud(
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

        return repository.listarDetalleSolicitud(
                solicitudId
        );

    }

    // =========================================
    // LISTAR DETALLE ORIGINAL
    // =========================================
    public List<Map<String, Object>> listarDetalleOriginal(
            Integer invnum
    ) {

        return repository.listarDetalleOriginal(
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

        return repository.anularSolicitud(
                solicitudId,
                usecodSolicita
        );

    }
}
