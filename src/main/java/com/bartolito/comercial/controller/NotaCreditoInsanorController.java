package com.bartolito.comercial.controller;

import com.bartolito.comercial.service.LiquidacionClinicaService;
import com.bartolito.comercial.service.NotaCreditoInsanorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notaCreditoInsanor")
public class NotaCreditoInsanorController {

    @Autowired
    private NotaCreditoInsanorService service;

    // =========================================
    // NOTAS DE CREDITO - LISTAR CABECERA
    // =========================================
    @PostMapping("/listarNotasCredito")
    public ResponseEntity<?> listarNotasCredito(
            @RequestBody Map<String, Object> request
    ) {

        Integer invnumAper = request.get("invnumAper") != null
                ? Integer.parseInt(request.get("invnumAper").toString())
                : null;

        Integer invnum = request.get("invnum") != null
                ? Integer.parseInt(request.get("invnum").toString())
                : null;

        List<Map<String, Object>> result =
                service.listarNotasCredito(
                        invnumAper,
                        invnum
                );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // NOTAS DE CREDITO - LISTAR DETALLE
    // =========================================
    @PostMapping("/listarNotasCreditoDetalle")
    public ResponseEntity<?> listarNotasCreditoDetalle(
            @RequestBody Map<String, Object> request
    ) {

        Integer nconum = request.get("nconum") != null
                ? Integer.parseInt(request.get("nconum").toString())
                : null;

        List<Map<String, Object>> result =
                service.listarNotasCreditoDetalle(
                        nconum
                );

        return ResponseEntity.ok(result);
    }


    // =========================================
    // CONCEPTOS - LISTAR
    // =========================================
    @PostMapping("/listarConceptos")
    public ResponseEntity<?> listarConceptos(
            @RequestBody Map<String, Object> request
    ) {

        Integer invnum = request.get("invnum") != null
                ? Integer.parseInt(request.get("invnum").toString())
                : null;

        List<Map<String, Object>> result =
                service.listarConceptos(
                        invnum
                );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // FACTURA - CABECERA
    // =========================================
    @PostMapping("/listarCabeceraFactura")
    public ResponseEntity<?> listarCabeceraFactura(
            @RequestBody Map<String, Object> request
    ) {

        Integer invnum = request.get("invnum") != null
                ? Integer.parseInt(request.get("invnum").toString())
                : null;

        List<Map<String, Object>> result =
                service.listarCabeceraFactura(
                        invnum
                );

        return ResponseEntity.ok(result);
    }

}
