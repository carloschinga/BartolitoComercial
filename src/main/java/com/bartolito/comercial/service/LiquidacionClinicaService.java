package com.bartolito.comercial.service;

import com.bartolito.comercial.repository.LiquidacionCajaRepository;
import com.bartolito.comercial.repository.LiquidacionClinicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LiquidacionClinicaService {

    @Autowired
    private LiquidacionClinicaRepository repository;

    // =========================================
    // CIERRES DE CAJA - LISTAR
    // =========================================
    public List<Map<String, Object>> listarCierresCaja(
            String fechaInicio,
            String fechaFin,
            Integer usecod
    ) {

        return repository.listarCierresCaja(
                fechaInicio,
                fechaFin,
                usecod
        );
    }

    // =========================================
    // CIERRES DE CAJA - METODOS PAGO
    // =========================================
    public List<Map<String, Object>> listarMetodosPago(
            String fechaInicio,
            String fechaFin,
            Integer invnumAper
    ) {

        return repository.listarMetodosPago(
                fechaInicio,
                fechaFin, invnumAper
        );
    }

    // =========================================
    // CIERRE DE CAJA - LISTAR CABECERA
    // =========================================
    public List<Map<String, Object>> listarCabecera(
            Integer invnumAper
    ) {

        return repository.listarCabecera(
                invnumAper
        );
    }

    // =========================================
    // CIERRE DE CAJA - LISTAR FORMAS DE PAGO
    // =========================================
    public List<Map<String, Object>> listarFormasPago(
            Integer invnum
    ) {

        return repository.listarFormasPago(
                invnum
        );
    }
}
