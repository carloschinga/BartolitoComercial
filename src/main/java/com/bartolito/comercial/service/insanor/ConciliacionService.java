package com.bartolito.comercial.service.insanor;

import com.bartolito.comercial.repository.insanor.ConciliacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ConciliacionService {

    @Autowired
    private ConciliacionRepository repository;

    // =========================================
    // LISTAR
    // =========================================
    public List<Map<String, Object>> listadoPrueba(
    ) {

        return repository.listadoPrueba(
        );
    }

}
