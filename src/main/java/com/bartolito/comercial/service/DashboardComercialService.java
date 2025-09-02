package com.bartolito.comercial.service;

import com.bartolito.comercial.repository.DashboardComercialRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardComercialService {

    private final DashboardComercialRepository dashboardRepository;

    // Inyección de dependencias por constructor
    public DashboardComercialService(DashboardComercialRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    public String listarDashboard() {
        return dashboardRepository.listarDashboardJson();
    }
}
