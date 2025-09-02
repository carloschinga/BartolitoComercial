package com.bartolito.comercial.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class ComerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // METODO PARA EJECUTAR EL SP QUE AGREGA UN DESEMPEÑO DE VENTAS
    public String agregarDesempenioJson(String desobj, int usecod, String mesano,
                                        String tipo, String estado, int hecAct) {
        String sql = "EXEC sp_bart_desempenio_meta_venta_agregar " +
                "@desobj = ?, @usecod = ?, @mesano = ?, @estado = ?, @tipo = ?, @hecAct = ?";

        return jdbcTemplate.queryForObject(sql, String.class, desobj, usecod, mesano, tipo, estado, hecAct);
    }

    // METODO PARA EJECUTAR EL SP QUE MODIFICA UN DESEMPEÑO DE VENTAS
    public String modificarDesempenioJson(int cuotVtaId, String desobj, int usecod, String mesano,
                                          String estado, String tipo, int hecAct) {

        String sql = "EXEC sp_bart_desempenio_meta_venta_modificar " +
                "@CuotVtaId = ?, @desobj = ?, @usecod = ?, " +
                "@mesano = ?, @estado = ?, @tipo = ?, @hecAct = ?";

        // Ejecutamos la consulta y devolvemos el resultado
        return jdbcTemplate.queryForObject(sql, String.class, cuotVtaId, desobj, usecod, mesano, estado, tipo, hecAct);
    }

    // METODO PARA EJECUTAR EL SP QUE LISTA LOS DESEMPEÑOS DE VENTAS
    public String listarDesempenioJson() {
        // Query for list devuelve vacío si no hay resultados
        List<String> result = jdbcTemplate.queryForList("EXEC sp_bart_desempenio_meta_venta_listar", String.class);

        // Si no hay resultados, devolvemos un JSON vacío con la misma estructura
        if (result.isEmpty()) {
            return "{\"data\":[]}";
        }

        return String.join("", result);
    }

    public String seleccionarJson(int cuotVtaId) {
        String sql = "EXEC sp_bart_desempenio_meta_venta_seleccionar @CuotVtaId = ?";

        List<String> result = jdbcTemplate.queryForList(sql, String.class, cuotVtaId);

        return result.isEmpty() ? "{}" : result.get(0);
    }

    // Metodo para seleccionar la meta a evaluar y marcarla como activa
    public String seleccionarMetaJson(int cuotVtaId) {
        String sql = "EXEC sp_bart_desempenio_meta_venta_selecionarMeta @CuotVtaId = ?";

        // Ejecutamos la consulta y devolvemos el resultado en formato JSON
        List<String> result = jdbcTemplate.queryForList(sql, String.class, cuotVtaId);

        // Devuelve exactamente el JSON del SP
        return result.isEmpty() ? "{}" : result.get(0);
    }

    public String eliminarJson(int cuotVtaId) {
        String sql = "EXEC sp_bart_desempenio_meta_venta_eliminar  @CuotVtaId = ?";
        List<String> result = jdbcTemplate.queryForList(sql, String.class, cuotVtaId);

        // Devuelve exactamente el JSON del SP
        return result.isEmpty() ? "{}" : result.get(0);
    }


    /****************************METODOS PARA LA META FARMACIA******************/
    public String listarMetaVentaFarmacia(int cuotVtaId) {
        String sql = "EXEC sp_bart_desempenio_meta_venta_farmacia_listar @CuotVtaId = ?";
        // devuelve todas las filas de la primera columna como List<String>
        List<String> result = jdbcTemplate.queryForList(sql, String.class, cuotVtaId);

        // concatenamos en caso de que haya más de una fila
        return String.join("", result);
    }


    public String modificarMetaFarmaciaJson(int cuotVtaId, int siscod, BigDecimal cantidad, BigDecimal porc_estra, int usecod) {
        try {
            String sql = "EXEC sp_bart_desempenio_meta_venta_farmacia_modificar ?, ?, ?, ?, ?";
            List<String> result = jdbcTemplate.queryForList(sql, String.class,
                    cuotVtaId, siscod, cantidad, porc_estra, usecod);

            // concatenamos en caso de que haya más de una fila
            return String.join("", result);

        } catch (DataAccessException dae) {
            System.err.println("Error al ejecutar el SP: " + dae.getMessage());
            return "{\"resultado\":\"error\",\"mensaje\":\"Error en la base de datos\",\"data\":[]}";
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            return "{\"resultado\":\"error\",\"mensaje\":\"Error inesperado\",\"data\":[]}";
        }
    }


    public String listarDashboardJson() {
        try {
            String sql = "EXEC sp_bart_desempenio_meta_venta_dashboard";
            List<String> result = jdbcTemplate.queryForList(sql, String.class);
            return String.join("", result); // concatenar filas si hubiera más de una
        } catch (DataAccessException dae) {
            System.err.println("Error al ejecutar SP dashboard: " + dae.getMessage());
            return "{\"resultado\":\"error\",\"mensaje\":\"Error en la base de datos\",\"data\":[]}";
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            return "{\"resultado\":\"error\",\"mensaje\":\"Error inesperado\",\"data\":[]}";
        }
    }






}
