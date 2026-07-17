package com.bartolito.comercial.service;

import com.bartolito.comercial.repository.LiquidacionCajaRepository;
import com.bartolito.comercial.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LiquidacionCajaService {

    @Autowired
    private LiquidacionCajaRepository repository;

    // =========================================
    // LIQUIDACION CAJA - CABECERA
    // =========================================
    public List<Map<String, Object>> obtenerCabeceraLiquidacionCaja(
            String fechaInicio,
            String fechaFin,
            Integer siscod,
            Integer invnumAper
    ) {

        return repository.obtenerCabeceraLiquidacionCaja(
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

        return repository.obtenerNotasCreditoLiquidacionCaja(
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

        return repository.obtenerFormasPagoLiquidacionCaja(
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

        return repository.obtenerAnulacionesPinpadLiquidacionCaja(
                fechaInicio,
                fechaFin,
                siscod,
                invnumAper
        );
    }

    // =========================================
    // GUARDAR / ACTUALIZAR LIQUIDACIÓN DE CAJA
    // =========================================
    public List<Map<String, Object>> guardarLiquidacionCaja(
            Map<String, Object> request
    ) {

        return repository.guardarLiquidacionCaja(request);
    }

    // =========================================
    // OBTENER LIQUIDACIÓN DE CAJA
    // =========================================
    public List<Map<String, Object>> obtenerLiquidacionCaja(
            Integer invnumAper,
            Integer siscod
    ) {
        return repository.obtenerLiquidacionCaja(
                invnumAper,
                siscod
        );
    }
}
