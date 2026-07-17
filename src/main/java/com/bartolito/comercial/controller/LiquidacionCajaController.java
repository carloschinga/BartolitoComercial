package com.bartolito.comercial.controller;


import com.bartolito.comercial.service.LiquidacionCajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/liquidacion")
public class LiquidacionCajaController {

    @Autowired
    private LiquidacionCajaService service;

    // =========================================
    // CABECERA
    // =========================================
    @PostMapping("/cabecera")
    public ResponseEntity<?> obtenerCabeceraLiquidacionCaja(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio").toString();
        String fechaFin = request.get("fechaFin").toString();

        Integer invnumAper = request.get("invnumAper") != null
                ? Integer.parseInt(request.get("invnumAper").toString())
                : null;

        Integer siscod = request.get("siscod") != null
                ? Integer.parseInt(request.get("siscod").toString())
                : null;

        List<Map<String, Object>> result = service.obtenerCabeceraLiquidacionCaja(
                fechaInicio,
                fechaFin,
                siscod,
                invnumAper
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // NOTAS DE CREDITO
    // =========================================
    @PostMapping("/notasCredito")
    public ResponseEntity<?> obtenerNotasCreditoLiquidacionCaja(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio").toString();
        String fechaFin = request.get("fechaFin").toString();

        Integer invnumAper = request.get("invnumAper") != null
                ? Integer.parseInt(request.get("invnumAper").toString())
                : null;

        Integer siscod = request.get("siscod") != null
                ? Integer.parseInt(request.get("siscod").toString())
                : null;

        List<Map<String, Object>> result = service.obtenerNotasCreditoLiquidacionCaja(
                fechaInicio,
                fechaFin,
                siscod,
                invnumAper
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // FORMAS DE PAGO
    // =========================================
    @PostMapping("/formasPago")
    public ResponseEntity<?> obtenerFormasPagoLiquidacionCaja(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio").toString();
        String fechaFin = request.get("fechaFin").toString();

        Integer invnumAper = request.get("invnumAper") != null
                ? Integer.parseInt(request.get("invnumAper").toString())
                : null;

        Integer siscod = request.get("siscod") != null
                ? Integer.parseInt(request.get("siscod").toString())
                : null;

        List<Map<String, Object>> result = service.obtenerFormasPagoLiquidacionCaja(
                fechaInicio,
                fechaFin,
                siscod,
                invnumAper
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // ANULACIONES PINPAD
    // =========================================
    @PostMapping("/anulacionesPinpad")
    public ResponseEntity<?> obtenerAnulacionesPinpadLiquidacionCaja(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio").toString();
        String fechaFin = request.get("fechaFin").toString();

        Integer invnumAper = request.get("invnumAper") != null
                ? Integer.parseInt(request.get("invnumAper").toString())
                : null;

        Integer siscod = request.get("siscod") != null
                ? Integer.parseInt(request.get("siscod").toString())
                : null;

        List<Map<String, Object>> result = service.obtenerAnulacionesPinpadLiquidacionCaja(
                fechaInicio,
                fechaFin,
                siscod,
                invnumAper
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // OBTENER LIQUIDACIÓN DE CAJA
    // =========================================
    @PostMapping("/obtenerLiquidacionCaja")
    public ResponseEntity<?> obtenerLiquidacionCaja(
            @RequestBody Map<String, Object> request
    ) {

        Integer invnumAper = request.get("invnumAper") != null
                ? Integer.parseInt(request.get("invnumAper").toString())
                : null;

        Integer siscod = request.get("siscod") != null
                ? Integer.parseInt(request.get("siscod").toString())
                : null;

        List<Map<String, Object>> result = service.obtenerLiquidacionCaja(
                invnumAper,
                siscod
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // GUARDAR / ACTUALIZAR LIQUIDACIÓN DE CAJA
    // =========================================
    @PostMapping("/guardarLiquidacionCaja")
    public ResponseEntity<?> guardarLiquidacionCaja(
            @RequestBody Map<String, Object> request
    ) {

        List<Map<String, Object>> result =
                service.guardarLiquidacionCaja(request);

        return ResponseEntity.ok(result);
    }
}
