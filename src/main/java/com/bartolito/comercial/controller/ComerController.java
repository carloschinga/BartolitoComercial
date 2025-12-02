package com.bartolito.comercial.controller;

import com.bartolito.comercial.service.ComerService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
import java.sql.Date;

@RestController
@RequestMapping("/comercial")
public class ComerController {

    private final ComerService service;

    public ComerController(ComerService service) {
        this.service = service;
    }

    // Metodo para Agregar un desempeño
    @PostMapping("/agregar")
    public ResponseEntity<String> agregar(@RequestBody Map<String, Object> request) {

        try {
            String desobj = request.get("desobj").toString();
            int usecod = Integer.parseInt(request.get("usecod").toString());
            String mesano = request.get("mesano").toString();
            String estado = request.get("estado").toString();
            String tipo = request.get("tipo").toString();
            int hecAct = Integer.parseInt(request.get("hecAct").toString());

            // Llamar al servicio
            String result = service.agregarDesempenioJson(desobj, usecod, mesano, estado, tipo, hecAct);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON) // 👈 asegura JSON real
                    .body(result);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ocurrió un error al agregar el desempeño");
        }
    }

    // Endpoint para modificar un desempeño
    @PostMapping("/modificar")
    public ResponseEntity<String> modificar(@RequestBody Map<String, Object> request) {
        try {
            int cuotVtaId = Integer.parseInt(request.get("cuotVtaId").toString());
            String desobj = request.get("desobj").toString();
            int usecod = Integer.parseInt(request.get("usecod").toString());
            String mesano = request.get("mesano").toString();
            String estado = request.get("estado").toString();
            String tipo = request.get("tipo").toString();
            int hecAct = Integer.parseInt(request.get("hecAct").toString());

            String result = service.modificarDesempenioJson(cuotVtaId, desobj, usecod, mesano, estado, tipo, hecAct);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(result);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"resultado\":\"error\",\"mensaje\":\"Ocurrió un error al modificar: " + e.getMessage() + "\"}");
        }
    }


    // Listar los desempeños
    @GetMapping("/listardesempenio")
    public ResponseEntity<String> listar() {
        try {
            String result = service.obtenerDesempenioJson();
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON) // 👈 asegura JSON real
                    .body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ocurrió un error al listar los desempeños");
        }
    }

    @PostMapping("/seleccionar")
    public ResponseEntity<String> seleccinar(@RequestBody Map<String, Object> request) {
        try {
            int cuotVtaId = Integer.parseInt(request.get("cuotVtaId").toString());
            String result = service.seleccionar(cuotVtaId);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON) // 👈 asegura JSON real
                    .body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ocurrió un error al seleccionar la meta");
        }
    }

    @PostMapping("/seleccionarmeta")
    public ResponseEntity<String> seleccionarMeta(@RequestBody Map<String, Object> request) {
        try {
            int cuotVtaId = Integer.parseInt(request.get("cuotVtaId").toString());
            String result = service.seleccionarMeta(cuotVtaId);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON) // 👈 asegura JSON real
                    .body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"resultado\":\"error\",\"mensaje\":\"Error interno en el servidor\",\"data\":{}}");
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminar(@RequestBody Map<String, Object> request) {
        try {
            int cuotVtaId = Integer.parseInt(request.get("cuotVtaId").toString());
            String result = service.eliminar(cuotVtaId);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON) // 👈 asegura JSON real
                    .body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"resultado\":\"error\",\"mensaje\":\"Error interno en el servidor\",\"data\":{}}");
        }
    }

    /****************************METODOS PARA LA META FARMACIA******************/
    @PostMapping("/listarfarmacia")
    public ResponseEntity<String> obtenerMetaVenta(@RequestBody Map<String, Integer> request) {
        int cuotVtaId = request.get("cuotVtaId");
        String result = service.getMetaVenta(cuotVtaId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON) // 👈 asegura JSON real
                .body(result);
    }

    @PostMapping("/modificarfarmacia")
    public ResponseEntity<String> modificarMetaFarmacia(@RequestBody Map<String, Object> request) {
        try {
            int cuotVtaId = Integer.parseInt(request.get("cuotVtaId").toString());
            int siscod = Integer.parseInt(request.get("siscod").toString());
            BigDecimal cantidad = new BigDecimal(request.get("cantidad").toString());
            BigDecimal porc_estra = new BigDecimal(request.get("porc_estra").toString());
            int usecod = Integer.parseInt(request.get("usecod").toString());

            String result = service.modificarMetaFarmacia(cuotVtaId, siscod, cantidad, porc_estra, usecod);

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

    @PostMapping("/dashboard")
    public ResponseEntity<String> listarDashboard() {
        try {
            String result = service.listarDashboard();
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

    /*================================ENDPOINTS PARA ROLES===================================*/

    @GetMapping("/listarroles")
    public ResponseEntity<String> listarRoles() {
        try {
            String result = service.listarRoles();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"resultado\":\"error\",\"mensaje\":\"Error interno en el servidor\",\"data\":[]}");
        }
    }


    @PostMapping("/listarsucursalesmonto")
    public ResponseEntity<String> listarSucursalesMonto(@RequestBody Map<String, Integer> request) {
        try {
            int cuotVtaId = Integer.parseInt(request.get("cuotVtaId").toString());
            String result = service.listarSucursalesMonto(cuotVtaId);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"resultado\":\"error\",\"mensaje\":\"Error interno en el servidor\",\"data\":[]}");
        }
    }

    @PostMapping("/listarrolesporfarmacia")
    public ResponseEntity<String> listarRolesporFarmacia(@RequestBody Map<String, Integer> request) {
        try {
            int CuotVtaMesId = Integer.parseInt(request.get("CuotVtaMesId").toString());
            String result = service.listarRolesporFarmacia(CuotVtaMesId);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"resultado\":\"error\",\"mensaje\":\"Error interno en el servidor\",\"data\":[]}");
        }
    }

    @PostMapping("/modificarroles")
    public ResponseEntity<String> modificarRoles(@RequestBody Map<String, Object> request) {
        try {
            // Convertimos el Map a JSON string
            JSONObject json = new JSONObject(request);
            String jsonString = json.toString();

            // Llamamos al servicio
            String result = service.modificarRoles(jsonString);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(result);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"resultado\":\"error\",\"mensaje\":\"Error interno en el servidor\"}");
        }
    }

    @GetMapping("/listarvendedores")
    public ResponseEntity<String> listarVendedores() {
        try {
            String json = service.obtenerVendedores();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(json);
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{ \"resultado\": \"error\", \"mensaje\": \"" + e.getMessage() + "\", \"data\": [] }");
        }
    }

    @PostMapping(value = "/listarvendedoresasignados", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> listarVendedoresAsignados(@RequestBody(required = false) Map<String, Object> request) {
        try {
            Integer cuotVtaRolId = null;
            if (request != null && request.containsKey("cuotVtaRolId")) {
                cuotVtaRolId = Integer.parseInt(request.get("cuotVtaRolId").toString());
            }

            String json = service.obtenerVendedoresAsignados(cuotVtaRolId);
            return ResponseEntity.ok(json);

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("{ \"resultado\": \"error\", \"mensaje\": \"" + e.getMessage() + "\", \"vendedores\": [] }");
        }
    }

    // Endpoint para modificar vendedores asignados a un rol
    @PostMapping(value = "/modificarvendedores", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> modificarVendedores(@RequestBody Map<String, Object> request) {
        try {
            // Convertimos el Map a JSON string
            JSONObject json = new JSONObject(request);
            String jsonString = json.toString();

            String result = service.modificarVendedores(jsonString);

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body("{\"resultado\":\"error\",\"mensaje\":\"Error interno en el servidor\"}");
        }
    }


    /*==================Codido para el Dashboard Vendedores===============*/

    @GetMapping("/listarfarmacias")
    public ResponseEntity<String> obtenerFarmacias() {
        try {
            String result = service.obtenerFarmacias();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(result);
        } catch (Exception e) {
            // Si algo falla en Service o Repository
            System.err.println("Error en getFarmacias: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"resultado\":\"error\",\"farmacias\":[]}");
        }
    }

    @PostMapping("/listarvendedoreslocal")
    public ResponseEntity<String> listarVendedoresLocal(@RequestBody Map<String, Integer> request) {
        try {
            int siscod = request.get("siscod");
            String result = service.obtenerVendedoresLocal(siscod);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"resultado\":\"error\",\"vendedores\":[]}");
        }
    }


    @PostMapping("/dashboarvendedor")
    public ResponseEntity<String> obtenerMetaVentaVendedor(@RequestBody Map<String, Integer> request) {
        try {
            int usecod = request.get("usecod");
            String json = service.obtenerMetaVentaVendedor(usecod);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(json);
        } catch (Exception e) {
            System.err.println("Error en obtenerMetaVenta: " + e.getMessage());
            return ResponseEntity.status(500)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"resultado\":\"error\",\"mensaje\":\"Error interno\",\"data\":[]}");
        }
    }

    @PostMapping("/vendedores-por-farmacia")
    public ResponseEntity<String> listarVendedoresPorFarmacia(@RequestBody Map<String, Integer> request) {
        try {
            int siscod = request.get("siscod");
            String result = service.obtenerVendedoresPorFarmacia(siscod);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"resultado\":\"error\",\"vendedores\":[]}");
        }
    }

    /*==================================GESTIÓN DE UMBRALES========================================*/

    @GetMapping("/listarumbrales")
    public ResponseEntity<String> listarUmbrales() {
        try {
            String result = service.obtenerUmbrales();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(result);
        } catch (Exception e) {
            // Si algo falla en Service o Repository
            System.err.println("Error en listarUmbrales: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"resultado\":\"error\",\"umbrales\":[]}");
        }
    }

    @PostMapping("/modificarumbrales")
    public ResponseEntity<String> modificaarUmbrales(@RequestBody Map<String, Object> request) {
        try {
            int codumb = Integer.parseInt(request.get("codumb").toString());
            String nomumb = request.get("nomumb").toString();
            BigDecimal minpor = new BigDecimal(request.get("minpor").toString());
            BigDecimal maxpor = new BigDecimal(request.get("maxpor").toString());

            String result = service.modificarUmbrales(codumb, nomumb, minpor, maxpor);

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


    /*=========================== OBJETIVO COMERCIAL DE PRODUCTOS ==============================*/

    @GetMapping("/listarproductos")
    public ResponseEntity<Map<String, Object>> listarProductos() {
        try {
            List<Map<String, Object>> result = service.obtenerProductos();

            Map<String, Object> response = new HashMap<>();
            response.put("resultado", "ok");
            response.put("productos", result);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("Error en listar Productos: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("resultado", "error"));
        }
    }


    @PostMapping("/agregarproducto")
    public ResponseEntity<String> agregarProducto(@RequestBody Map<String, Object> request) {

        String codpro = request.get("codpro").toString();
        String tipo = request.get("tipo").toString();

        // Pueden venir como null si el frontend no envía el valor
        BigDecimal unidades = request.get("unidades") != null ? new BigDecimal(request.get("unidades").toString()) : null;
        BigDecimal monto = request.get("monto") != null ? new BigDecimal(request.get("monto").toString()) : null;

        int cuotVtaId = Integer.parseInt(request.get("cuotVtaId").toString());
        int useId = Integer.parseInt(request.get("useId").toString());

        String result = service.agregarMetaVentaProducto(codpro, tipo, unidades, monto, cuotVtaId, useId);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(result);
    }

    @PostMapping("/listar-venta-productos")
    public ResponseEntity<String> listarMetaVentaProductos(@RequestBody Map<String, Integer> request) {

        int cuotVtaId = request.get("cuotVtaId");
        String result = service.listarMetaVentaProductos(cuotVtaId);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(result);
    }

    @PostMapping("/eliminar-meta-producto")
    public ResponseEntity<String> eliminarMetaVentaProducto(@RequestBody Map<String, Object> request) {
        String codpro = request.get("codpro").toString();
        int cuotVtaId = Integer.parseInt(request.get("cuotVtaId").toString());

        String result = service.eliminarMetaVentaProducto(codpro, cuotVtaId);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(result);

    }

    @PostMapping("/agregar-producto-permanente")
    public ResponseEntity<String> agregarProductoPermanente(@RequestBody Map<String, Object> request) {
        String codpro = request.get("codpro").toString();
        int cantpro = Integer.parseInt(request.get("cantpro").toString());
        int cuotVtaId = Integer.parseInt(request.get("cuotVtaId").toString()); // Nuevo parámetro

        String result = service.agregarPermanente(codpro, cantpro, cuotVtaId);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(result);
    }

    @PostMapping("/eliminar-producto-permanente")
    public ResponseEntity<String> eliminarProductoPermanente(@RequestBody Map<String, Object> request) {
        String codpro = request.get("codpro").toString();
        int cuotVtaId = Integer.parseInt(request.get("cuotVtaId").toString()); // Nuevo parámetro

        String result = service.eliminarPermanente(codpro, cuotVtaId);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(result);
    }

    /*=========================== OBJETIVO DE VENTA DE PRODUCTOS ==============================*/

    @GetMapping("/listar-sucursales-productos-detalle")
    public ResponseEntity<String> listarSucursalesProductosDetalle() {
        String result = service.listarSucursalesProductosDetalle();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(result);

    }

    @PostMapping("/listar-objetivo-productos-detalle")
    public ResponseEntity<String> listarObjetivoProductosDetalle(@RequestBody Map<String, Object> request) {

        int cuotVtaId = Integer.parseInt(request.get("cuotVtaId").toString());
        String codpro = request.get("codpro").toString();

        String result = service.listarObjetivoProductosDetalle(cuotVtaId, codpro);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(result);
    }


    @PostMapping("/agregar-objetivo-productos-detalle")
    public ResponseEntity<String> agregarObjetivoProductosDetalle(@RequestBody Map<String, Object> request) {
        int cuotVtaId = Integer.parseInt(request.get("cuotVtaId").toString());
        String codpro = request.get("codpro").toString();
        int sucurId = Integer.parseInt(request.get("sucurId").toString());

        String result = service.agregarObjetivoProductosDetalle(cuotVtaId, codpro, sucurId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(result);

    }

    @DeleteMapping("/eliminar-objetivo-productos-detalle")
    public ResponseEntity<String> eliminarObjetivoProductosDetalle(@RequestBody Map<String, Object> request) {
        int cuotVtaId = Integer.parseInt(request.get("cuotVtaId").toString());
        String codpro = request.get("codpro").toString();
        int sucurId = Integer.parseInt(request.get("sucurId").toString());

        String result = service.eliminarObjetivoProductosDetalle(cuotVtaId, codpro, sucurId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(result);
    }

    @PutMapping("/modificar-unidades")
    public ResponseEntity<String> modificarUnidadesProductos(@RequestBody Map<String, Object> request) {

        try {
            int cuotVtaId = Integer.parseInt(request.get("cuotVtaId").toString());
            String codpro = request.get("codpro").toString();

            // Recibir directamente unidades y monto (pueden ser null)
            BigDecimal unidades = request.get("unidades") != null
                    ? new BigDecimal(request.get("unidades").toString())
                    : null;

            BigDecimal monto = request.get("monto") != null
                    ? new BigDecimal(request.get("monto").toString())
                    : null;

            // Llamar al servicio con ambos valores
            String result = service.modificarUnidades(cuotVtaId, codpro, unidades, monto);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(result);

        } catch (Exception e) {
            String m = "";
            m = e.getMessage();
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/dashboard-producto-por-producto")
    public ResponseEntity<Map<String, Object>> obtenerDashboardProducto(@RequestBody Map<String, Object> request) {
        int siscod = Integer.parseInt(request.get("siscod").toString());
        int usecod = Integer.parseInt(request.get("usecod").toString()); // Nuevo parámetro

        List<Map<String, Object>> dashboard = service.obtenerDashboardProductoporProducto(siscod, usecod);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("resultado", "ok");
        response.put("dashboardProducto", dashboard);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/dashboard-producto-por-vendedor")
    public ResponseEntity<Map<String, Object>> obtenerDashboardProductoporVendedor(@RequestBody Map<String, Object> request) {
        int siscod = Integer.parseInt(request.get("siscod").toString());

        List<Map<String, Object>> dashboard = service.obtenerDashboardProductoporVendedor(siscod);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("resultado", "ok");
        response.put("dashboardProducto", dashboard);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/dashboard-producto")
    public ResponseEntity<Map<String, Object>> obtenerDashboardProducto() {

        List<Map<String, Object>> dashboard = service.obtenerDashboardProducto();

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("resultado", "ok");
        response.put("dashboardProducto", dashboard);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/vendedores/meta-producto")
    public ResponseEntity<Map<String, Object>> obtenerVendedoresConMetaProducto(@RequestBody Map<String, Object> request) {

        // Tomar el parámetro del body
        int siscod = Integer.parseInt(request.get("siscod").toString());

        // Llamar al servicio
        List<Map<String, Object>> result = service.obtenerVendedoresConMetaProducto(siscod);

        // Armar la respuesta
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("resultado", "ok");
        response.put("vendedoresConMetaProducto", result);

        return ResponseEntity.ok(response);
    }




    /*=========================== LISTADO DE CAJJAS CERRADAS ==============================*/

    @PostMapping("/listar-cajas-cerradas")
    public ResponseEntity<Map<String, Object>> obtenerCajasCerradas(@RequestBody Map<String, Object> request) {
        Date fecha1 = Date.valueOf(request.get("fecha1").toString());
        Date fecha2 = Date.valueOf(request.get("fecha2").toString());
        int siscod = Integer.parseInt(request.get("siscod").toString());
        int usecod1 = Integer.parseInt(request.get("usecod1").toString());

        List<Map<String, Object>> result = service.obtenerCajasCerradas(fecha1, fecha2, siscod, usecod1);

        Map<String, Object> response = new HashMap<>();
        response.put("resultado", "ok");
        response.put("cajas", result);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/listar-usuarios-cajas-cerradas")
    public ResponseEntity<Map<String, Object>> obtenerUsuariosCajasCerradas(@RequestBody Map<String, Object> request) {
        String fecha1 = request.get("fecha1").toString();
        String fecha2 = request.get("fecha2").toString();
        int siscod = Integer.parseInt(request.get("siscod").toString());

        List<Map<String, Object>> usuarios = service.obtenerUsuariosCajasCerradas(fecha1, fecha2, siscod);

        Map<String, Object> response = new HashMap<>();
        response.put("resultado", "ok");
        response.put("usuarios", usuarios);

        return ResponseEntity.ok(response);
    }

    /*=========================== DASHBOARD RESUMEN ==============================*/

    @PostMapping("/dashboard/resumen")
    public ResponseEntity<Map<String, Object>> obtenerdDashnoardResumen(@RequestBody Map<String, Object> request) {

        int cuotVtaId = Integer.parseInt(request.get("cuotVtaId").toString());

        List<Map<String, Object>> result = service.obtenerdDashnoardResumen(cuotVtaId);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("resultado", "ok");
        response.put("dashhboard_resumen", result);

        return ResponseEntity.ok(response);
    }
}
