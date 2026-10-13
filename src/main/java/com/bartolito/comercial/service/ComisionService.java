package com.bartolito.comercial.service;

import com.bartolito.comercial.repository.ComisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ComisionService {

    @Autowired
    private ComisionRepository repository;

    // =========================================
    // COMISIONES - LISTAR METAS DE VENTAS
    // =========================================
    public List<Map<String, Object>> listarMetasVentas() {

        return repository.listarMetasVentas();
    }

    // =========================================
    // COMISIONES - LISTAR PERSONAL MATRICULADO
    // =========================================
    public List<Map<String, Object>> listarPersonalMatriculado(
            Integer siscod,
            Integer cuotVtaId
    ) {

        return repository.listarPersonalMatriculado(
                siscod,
                cuotVtaId
        );
    }
}
