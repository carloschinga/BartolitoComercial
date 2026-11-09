package com.bartolito.comercial.controller;

import com.bartolito.comercial.service.TransaccionInsanorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transaccionInsanor")
public class TransaccionInsanorController {

    @Autowired
    private TransaccionInsanorService service;

    // =========================================
    // SUMATORIA FORMA DE PAGO COMPLETA
    // =========================================
    @PostMapping("/sumatoriaFormaPagoCompleta")
    public ResponseEntity<?> sumatoriaFormaPagoCompleta(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio") != null
                ? request.get("fechaInicio").toString()
                : null;

        String fechaFin = request.get("fechaFin") != null
                ? request.get("fechaFin").toString()
                : null;

        Integer siscod = request.get("siscod") != null
                ? Integer.parseInt(request.get("siscod").toString())
                : null;

        Integer invnumAper = request.get("invnumAper") != null
                ? Integer.parseInt(request.get("invnumAper").toString())
                : null;

        List<Map<String, Object>> result = service.sumatoriaFormaPagoCompleta(
                fechaInicio,
                fechaFin,
                siscod,
                invnumAper
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // LISTAR DUPLICADOS HUÉRFANOS
    // =========================================
    @PostMapping("/listarDuplicadosHuerfanos")
    public ResponseEntity<?> listarDuplicadosHuerfanos(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio") != null
                ? request.get("fechaInicio").toString()
                : null;

        String fechaFin = request.get("fechaFin") != null
                ? request.get("fechaFin").toString()
                : null;

        Integer siscod = request.get("siscod") != null
                ? Integer.parseInt(request.get("siscod").toString())
                : null;

        Integer invnumAper = request.get("invnumAper") != null
                ? Integer.parseInt(request.get("invnumAper").toString())
                : null;

        List<Map<String, Object>> result = service.listarDuplicadosHuerfanos(
                fechaInicio,
                fechaFin,
                siscod,
                invnumAper
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // LISTAR NOTAS DE CRÉDITO
    // =========================================
    @PostMapping("/listarNotaCredito")
    public ResponseEntity<?> listarNotaCredito(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio") != null
                ? request.get("fechaInicio").toString()
                : null;

        String fechaFin = request.get("fechaFin") != null
                ? request.get("fechaFin").toString()
                : null;

        Integer siscod = request.get("siscod") != null
                ? Integer.parseInt(request.get("siscod").toString())
                : null;

        Integer invnumAper = request.get("invnumAper") != null
                ? Integer.parseInt(request.get("invnumAper").toString())
                : null;

        List<Map<String, Object>> result = service.listarNotaCredito(
                fechaInicio,
                fechaFin,
                siscod,
                invnumAper
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // LISTAR TRANSACCIONES DE CRÉDITO
    // =========================================
    @PostMapping("/listarTransaccionesCredito")
    public ResponseEntity<?> listarTransaccionesCredito(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio") != null
                ? request.get("fechaInicio").toString()
                : null;

        String fechaFin = request.get("fechaFin") != null
                ? request.get("fechaFin").toString()
                : null;

        Integer siscod = request.get("siscod") != null
                ? Integer.parseInt(request.get("siscod").toString())
                : null;

        Integer invnumAper = request.get("invnumAper") != null
                ? Integer.parseInt(request.get("invnumAper").toString())
                : null;

        List<Map<String, Object>> result = service.listarTransaccionesCredito(
                fechaInicio,
                fechaFin,
                siscod,
                invnumAper
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // LISTAR NOTAS DE CRÉDITO APLICADAS
    // =========================================
    @PostMapping("/listarNotaCreditoAplicadas")
    public ResponseEntity<?> listarNotaCreditoAplicadas(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio") != null
                ? request.get("fechaInicio").toString()
                : null;

        String fechaFin = request.get("fechaFin") != null
                ? request.get("fechaFin").toString()
                : null;

        Integer siscod = request.get("siscod") != null
                ? Integer.parseInt(request.get("siscod").toString())
                : null;

        Integer invnumAper = request.get("invnumAper") != null
                ? Integer.parseInt(request.get("invnumAper").toString())
                : null;

        List<Map<String, Object>> result = service.listarNotaCreditoAplicadas(
                fechaInicio,
                fechaFin,
                siscod,
                invnumAper
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // COMERCIAL - LISTAR INGRESOS
    // =========================================
    @PostMapping("/listarIngresos")
    public ResponseEntity<?> listarIngresos(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio") != null
                ? request.get("fechaInicio").toString()
                : null;

        String fechaFin = request.get("fechaFin") != null
                ? request.get("fechaFin").toString()
                : null;

        Integer siscod = request.get("siscod") != null
                ? Integer.parseInt(request.get("siscod").toString())
                : null;

        Integer invnumAper = request.get("invnumAper") != null
                ? Integer.parseInt(request.get("invnumAper").toString())
                : null;

        List<Map<String, Object>> result = service.listarIngresos(
                fechaInicio,
                fechaFin,
                siscod,
                invnumAper
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // COMERCIAL - LISTAR EGRESOS
    // =========================================
    @PostMapping("/listarEgresos")
    public ResponseEntity<?> listarEgresos(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio") != null
                ? request.get("fechaInicio").toString()
                : null;

        String fechaFin = request.get("fechaFin") != null
                ? request.get("fechaFin").toString()
                : null;

        Integer siscod = request.get("siscod") != null
                ? Integer.parseInt(request.get("siscod").toString())
                : null;

        Integer invnumAper = request.get("invnumAper") != null
                ? Integer.parseInt(request.get("invnumAper").toString())
                : null;

        List<Map<String, Object>> result = service.listarEgresos(
                fechaInicio,
                fechaFin,
                siscod,
                invnumAper
        );

        return ResponseEntity.ok(result);
    }
}
