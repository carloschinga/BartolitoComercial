package com.bartolito.comercial.service;

import com.bartolito.comercial.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository repository;

    // =========================================
    // LISTAR TRANSACCIONES
    // =========================================
    public List<Map<String, Object>> listarTransacciones(
            String fechaInicio,
            String fechaFin,
            Integer invnumAper,
            Integer siscod
    ) {

        return repository.listarTransacciones(
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

        return repository.listarVentasCredito(
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

        return repository.listarAperturasCaja(
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

        return repository.listarMetodosPago(
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

        return repository.listarSumatoriaFormaPago(
                fechaInicio,
                fechaFin,
                invnumAper,
                siscod
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

        return repository.listarNotaCredito(
                fechaInicio,
                fechaFin,
                invnumAper,
                siscod
        );
    }

    // =========================================
    // LISTAR PINPAD ANULADOS
    // =========================================
    public List<Map<String, Object>> listarPinpadAnulado(
            String fechaInicio,
            String fechaFin,
            Integer invnumAper,
            Integer siscod
    ) {

        return repository.listarPinpadAnulado(
                fechaInicio,
                fechaFin,
                invnumAper,
                siscod
        );
    }

    // =========================================
    // LISTAR EMERGENCIAS PINPAD
    // =========================================
    public List<Map<String, Object>> listarEmergenciasPinpad(
            String fechaInicio,
            String fechaFin,
            Integer invnumAper,
            Integer siscod
    ) {

        return repository.listarEmergenciasPinpad(
                fechaInicio,
                fechaFin,
                invnumAper,
                siscod
        );
    }

    // =========================================
    // OBTENER CABECERA NOTA CREDITO
    // =========================================
    public List<Map<String, Object>> obtenerCabeceraNotaCredito(
            Integer invnum
    ) {

        return repository.obtenerCabeceraNotaCredito(
                invnum
        );
    }

    // =========================================
    // OBTENER DETALLE PRODUCTOS NOTA CREDITO
    // =========================================
    public List<Map<String, Object>> obtenerDetalleProductosNotaCredito(
            Integer invnum
    ) {

        return repository.obtenerDetalleProductosNotaCredito(
                invnum
        );
    }

    // =========================================
    //  OBTENER DETALLE APLICADO
    // =========================================
    public List<Map<String, Object>> obtenerDetalleAplicado(
            Integer invnum
    ) {

        return repository.obtenerDetalleAplicado(
                invnum
        );
    }

    // =========================================
    // LISTAR HUERFANOS
    // =========================================
    public List<Map<String, Object>> listarHuerfanos(
            String fechaInicio,
            String fechaFin,
            Integer invnumAper,
            Integer siscod
    ) {

        return repository.listarHuerfanos(
                fechaInicio,
                fechaFin,
                invnumAper,
                siscod
        );
    }

}
