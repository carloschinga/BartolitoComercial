package com.bartolito.comercial.controller;

import com.bartolito.comercial.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;


    @PostMapping("/paginasxgrupo")
    public String listarPaginasXGrupo(
            @RequestHeader("Authorization") String authorization,
            @RequestBody Map<String, String> menuData){


        // Obtiene "tipo" o "N" por defecto si no existe
        String tipo = menuData.getOrDefault("tipo", "N");

        // Quita el prefijo "Bearer " del token
        String token = authorization.replace("Bearer ", "");

        return menuService.obtenerPaginasPorGrupo(tipo, token);

    }
}
