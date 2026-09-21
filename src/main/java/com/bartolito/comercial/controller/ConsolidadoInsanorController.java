package com.bartolito.comercial.controller;


import com.bartolito.comercial.service.ConsolidadoCajaService;
import com.bartolito.comercial.service.ConsolidadoInsanorService;
import com.bartolito.comercial.util.dto.consolidadoCaja.ConsolidadoCajaBatchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/consolidadoInsanor")
public class ConsolidadoInsanorController {

    @Autowired
    private ConsolidadoInsanorService service;

    // =========================================
    // CONSOLIDADO CAJA - BATCH
    // =========================================
    @PostMapping("/saveOrUpdateBatch")
    public ResponseEntity<?> guardarConsolidadoCajaBatch(
            @RequestBody ConsolidadoCajaBatchResponse request
    ) {

        List<Map<String, Object>> result =
                service.guardarConsolidadoCajaBatch(request);

        return ResponseEntity.ok(result);
    }

    // =========================================
    // CONSOLIDADO CAJA - LISTAR
    // =========================================
    @PostMapping("/listarConsolidadoCaja")
    public ResponseEntity<?> listarConsolidadoCaja(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio") != null
                ? request.get("fechaInicio").toString()
                : null;

        String fechaFin = request.get("fechaFin") != null
                ? request.get("fechaFin").toString()
                : null;

        List<Map<String, Object>> result =
                service.listarConsolidadoCaja(
                        fechaInicio,
                        fechaFin
                );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // CONSOLIDADO CAJA - SUMATORIA FORMA PAGO TOTAL
    // =========================================
    @PostMapping("/listarSumatoriaFormaPago")
    public ResponseEntity<?> listarFormaPagoCompleto(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio") != null
                ? request.get("fechaInicio").toString()
                : null;

        String fechaFin = request.get("fechaFin") != null
                ? request.get("fechaFin").toString()
                : null;

        Integer invnumAper = request.get("invnumAper") != null
                ? Integer.parseInt(request.get("invnumAper").toString())
                : null;

        List<Map<String, Object>> result =
                service.listarFormaPagoCompleto(
                        fechaInicio,
                        fechaFin,
                        invnumAper
                );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // CERRAR CONSOLIDADOS
    // =========================================
    @PostMapping("/cerrarConsolidado")
    public ResponseEntity<?> cerrarConsolidado(
            @RequestBody Map<String, Object> request
    ) {

        String consolidadoCajaIds = request.get("consolidadoCajaIds") != null
                ? request.get("consolidadoCajaIds").toString()
                : null;

        String usuario = request.get("usuario") != null
                ? request.get("usuario").toString()
                : null;

        List<Map<String, Object>> result =
                service.cerrarConsolidado(
                        consolidadoCajaIds,
                        usuario
                );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // ACTUALIZAR OBSERVACIÓN
    // =========================================
    @PostMapping("/actualizarObservacion")
    public ResponseEntity<?> actualizarObservacion(
            @RequestBody Map<String, Object> request
    ) {

        Integer invnumAper = request.get("invnumAper") != null
                ? Integer.parseInt(request.get("invnumAper").toString())
                : null;

        String observacion = request.get("observacion") != null
                ? request.get("observacion").toString()
                : null;

        String usuario = request.get("usuario") != null
                ? request.get("usuario").toString()
                : null;

        List<Map<String, Object>> result =
                service.actualizarObservacion(
                        invnumAper,
                        observacion,
                        usuario
                );

        return ResponseEntity.ok(result);
    }
}
