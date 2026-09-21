package com.bartolito.comercial.controller;

import com.bartolito.comercial.service.CorreccionInsanorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/correccionInsanor")
public class CorreccionInsanorController {

    @Autowired
    private CorreccionInsanorService service;

    // =========================================
    // CABECERA
    // =========================================
    @PostMapping("/cabecera")
    public ResponseEntity<?> obtenerCabeceraCorreccion(
            @RequestBody Map<String, Object> request
    ) {

        Integer scaja = request.get("scaja") != null
                ? Integer.parseInt(request.get("scaja").toString())
                : null;

        List<Map<String, Object>> result = service.obtenerCabeceraCorreccion(
                scaja
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // DETALLE
    // =========================================
    @PostMapping("/detalle")
    public ResponseEntity<?> obtenerDetalleCorreccion(
            @RequestBody Map<String, Object> request
    ) {

        Integer invnum = Integer.parseInt(request.get("invnum").toString());

        List<Map<String, Object>> result = service.obtenerDetalleCorreccion(
                invnum
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // METODOS DE PAGO
    // =========================================
    @PostMapping("/listarMetodosPago")
    public ResponseEntity<?> obtenerMetodosPagoCorreccion(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio").toString();
        String fechaFin = request.get("fechaFin").toString();

        List<Map<String, Object>> result = service.obtenerMetodosPagoCorreccion(
                fechaInicio,
                fechaFin
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // TRANSACCIONES PINPAD
    // =========================================
    @PostMapping("/listarTransaccionesPinpad")
    public ResponseEntity<?> obtenerTransaccionesPinpad(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio") != null
                ? request.get("fechaInicio").toString()
                : null;

        String fechaFin = request.get("fechaFin") != null
                ? request.get("fechaFin").toString()
                : null;

        String operacion = request.get("operacion") != null
                ? request.get("operacion").toString()
                : null;

        List<Map<String, Object>> result = service.obtenerTransaccionesPinpad(
                fechaInicio,
                fechaFin,
                operacion
        );

        return ResponseEntity.ok(result);
    }


// =========================================
// GUARDAR SOLICITUD
// =========================================
    @PostMapping("/guardarSolicitud")
    public ResponseEntity<?> guardarSolicitud(
            @RequestBody Map<String, Object> request
    ) throws JsonProcessingException {

        Integer secuenciaCaja =
                Integer.parseInt(request.get("secuenciaCaja").toString());

        Integer secuenciaApertura =
                Integer.parseInt(request.get("secuenciaApertura").toString());

        String numComprobante =
                request.get("numComprobante").toString();

        BigDecimal totalComprobante =
                new BigDecimal(request.get("totalComprobante").toString());

        String fechaCaja =
                request.get("fechaCaja").toString();

        Integer usecodSolicita =
                Integer.parseInt(request.get("usecodSolicita").toString());

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> detalle =
                (List<Map<String, Object>>) request.get("detalle");

        StringBuilder xml = new StringBuilder();
        xml.append("<Detalle>");

        for (Map<String, Object> item : detalle) {

            xml.append("<Item>");

            xml.append("<docpag>")
                    .append(item.get("docpag"))
                    .append("</docpag>");

            xml.append("<numitm>")
                    .append(item.get("numitm"))
                    .append("</numitm>");

            xml.append("<monto>")
                    .append(item.get("monto"))
                    .append("</monto>");

            // Referencia del pago / tarjeta
            xml.append("<numref>")
                    .append(item.get("numref"))
                    .append("</numref>");

            // Código de tarjeta
            xml.append("<tarjcod>")
                    .append(item.get("tarjcod"))
                    .append("</tarjcod>");

            // Operación de tarjeta
            xml.append("<docopertaj>")
                    .append(item.get("docopertaj"))
                    .append("</docopertaj>");

            // Descripción
            xml.append("<docdes>")
                    .append(item.get("docdes"))
                    .append("</docdes>");

            // Comprobante/documento relacionado
            xml.append("<invnum_ref>")
                    .append(item.get("invnum_ref"))
                    .append("</invnum_ref>");

            // ID de transacción Pinpad
            xml.append("<idtransac>")
                    .append(item.get("idtransac"))
                    .append("</idtransac>");

            xml.append("</Item>");
        }

        xml.append("</Detalle>");

        String detalleXml = xml.toString();

        List<Map<String, Object>> result =
                service.guardarSolicitudCorreccion(
                        secuenciaCaja,
                        secuenciaApertura,
                        numComprobante,
                        totalComprobante,
                        fechaCaja,
                        usecodSolicita,
                        detalleXml
                );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // APROBAR SOLICITUD
    // =========================================
    @PostMapping("/aprobarSolicitud")
    public ResponseEntity<?> aprobarSolicitud(
            @RequestBody Map<String, Object> request
    ) {

        Integer solicitudId =
                Integer.parseInt(request.get("solicitudId").toString());

        Integer usecodAprueba =
                Integer.parseInt(request.get("usecodAprueba").toString());

        List<Map<String, Object>> result =
                service.aprobarSolicitud(
                        solicitudId,
                        usecodAprueba
                );

        return ResponseEntity.ok(result);

    }

    // =========================================
    // RECHAZAR SOLICITUD
    // =========================================
    @PostMapping("/rechazarSolicitud")
    public ResponseEntity<?> rechazarSolicitud(
            @RequestBody Map<String, Object> request
    ) {

        Integer solicitudId =
                Integer.parseInt(request.get("solicitudId").toString());

        String motivoRechazo =
                request.get("motivoRechazo").toString();

        Integer usecodAprueba =
                Integer.parseInt(request.get("usecodAprueba").toString());

        List<Map<String, Object>> result =
                service.rechazarSolicitud(
                        solicitudId,
                        motivoRechazo,
                        usecodAprueba
                );

        return ResponseEntity.ok(result);

    }

    // =========================================
    // HISTORICO SOLICITUD
    // =========================================
    @PostMapping("/listarHistoricoSolicitud")
    public ResponseEntity<?> listarHistoricoSolicitud(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio").toString();
        String fechaFin = request.get("fechaFin").toString();

        Integer usecod = Integer.parseInt(
                request.get("usecod").toString()
        );

        String estado = request.get("estado") != null
                ? request.get("estado").toString()
                : null;

        List<Map<String, Object>> cabeceras =
                service.listarHistoricoCabecera(
                        fechaInicio,
                        fechaFin,
                        usecod,
                        estado
                );

        for (Map<String, Object> cabecera : cabeceras) {

            Integer solicitudId = Integer.parseInt(
                    cabecera.get("solicitud_id").toString()
            );

            List<Map<String, Object>> detalle =
                    service.listarHistoricoDetalle(solicitudId);

            cabecera.put("detalle", detalle);

        }

        return ResponseEntity.ok(cabeceras);

    }

    // =========================================
    // GET SOLICITUD
    // =========================================
    @PostMapping("/getSolicitud")
    public ResponseEntity<?> obtenerSolicitud(
            @RequestBody Map<String, Object> request
    ) {

        Integer solicitudId = Integer.parseInt(
                request.get("solicitudId").toString()
        );

        List<Map<String, Object>> cabecera =
                service.obtenerSolicitud(solicitudId);

        if (cabecera.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyMap());
        }

        List<Map<String, Object>> detalle =
                service.listarHistoricoDetalle(solicitudId);

        Map<String, Object> response = new LinkedHashMap<>(cabecera.get(0));

        response.put("detalle", detalle);

        return ResponseEntity.ok(response);

    }

    // =========================================
    // LISTAR SOLICITUDES
    // =========================================
    @PostMapping("/listarSolicitud")
    public ResponseEntity<?> listarSolicitud(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio").toString();
        String fechaFin = request.get("fechaFin").toString();

        String estado = request.get("estado") != null
                ? request.get("estado").toString()
                : null;

        List<Map<String, Object>> cabeceras =
                service.listarCabeceraSolicitud(
                        fechaInicio,
                        fechaFin,
                        estado
                );

        for (Map<String, Object> cabecera : cabeceras) {

            Integer solicitudId = Integer.parseInt(
                    cabecera.get("solicitud_id").toString()
            );

            List<Map<String, Object>> detalle =
                    service.listarDetalleSolicitud(solicitudId);

            cabecera.put("detalle", detalle);

        }

        return ResponseEntity.ok(cabeceras);

    }

    // =========================================
    // LISTAR DETALLE ORIGINAL
    // =========================================
    @PostMapping("/listarDetalleOriginal")
    public ResponseEntity<?> listarDetalleOriginal(
            @RequestBody Map<String, Object> request
    ) {

        Integer invnum = Integer.parseInt(
                request.get("invnum").toString()
        );

        List<Map<String, Object>> result =
                service.listarDetalleOriginal(invnum);

        return ResponseEntity.ok(result);

    }

    // =========================================
    // ANULAR SOLICITUD
    // =========================================
    @PostMapping("/anularSolicitud")
    public ResponseEntity<?> anularSolicitud(
            @RequestBody Map<String, Object> request
    ) {

        Integer solicitudId = Integer.parseInt(
                request.get("solicitudId").toString()
        );

        Integer usecodSolicita = Integer.parseInt(
                request.get("usecodSolicita").toString()
        );

        List<Map<String, Object>> result =
                service.anularSolicitud(
                        solicitudId,
                        usecodSolicita
                );

        return ResponseEntity.ok(result);

    }
}
