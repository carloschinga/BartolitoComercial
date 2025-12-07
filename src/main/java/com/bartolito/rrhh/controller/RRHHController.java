package com.bartolito.rrhh.controller;

import com.bartolito.rrhh.service.RRHHService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class RRHHController {

    private final RRHHService service;
    private Map<String, Object> response;

    public RRHHController(RRHHService service) {

        this.service = service;
    }

    @GetMapping("/turnos/listar")
    public ResponseEntity<Map<String, Object>> obtenerdTurnos() {

        try {
            List<Map<String, Object>> result = service.obtenerdTurnos();

            Map<String, Object> data = new LinkedHashMap<>();
            data.put("turnos", result);

            Map<String, Object> response = new LinkedHashMap<>();
            response.put("resultado", "ok");
            response.put("data", data);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // 2. SI FALLA: Imprimimos el error completo en la consola (Importante para ti)
            e.printStackTrace();

            // 3. Devolvemos el mensaje de error a Postman
            response.put("resultado", "error");
            response.put("mensaje", "Error al intentar listar horarios");
            // Aquí enviamos el error técnico real:
            response.put("error_tecnico", e.getMessage());
            response.put("causa_raiz", e.getCause() != null ? e.getCause().toString() : "Desconocida");

            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping("/turnos/seleccionar/{codiTurn}")
    public ResponseEntity<Map<String, Object>> seleccionarTurnoPorCodigo(@PathVariable Integer codiTurn) {

        Map<String, Object> horarioData = service.seleccionarTurnoPorCodigo(codiTurn);

        Map<String, Object> data = new LinkedHashMap<>();

        data.put("turno", horarioData);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("resultado", "ok");
        response.put("data", data);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/turnos/agregar")
    public ResponseEntity<Map<String, Object>> agregarTurno(@RequestBody Map<String, String> requestBody) {

        String nombTurn = requestBody.get("nombTurn");
        String ingrTurn = requestBody.get("ingrTurn");
        String saldTurn = requestBody.get("saldTurn");

        int nuevoId = service.agregarTurno(nombTurn, ingrTurn, saldTurn);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("resultado", "ok");
        response.put("mensaje", "Turno insertado exitosamente.");
        response.put("nuevoId", nuevoId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/turnos/editar/{codiTurn}")
    public ResponseEntity<Map<String, Object>> editarTurno(@PathVariable Integer codiTurn, @RequestBody Map<String, String> requestBody) {

        String nombTurn = requestBody.get("nombTurn");
        String ingrTurn = requestBody.get("ingrTurn");
        String saldTurn = requestBody.get("saldTurn");

        service.editarTurno(codiTurn, nombTurn, ingrTurn, saldTurn);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("resultado", "ok");
        response.put("mensaje", "Turno actualizado exitosamente.");

        return ResponseEntity.ok(response);
    }

    /*====================== SECCIÓN DE LA GESTIÓN HORARIO ======================*/

    @GetMapping("/horarios/listar")
    public ResponseEntity<Map<String, Object>> obtenerHorario() {

        List<Map<String, Object>> result = service.obtenerHorario();

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("horario", result);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("resultado", "ok");
        response.put("data", data);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/horarios/seleccionar/{codiHora}")
    public ResponseEntity<Map<String, Object>> seleccionarHorarioPorCodigo(@PathVariable Integer codiHora) {

        Map<String, Object> horarioData = service.seleccionarHorarioPorCodigo(codiHora);

        Map<String, Object> data = new LinkedHashMap<>();

        data.put("horario", horarioData);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("resultado", "ok");
        response.put("data", data);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/horarios/agregar")
    public ResponseEntity<Map<String, Object>> agregarHorario(@RequestBody Map<String, String> requestBody) {

        String nombHora = requestBody.get("nombHora");
        Integer usuacrea = Integer.parseInt(requestBody.get("usuacrea"));

        int nuevoId = service.agregarHorario(nombHora, usuacrea);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("resultado", "ok");
        response.put("mensaje", "Horario creado exitosamente.");
        response.put("nuevoId", nuevoId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/horarios/editar/{codiHora}")
    public ResponseEntity<Map<String, Object>> editarHorario(@PathVariable Integer codiHora, @RequestBody Map<String, Object> requestBody) {

        String nombHora = (String) requestBody.get("nombHora");
        Integer usuamodi = (Integer) requestBody.get("usuamodi");

        service.editarHorario(codiHora, nombHora, usuamodi);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("resultado", "ok");
        response.put("mensaje", "Horario actualizado exitosamente.");

        return ResponseEntity.ok(response);
    }

    /*====================== SECCIÓN DE GESTION DETALLE HORARIO ======================*/

    @GetMapping("/horarios/detalle/listar")
    public ResponseEntity<Map<String, Object>> obtenerHorarioDetalle() {

        List<Map<String, Object>> result = service.obtenerHorarioDetalle();

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("detalles", result);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("resultado", "ok");
        response.put("data", data);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/horarios/detalle/seleccionar/{codiHora}")
    public ResponseEntity<Map<String, Object>> seleccionarHorarioDetallePorCodigo(@PathVariable Integer codiHora) {

        List<Map<String, Object>> listaDetalles = service.seleccionarHorarioDetallePorCodigo(codiHora);

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("detalles", listaDetalles);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("resultado", "ok");
        response.put("data", data);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/horarios/detalle/agregar")
    public ResponseEntity<Map<String, Object>> agregarHorarioDetalle(@RequestBody Map<String, String> requestBody) {

        Integer codiHora = Integer.parseInt(requestBody.get("codiHora"));
        Integer codiDia = Integer.parseInt(requestBody.get("codiDia"));
        Integer codiTurn = Integer.parseInt(requestBody.get("codiTurn"));
        Integer anulTurn = Integer.parseInt(requestBody.get("anulTurn")); // 1 o 0
        Integer usuacrea = Integer.parseInt(requestBody.get("usuacrea"));

        int nuevoId = service.agregarHorarioDetalle(codiHora, codiDia, codiTurn, anulTurn, usuacrea);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("resultado", "ok");
        response.put("mensaje", "Detalle de horario agregado exitosamente.");
        response.put("nuevoId", nuevoId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/horarios/detalle/editar/{codiHoraDeta}")
    public ResponseEntity<Map<String, Object>> editarHorarioDetalle(@PathVariable Integer codiHoraDeta, @RequestBody Map<String, String> requestBody) {

        Integer codiHora = Integer.parseInt(requestBody.get("codiHora"));
        Integer codiDia = Integer.parseInt(requestBody.get("codiDia"));
        Integer codiTurn = Integer.parseInt(requestBody.get("codiTurn"));
        Integer anulTurn = Integer.parseInt(requestBody.get("anulTurn"));
        Integer usuamodi = Integer.parseInt(requestBody.get("usuamodi")); // ID del usuario que edita

        service.editarHorarioDetalle(codiHoraDeta, codiHora, codiDia, codiTurn, anulTurn, usuamodi);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("resultado", "ok");
        response.put("mensaje", "Detalle de horario actualizado exitosamente.");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/horarios/detalle/eliminar/{codiHoraDeta}")
    public ResponseEntity<Map<String, Object>> eliminarHorarioDetalle(@PathVariable Integer codiHoraDeta, @RequestBody Map<String, Integer> requestBody) {

        Integer codiHora = (requestBody.get("codiHora"));
        Integer codiDia = (requestBody.get("codiDia"));
        Integer codiTurn = (requestBody.get("codiTurn"));
        Integer usuamodi = (requestBody.get("usuamodi")); // Quién lo eliminó

        service.eliminarHorarioDetalle(codiHoraDeta, codiHora, codiDia, codiTurn, usuamodi);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("resultado", "ok");
        response.put("mensaje", "Detalle de horario eliminado (anulado) exitosamente.");

        return ResponseEntity.ok(response);
    }

    /*====================== SECCIÓN DE GESTION DETALLE SEMANAL ======================*/

    @GetMapping("/horarios/semanal/listar")
    public ResponseEntity<Map<String, Object>> listarHorarioSemanal() {

        List<Map<String, Object>> result = service.listarHorarioSemanal();

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("semanales", result); // Nombre claro para la colección

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("resultado", "ok");
        response.put("data", data);

        return ResponseEntity.ok(response);
    }

}