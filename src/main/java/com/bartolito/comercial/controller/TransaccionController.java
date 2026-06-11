package com.bartolito.comercial.controller;

import com.bartolito.comercial.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/transaccion")
public class TransaccionController {

    @Autowired
    private TransaccionService service;

    // =========================================
    // LISTAR TRANSACCIONES
    // =========================================
    @PostMapping("/listarTransacciones")
    public ResponseEntity<?> listarTransacciones(
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

        List<Map<String, Object>> result = service.listarTransacciones(
                fechaInicio,
                fechaFin,
                invnumAper,
                siscod
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // LISTAR VENTAS AL CREDITO
    // =========================================
    @PostMapping("/listarVentasCredito")
    public ResponseEntity<?> listarVentasCredito(
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

        List<Map<String, Object>> result = service.listarVentasCredito(
                fechaInicio,
                fechaFin,
                invnumAper,
                siscod
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // LISTAR APERTURAS DE CAJA
    // =========================================
    @PostMapping("/listarApertura")
    public ResponseEntity<?> listarAperturasCaja(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio").toString();
        String fechaFin = request.get("fechaFin").toString();

        Integer siscod = request.get("siscod") != null
                ? Integer.parseInt(request.get("siscod").toString())
                : null;

        List<Map<String, Object>> result = service.listarAperturasCaja(
                fechaInicio,
                fechaFin,
                siscod
        );

        return ResponseEntity.ok(result);
    }


    // =========================================
    // LISTAR METODOS DE PAGO
    // =========================================
    @PostMapping("/listarMetodosPago")
    public ResponseEntity<?> listarMetodosPago(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio").toString();
        String fechaFin = request.get("fechaFin").toString();

        List<Map<String, Object>> result = service.listarMetodosPago(
                fechaInicio,
                fechaFin
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // LISTAR SUMATORIA FORMA PAGO
    // =========================================
    @PostMapping("/listarSumatoriaFormaPago")
    public ResponseEntity<?> listarSumatoriaFormaPago(
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

        List<Map<String, Object>> result = service.listarSumatoriaFormaPago(
                fechaInicio,
                fechaFin,
                invnumAper,
                siscod
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // LISTAR NOTAS DE CREDITO
    // =========================================
    @PostMapping("/listarNotaCredito")
    public ResponseEntity<?> listarNotaCredito(
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

        List<Map<String, Object>> result = service.listarNotaCredito(
                fechaInicio,
                fechaFin,
                invnumAper,
                siscod
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // OBTENER CABECERA NOTA CREDITO
    // =========================================
    @PostMapping("/obtenerCabeceraNotaCredito")
    public ResponseEntity<?> obtenerCabeceraNotaCredito(
            @RequestBody Map<String, Object> request
    ) {

        Integer invnum = Integer.parseInt(
                request.get("invnum").toString()
        );

        List<Map<String, Object>> result =
                service.obtenerCabeceraNotaCredito(invnum);

        return ResponseEntity.ok(result);
    }

    // =========================================
    // OBTENER DETALLE PRODUCTOS NOTA CREDITO
    // =========================================
    @PostMapping("/obtenerDetalleProductosNotaCredito")
    public ResponseEntity<?> obtenerDetalleProductosNotaCredito(
            @RequestBody Map<String, Object> request
    ) {

        Integer invnum = Integer.parseInt(
                request.get("invnum").toString()
        );

        List<Map<String, Object>> result =
                service.obtenerDetalleProductosNotaCredito(invnum);

        return ResponseEntity.ok(result);
    }

    // =========================================
    //  OBTENER DETALLE APLICADO
    // =========================================
    @PostMapping("/obtenerDetalleAplicado")
    public ResponseEntity<?> obtenerDetalleAplicado(
            @RequestBody Map<String, Object> request
    ) {

        Integer invnum = Integer.parseInt(
                request.get("invnum").toString()
        );

        List<Map<String, Object>> result =
                service.obtenerDetalleAplicado(invnum);

        return ResponseEntity.ok(result);
    }

    // =========================================
    // LISTAR HUERFANOS
    // =========================================
    @PostMapping("/listarHuerfanos")
    public ResponseEntity<?> listarHuerfanos(
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

        List<Map<String, Object>> result = service.listarHuerfanos(
                fechaInicio,
                fechaFin,
                invnumAper,
                siscod
        );

        return ResponseEntity.ok(result);
    }
}
