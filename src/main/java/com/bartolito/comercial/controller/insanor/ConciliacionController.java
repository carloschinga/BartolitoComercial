package com.bartolito.comercial.controller.insanor;

import com.bartolito.comercial.service.insanor.ConciliacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/insanor/conciliacion")
public class ConciliacionController {

    @Autowired
    private ConciliacionService service;

    // =========================================
    // LISTAR
    // =========================================
    @PostMapping("/listado-prueba")
    public ResponseEntity<?> listadoPrueba(
    ) {

        List<Map<String, Object>> result =
                service.listadoPrueba(
                );

        return ResponseEntity.ok(result);
    }
}
