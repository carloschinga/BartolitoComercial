package com.bartolito.comercial.controller;

import com.bartolito.comercial.service.DashboardComercialService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard") // ruta base
public class DashboardComercialController {

    private final DashboardComercialService dashboardService;

    // Constructor para inyección de dependencias
    public DashboardComercialController(DashboardComercialService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/listar")
    public ResponseEntity<String> listarDashboard() {
        try {
            String result = dashboardService.listarDashboard();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"resultado\":\"error\",\"mensaje\":\"Error interno en el servidor\",\"data\":[]}");
        }
    }
}
