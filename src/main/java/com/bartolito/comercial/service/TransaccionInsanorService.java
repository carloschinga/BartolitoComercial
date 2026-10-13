package com.bartolito.comercial.service;

import com.bartolito.comercial.repository.TransaccionInsanorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TransaccionInsanorService {

    @Autowired
    private TransaccionInsanorRepository repository;

    // =========================================
    // SUMATORIA FORMA DE PAGO COMPLETA
    // =========================================
    public List<Map<String, Object>> sumatoriaFormaPagoCompleta(
            String fechaInicio,
            String fechaFin,
            Integer siscod,
            Integer invnumAper
    ) {

        return repository.sumatoriaFormaPagoCompleta(
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

        return repository.listarDuplicadosHuerfanos(
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

        return repository.listarNotaCredito(
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

        return repository.listarTransaccionesCredito(
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

        return repository.listarNotaCreditoAplicadas(
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

        return repository.listarIngresos(
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

        return repository.listarEgresos(
                fechaInicio,
                fechaFin,
                siscod,
                invnumAper
        );
    }
}
