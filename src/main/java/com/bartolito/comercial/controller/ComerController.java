package com.bartolito.comercial.controller;

import com.bartolito.comercial.service.ComerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

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
            e.printStackTrace(); // 👈 imprime en logs el error real
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
    public ResponseEntity<String> getFarmacias() {
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
}
