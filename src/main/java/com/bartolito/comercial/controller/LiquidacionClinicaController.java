package com.bartolito.comercial.controller;

import com.bartolito.comercial.service.LiquidacionClinicaService;
import com.bartolito.comercial.service.LiquidacionPDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/liquidacionClinica")
public class LiquidacionClinicaController {

    @Autowired
    private LiquidacionClinicaService service;

    @Autowired
    private LiquidacionPDFService liquidacionPDFService;

    // =========================================
    // LISTAR FORMAS DE PAGO
    // =========================================
    @PostMapping("/formasPago")
    public ResponseEntity<?> listarFormasPago(
            @RequestBody Map<String, Object> request
    ) {

        Integer invnum = request.get("invnum") != null
                ? Integer.parseInt(request.get("invnum").toString())
                : null;

        List<Map<String, Object>> result =
                service.listarFormasPago(
                        invnum
                );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // LISTAR FORMAS DE PAGO
    // =========================================
    @PostMapping("/metodosPago")
    public ResponseEntity<?> listarMetodosPago(
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
                service.listarMetodosPago(
                        fechaInicio, fechaFin, invnumAper
                );

        return ResponseEntity.ok(result);
    }


    // =========================================
    // LISTAR CABECERA
    // =========================================
    @PostMapping("/cabecera")
    public ResponseEntity<?> listarCabecera(
            @RequestBody Map<String, Object> request
    ) {

        Integer invnumAper = request.get("invnumAper") != null
                ? Integer.parseInt(request.get("invnumAper").toString())
                : null;

        List<Map<String, Object>> result =
                service.listarCabecera(
                        invnumAper
                );

        return ResponseEntity.ok(result);
    }


    // =========================================
    // LISTAR CIERRES DE CAJA
    // =========================================
    @PostMapping("/listarCierres")
    public ResponseEntity<?> listarCierresCaja(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio") != null
                ? request.get("fechaInicio").toString()
                : null;

        String fechaFin = request.get("fechaFin") != null
                ? request.get("fechaFin").toString()
                : null;

        Integer usecod = request.get("usecod") != null
                ? Integer.parseInt(request.get("usecod").toString())
                : null;

        List<Map<String, Object>> result =
                service.listarCierresCaja(
                        fechaInicio,
                        fechaFin,
                        usecod
                );

        return ResponseEntity.ok(result);
    }

    @GetMapping("/reporte/liquidacion")
    public ResponseEntity<byte[]> generarLiquidacionPDF(
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin,
            @RequestParam Integer invnumAper) {
        try {
            byte[] pdfBytes = liquidacionPDFService.generarPDFLiquidacion(fechaInicio, fechaFin, invnumAper);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment",
                    "liquidacion_" + fechaInicio + "_" + fechaFin + ".pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
