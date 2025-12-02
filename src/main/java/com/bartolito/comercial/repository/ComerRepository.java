package com.bartolito.comercial.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class ComerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String string;

    // METODO PARA EJECUTAR EL SP QUE AGREGA UN DESEMPEÑO DE VENTAS
    public String agregarDesempenioJson(String desobj, int usecod, String mesano, String tipo, String estado, int hecAct) {
        String sql = "EXEC sp_bart_desempenio_meta_venta_agregar " + "@desobj = ?, @usecod = ?, @mesano = ?, @estado = ?, @tipo = ?, @hecAct = ?";

        return jdbcTemplate.queryForObject(sql, String.class, desobj, usecod, mesano, tipo, estado, hecAct);
    }

    // METODO PARA EJECUTAR EL SP QUE MODIFICA UN DESEMPEÑO DE VENTAS
    public String modificarDesempenioJson(int cuotVtaId, String desobj, int usecod, String mesano, String estado, String tipo, int hecAct) {

        String sql = "EXEC sp_bart_desempenio_meta_venta_modificar " + "@CuotVtaId = ?, @desobj = ?, @usecod = ?, " + "@mesano = ?, @estado = ?, @tipo = ?, @hecAct = ?";

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
            List<String> result = jdbcTemplate.queryForList(sql, String.class, cuotVtaId, siscod, cantidad, porc_estra, usecod);

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


    /*================================MÉTODOS PARA ROLES===================================*/
    public String listarRolesJson() {
        try {
            String sql = "EXEC sp_bart_desempenio_vendedor_rol_listar";

            List<String> result = jdbcTemplate.queryForList(sql, String.class);

            return String.join("", result);

        } catch (DataAccessException dae) {
            return "{ \"resultado\": \"error\", \"mensaje\": \"Error de acceso a la base de datos\", \"data\": [] }";
        } catch (Exception e) {
            return "{ \"resultado\": \"error\", \"mensaje\": \"Ocurrió un error inesperado\", \"data\": [] }";
        }
    }

    public String listarSucursalesMonto(int cuotVtaId) {
        try {
            String sql = "EXEC sp_bart_desempenio_meta_venta_farmacia_listar ?";

            List<String> result = jdbcTemplate.queryForList(sql, String.class, cuotVtaId);

            return String.join("", result);

        } catch (DataAccessException dae) {
            return "{ \"resultado\": \"error\", \"mensaje\": \"Error de acceso a la base de datos\", \"data\": [] }";
        } catch (Exception e) {
            return "{ \"resultado\": \"error\", \"mensaje\": \"Ocurrió un error inesperado\", \"data\": [] }";
        }
    }

    public String listarRolesporFarmacia(int CuotVtaMesId) {
        try {
            String sql = "EXEC sp_bart_desempenio_vendedor_rol_farmacia_listar ?";

            List<String> result = jdbcTemplate.queryForList(sql, String.class, CuotVtaMesId);

            return String.join("", result);

        } catch (DataAccessException dae) {
            return "{ \"resultado\": \"error\", \"mensaje\": \"Error de acceso a la base de datos\", \"data\": [] }";
        } catch (Exception e) {
            return "{ \"resultado\": \"error\", \"mensaje\": \"Ocurrió un error inesperado\", \"data\": [] }";
        }
    }

    public String modificarRolesXml(String xml) {
        try {
            String sql = "EXEC sp_bart_desempenio_vendedor_rol_modificar @xml = ?";
            String result = jdbcTemplate.queryForObject(sql, new Object[]{xml}, String.class);
            return (result == null || result.isEmpty()) ? "{\"resultado\":\"error\",\"mensaje\":\"No se obtuvo respuesta\"}" : result;

        } catch (DataAccessException dae) {
            return "{ \"resultado\": \"error\", \"mensaje\": \"Error de acceso a la base de datos\", \"data\": [] }";
        } catch (Exception e) {
            return "{ \"resultado\": \"error\", \"mensaje\": \"Ocurrió un error inesperado\", \"data\": [] }";
        }
    }

    public String listarVendedores() {
        try {
            // SQL Server SP
            String sql = "EXEC sp_bart_desempenio_vendedor_listar";

            List<String> result = jdbcTemplate.queryForList(sql, String.class);

            return String.join("", result);

        } catch (DataAccessException dae) {
            return "{ \"resultado\": \"error\", \"mensaje\": \"Error de acceso a SQL Server\", \"data\": [] }";
        } catch (Exception e) {
            return "{ \"resultado\": \"error\", \"mensaje\": \"Error inesperado en la API\", \"data\": [] }";
        }
    }


    public String listarVendedoresAsignados(Integer cuotVtaRolId) {
        try {
            String sql = "EXEC sp_bart_desempenio_vendedor_rol_vendedor_listar @CuotVtaRolId = ?";
            List<String> result = jdbcTemplate.queryForList(sql, String.class, cuotVtaRolId);

//            if (result.isEmpty()) {
//                return "{ \"resultado\": \"ok\", \"vendedores\": [] }";
//            }

            return String.join("", result);

        } catch (DataAccessException dae) {
            return "{ \"resultado\": \"error\", \"mensaje\": \"Error de acceso a SQL Server\", \"vendedores\": [] }";
        } catch (Exception e) {
            return "{ \"resultado\": \"error\", \"mensaje\": \"Error inesperado en la API\", \"vendedores\": [] }";
        }
    }


    public String modificarVendedoresXml(String xml) {
        try {
            String sql = "EXEC sp_bart_desempenio_vendedor_rol_vendedor_modificar @xml = ?";
            String result = jdbcTemplate.queryForObject(sql, new Object[]{xml}, String.class);

            if (result == null || result.isEmpty()) {
                return "{\"resultado\":\"error\",\"mensaje\":\"No se obtuvo respuesta del SP\"}";
            }

            return result;

        } catch (DataAccessException dae) {
            return "{ \"resultado\": \"error\", \"mensaje\": \"Error de acceso a SQL Server\" }";
        } catch (Exception e) {
            return "{ \"resultado\": \"error\", \"mensaje\": \"Error inesperado en la API\" }";
        }
    }

    /*==================Código para el Dashboard Vendedores===============*/

    public String obtenerFarmacias() {
        String sql = "EXEC sp_bart_desempenio_farmacias_listar";
        try {
            List<String> result = jdbcTemplate.queryForList(sql, String.class);

            if (result.isEmpty()) {
                return "{\"resultado\":\"error\",\"farmacias\":[]}";
            }
            return String.join("", result);

        } catch (DataAccessException dae) {
            return "{ \"resultado\": \"error\", \"mensaje\": \"Error de acceso a SQL Server\" }";
        } catch (Exception e) {
            return "{ \"resultado\": \"error\", \"mensaje\": \"Error inesperado en la API\" }";
        }
    }

    public String listarVendedoresLocalJson(int siscod) {
        String sql = "EXEC sp_bart_desempenio_vendedor_farmacia_listar @siscod = ?";

        try {
            List<String> result = jdbcTemplate.queryForList(sql, String.class, siscod);
            if (result.isEmpty()) {
                return "{ \"resultado\": \"error\", \"vendedores\": [] }";
            }
            return String.join("", result);

        } catch (DataAccessException dae) {
            return "{ \"resultado\": \"error\", \"mensaje\": \"Error de acceso a SQL Server\" }";
        } catch (Exception e) {
            return "{ \"resultado\": \"error\", \"mensaje\": \"Error inesperado en la API\" }";
        }
    }

    public String obtenerMetaVentaVendedorJson(int usecod) {
        String sql = "EXEC sp_bart_desempenio_meta_venta_dashboard_vendedor @usecod = ?";

        try {
            List<String> result = jdbcTemplate.queryForList(sql, String.class, usecod);

            if (result.isEmpty()) {
                return "{\"resultado\":\"error\",\"mensaje\":\"datos\",\"data\":[]}";
            }
            return String.join("", result);

        } catch (DataAccessException dae) {
            return "{ \"resultado\": \"error\", \"mensaje\": \"Error de acceso a SQL Server\" }";
        } catch (Exception e) {
            return "{ \"resultado\": \"error\", \"mensaje\": \"Error inesperado en la API\" }";
        }
    }

    public String listarVendedoresPorFarmaciaJson(int siscod) {

        String sql = "EXEC sp_bart_desempenio_meta_venta_dashboard_farmacia @siscod = ?";
        try {
            List<String> result = jdbcTemplate.queryForList(sql, String.class, siscod);

            if (result.isEmpty()) {
                // Si no hay datos, devuelvo un JSON con data vacío
                return "{ \"resultado\": \"ok\", \"mensaje\": \"sin datos\", \"data\": [] }";
            }
            return String.join("", result);

        } catch (DataAccessException dae) {
            return "{ \"resultado\": \"error\", \"mensaje\": \"Error de acceso a SQL Server\" }";
        } catch (Exception e) {
            return "{ \"resultado\": \"error\", \"mensaje\": \"Error inesperado en la API\" }";
        }
    }


    /*==================================GESTIÓN DE UMBRALES========================================*/

    public String listarUmbralesJson() {
        String sql = "EXEC sp_bart_desempenio_umbrales_listar";
        try {
            List<String> result = jdbcTemplate.queryForList(sql, String.class);

            if (result.isEmpty()) {
                return "{ \"resultado\": \"ok\", \"mensaje\": \"sin datos\", \"umbrales\": [] }";
            }
            return String.join("", result);

        } catch (DataAccessException dae) {
            return "{ \"resultado\": \"error\", \"mensaje\": \"Error de acceso a SQL Server\" }";
        } catch (Exception e) {
            return "{ \"resultado\": \"error\", \"mensaje\": \"Error inesperado en la API\" }";
        }
    }

    public String modificarUmbralesJson(int codumb, String nomumb, BigDecimal minpor, BigDecimal maxpor) {
        String sql = "EXEC sp_bart_desempenio_umbrales_modificar ?, ?, ?, ?";

        try {
            List<String> result = jdbcTemplate.queryForList(sql, String.class, codumb, nomumb, minpor, maxpor);

            // Devuelve el JSON que vino del SP
            return result.isEmpty() ? "{}" : result.get(0);

        } catch (DataAccessException dae) {
            return "{ \"resultado\": \"error\", " + "\"mensaje\": \"Error de acceso a SQL Server\" }";
        } catch (Exception e) {
            return "{ \"resultado\": \"error\", \"mensaje\": \"Error inesperado en la API\" }";
        }
    }

    /*=========================== OBJETIVO COMERCIAL DE PRODUCTOS ==============================*/

    public List<Map<String, Object>> obtenerProductos() {
        String sql = "EXEC sp_bart_desempenio_productos_listar";
        try {
            return jdbcTemplate.queryForList(sql);
        } catch (DataAccessException dae) {
            System.err.println("Error de acceso a datos: " + dae.getMessage());
            return Collections.emptyList();
        }
    }

    public String agregarMetaVentaProducto(String codpro, String tipo, BigDecimal unidades, BigDecimal monto, int cuotVtaId, int useId) {
        String sql = "EXEC sp_bart_desempenio_meta_venta_producto_agregar ?, ?, ?, ?, ?, ?";
        return jdbcTemplate.queryForObject(sql, String.class, codpro, tipo, unidades, monto, cuotVtaId, useId);
    }

    public String obtenerMetaVentaProductos(int cuotVtaId) {
        String sql = "EXEC sp_bart_desempenio_meta_venta_producto_listar ?";

        List<String> result = jdbcTemplate.queryForList(sql, String.class, cuotVtaId);

        if (result.isEmpty()) {
            return "{ \"resultado\": \"ok\", \"data\": [] }";
        }

        // El SP ya devuelve JSON, así que unimos las filas
        return String.join("", result);
    }

    public String eliminarMetaVentaProducto(String codpro, int cuotVtaId) {
        String sql = "EXEC sp_bart_desempenio_meta_venta_producto_eliminar ?, ?";
        return jdbcTemplate.queryForObject(sql, String.class, codpro, cuotVtaId);
    }

    public String agregarProductoPermanente(String codpro, int cantpro, int cuotVtaId) {
        String sql = "EXEC sp_bart_desempenio_meta_venta_producto_perm_agregar @codpro = ?, @cantpro = ?, @cuotVtaId = ?";
        return jdbcTemplate.queryForObject(sql, String.class, codpro, cantpro, cuotVtaId);
    }

    public String eliminarProductoPermanente(String codpro, int cuotVtaId) {
        String sql = "EXEC sp_bart_desempenio_meta_venta_producto_perm_eliminar @codpro = ?, @cuotVtaId = ?";
        return jdbcTemplate.queryForObject(sql, String.class, codpro, cuotVtaId);
    }

    public String obtenerSucursalesProductosDetalle() {
        String sql = "EXEC sp_bart_desempenio_meta_venta_producto_sucursal";
        return jdbcTemplate.queryForObject(sql, String.class);
    }

    public String obtenerObjetivoProductosDetalle(int cuotVtaId, String codpro) {
        String sql = "EXEC sp_bart_desempenio_meta_venta_producto_detalle_listar ?, ?";
        return jdbcTemplate.queryForObject(sql, String.class, cuotVtaId, codpro);
    }


    public String agregarObjetivoProductosDetalle(int cuotVtaId, String codpro, int sucurId) {
        String sql = "EXEC sp_bart_desempenio_meta_venta_producto_detalle_agregar ?, ?, ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{cuotVtaId, codpro, sucurId}, String.class);
    }

    public String eliminarObjetivoProductosDetalle(int cuotVtaId, String codpro, int sucurId) {
        String sql = "EXEC sp_bart_desempenio_meta_venta_producto_detalle_eliminar ?, ?, ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{cuotVtaId, codpro, sucurId}, String.class);
    }

    public String modificarUnidades(int cuotVtaId, String codpro, BigDecimal unidades, BigDecimal monto) {
        String sql = "EXEC sp_bart_desempenio_meta_venta_producto_actualizar_unidades ?, ?, ?, ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{cuotVtaId, codpro, unidades, monto}, String.class);
    }

    public List<Map<String, Object>> obtenerDashboardProductoporProducto(int siscod, int usecod) {
        String sql = "EXEC sp_bart_desempenio_meta_venta_dashboard_producto_x_producto @siscod = ?, @usecod = ?";
        return jdbcTemplate.queryForList(sql, siscod, usecod);
    }

    public List<Map<String, Object>> obtenerDashboardProductoporVendedor(int siscod) {
        String sql = "EXEC sp_bart_desempenio_meta_venta_dashboard_producto_x_vendedor @siscod = ?";
        return jdbcTemplate.queryForList(sql, siscod);
    }

    public List<Map<String, Object>> obtenerDashboardProducto() {
        String sql = "EXEC sp_bart_desempenio_meta_venta_dashboard_producto";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> obtenerVendedoresConMetaProducto(int siscod) {
        String sql = "EXEC sp_bart_desempenio_vendedor_meta_producto @siscod = ?";
        return jdbcTemplate.queryForList(sql, siscod);
    }


    /*=========================== LISTADO DE CAJJAS CERRADAS ==============================*/

    public List<Map<String, Object>> obtenerCajasCerradas(Date fecha1, Date fecha2, int siscod, int usecod1) {
        String sql = "EXEC sp_bart_caja_cajas_cerradas_listar ?, ?, ?, ?";
        return jdbcTemplate.queryForList(sql, fecha1, fecha2, siscod, usecod1);
    }

    public List<Map<String, Object>> obtenerUsuariosCajasCerradas(String fecha1, String fecha2, int siscod) {
        String sql = "EXEC sp_bart_caja_cajas_cerradas_usuario_listar @fecha1 = ?, @fecha2 = ?, @siscod = ?";
        return jdbcTemplate.queryForList(sql, fecha1, fecha2, siscod);
    }

    /*=========================== DASHBOARD RESUMEN ==============================*/

    public List<Map<String, Object>> obtenerdDashnoardResumen(int cuotVtaId) {
        String sql = "EXEC sp_bart_desempenio_meta_venta_dashboard_resumen ?";
        return jdbcTemplate.queryForList(sql, cuotVtaId);
    }
}
