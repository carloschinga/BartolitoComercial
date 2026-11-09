package com.bartolito.comercial.service;


import com.bartolito.comercial.repository.LiquidacionClinicaRepository;
import com.bartolito.comercial.repository.NotaCreditoInsanorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NotaCreditoInsanorService {

    @Autowired
    private NotaCreditoInsanorRepository repository;

    // =========================================
    // NOTAS DE CREDITO - LISTAR CABECERA
    // =========================================
    public List<Map<String, Object>> listarNotasCredito(
            Integer invnumAper,
            Integer invnum
    ) {

        return repository.listarNotasCredito(
                invnumAper,
                invnum
        );
    }

    // =========================================
    // NOTAS DE CREDITO - LISTAR DETALLE
    // =========================================
    public List<Map<String, Object>> listarNotasCreditoDetalle(
            Integer nconum
    ) {

        return repository.listarNotasCreditoDetalle(
                nconum
        );
    }

    // =========================================
    // CONCEPTOS - LISTAR
    // =========================================
    public List<Map<String, Object>> listarConceptos(
            Integer invnum
    ) {

        return repository.listarConceptos(
                invnum
        );
    }

    // =========================================
    // FACTURAS - LISTAR
    // =========================================
    public List<Map<String, Object>> listarCabeceraFactura(
            Integer invnum
    ) {

        return repository.listarCabeceraFactura(
                invnum
        );
    }
}
