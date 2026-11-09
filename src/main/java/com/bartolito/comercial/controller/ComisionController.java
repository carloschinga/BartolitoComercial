package com.bartolito.comercial.controller;

import com.bartolito.comercial.service.ComisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comision")
public class ComisionController {

    @Autowired
    private ComisionService service;

    // =========================================
    // COMISIONES - LISTAR METAS DE VENTAS
    // =========================================
    @PostMapping("/listarMetasVentas")
    public ResponseEntity<?> listarMetasVentas() {

        List<Map<String, Object>> result = service.listarMetasVentas();

        return ResponseEntity.ok(result);
    }

    // =========================================
    // COMISIONES - LISTAR PERSONAL MATRICULADO
    // =========================================
    @PostMapping("/listarPersonalMatriculado")
    public ResponseEntity<?> listarPersonalMatriculado(
            @RequestBody Map<String, Object> request
    ) {

        Integer siscod = request.get("siscod") != null
                ? Integer.parseInt(request.get("siscod").toString())
                : null;

        Integer cuotVtaId = request.get("cuotVtaId") != null
                ? Integer.parseInt(request.get("cuotVtaId").toString())
                : null;

        List<Map<String, Object>> result = service.listarPersonalMatriculado(
                siscod,
                cuotVtaId
        );

        return ResponseEntity.ok(result);
    }
}
