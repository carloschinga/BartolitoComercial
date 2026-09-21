package com.bartolito.comercial.controller;

import com.bartolito.comercial.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/transaccion")
public class TransaccionController {

    @Autowired
    private TransaccionService service;

    // =========================================
    // LISTAR TRANSACCIONES
    // =========================================
    @PostMapping("/listarTransacciones")
    public ResponseEntity<?> listarTransacciones(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio").toString();
        String fechaFin = request.get("fechaFin").toString();

        Integer invnumAper = request.get("invnumAper") != null
                ? Integer.parseInt(request.get("invnumAper").toString())
                : null;

        Integer siscod = request.get("siscod") != null
                ? Integer.parseInt(request.get("siscod").toString())
                : null;

        List<Map<String, Object>> result = service.listarTransacciones(
                fechaInicio,
                fechaFin,
                invnumAper,
                siscod
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // LISTAR VENTAS AL CREDITO
    // =========================================
    @PostMapping("/listarVentasCredito")
    public ResponseEntity<?> listarVentasCredito(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio").toString();
        String fechaFin = request.get("fechaFin").toString();

        Integer invnumAper = request.get("invnumAper") != null
                ? Integer.parseInt(request.get("invnumAper").toString())
                : null;

        Integer siscod = request.get("siscod") != null
                ? Integer.parseInt(request.get("siscod").toString())
                : null;

        List<Map<String, Object>> result = service.listarVentasCredito(
                fechaInicio,
                fechaFin,
                invnumAper,
                siscod
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // LISTAR APERTURAS DE CAJA
    // =========================================
    @PostMapping("/listarApertura")
    public ResponseEntity<?> listarAperturasCaja(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio").toString();
        String fechaFin = request.get("fechaFin").toString();

        Integer siscod = request.get("siscod") != null
                ? Integer.parseInt(request.get("siscod").toString())
                : null;

        Integer usecod = request.get("usecod") != null
                ? Integer.parseInt(request.get("usecod").toString())
                : null;

        List<Map<String, Object>> result = service.listarAperturasCaja(
                fechaInicio,
                fechaFin,
                siscod,
                usecod
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // LISTAR METODOS DE PAGO
    // =========================================
    @PostMapping("/listarMetodosPago")
    public ResponseEntity<?> listarMetodosPago(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio").toString();
        String fechaFin = request.get("fechaFin").toString();

        List<Map<String, Object>> result = service.listarMetodosPago(
                fechaInicio,
                fechaFin
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // LISTAR SUMATORIA FORMA PAGO
    // =========================================
    @PostMapping("/listarSumatoriaFormaPago")
    public ResponseEntity<?> listarSumatoriaFormaPago(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio").toString();
        String fechaFin = request.get("fechaFin").toString();

        Integer invnumAper = request.get("invnumAper") != null
                ? Integer.parseInt(request.get("invnumAper").toString())
                : null;

        Integer siscod = request.get("siscod") != null
                ? Integer.parseInt(request.get("siscod").toString())
                : null;

        List<Map<String, Object>> result = service.listarSumatoriaFormaPago(
                fechaInicio,
                fechaFin,
                invnumAper,
                siscod
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // LISTAR NOTAS DE CREDITO
    // =========================================
    @PostMapping("/listarNotaCredito")
    public ResponseEntity<?> listarNotaCredito(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio").toString();
        String fechaFin = request.get("fechaFin").toString();

        Integer invnumAper = request.get("invnumAper") != null
                ? Integer.parseInt(request.get("invnumAper").toString())
                : null;

        Integer siscod = request.get("siscod") != null
                ? Integer.parseInt(request.get("siscod").toString())
                : null;

        List<Map<String, Object>> result = service.listarNotaCredito(
                fechaInicio,
                fechaFin,
                invnumAper,
                siscod
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // LISTAR PINPAD ANULADO
    // =========================================
    @PostMapping("/listarPinpadAnulado")
    public ResponseEntity<?> listarPinpadAnulado(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio").toString();
        String fechaFin = request.get("fechaFin").toString();

        Integer invnumAper = request.get("invnumAper") != null
                ? Integer.parseInt(request.get("invnumAper").toString())
                : null;

        Integer siscod = request.get("siscod") != null
                ? Integer.parseInt(request.get("siscod").toString())
                : null;

        List<Map<String, Object>> result = service.listarPinpadAnulado(
                fechaInicio,
                fechaFin,
                invnumAper,
                siscod
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // LISTAR EMERGENCIAS PINPAD
    // =========================================
    @PostMapping("/listarEmergenciaPinpad")
    public ResponseEntity<?> listarEmergenciasPinpad(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio").toString();
        String fechaFin = request.get("fechaFin").toString();

        Integer invnumAper = request.get("invnumAper") != null
                ? Integer.parseInt(request.get("invnumAper").toString())
                : null;

        Integer siscod = request.get("siscod") != null
                ? Integer.parseInt(request.get("siscod").toString())
                : null;

        List<Map<String, Object>> result = service.listarEmergenciasPinpad(
                fechaInicio,
                fechaFin,
                invnumAper,
                siscod
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // OBTENER CABECERA NOTA CREDITO
    // =========================================
    @PostMapping("/obtenerCabeceraNotaCredito")
    public ResponseEntity<?> obtenerCabeceraNotaCredito(
            @RequestBody Map<String, Object> request
    ) {

        Integer invnum = Integer.parseInt(
                request.get("invnum").toString()
        );

        List<Map<String, Object>> result =
                service.obtenerCabeceraNotaCredito(invnum);

        return ResponseEntity.ok(result);
    }

    // =========================================
    // OBTENER DETALLE PRODUCTOS NOTA CREDITO
    // =========================================
    @PostMapping("/obtenerDetalleProductosNotaCredito")
    public ResponseEntity<?> obtenerDetalleProductosNotaCredito(
            @RequestBody Map<String, Object> request
    ) {

        Integer invnum = Integer.parseInt(
                request.get("invnum").toString()
        );

        List<Map<String, Object>> result =
                service.obtenerDetalleProductosNotaCredito(invnum);

        return ResponseEntity.ok(result);
    }

    // =========================================
    // OBTENER DETALLE PRODUCTOS VENTA CREDITO
    // =========================================
    @PostMapping("/obtenerDetalleProductosVentaCredito")
    public ResponseEntity<?> obtenerDetalleProductosVentaCredito(
            @RequestBody Map<String, Object> request
    ) {

        Integer invnum = Integer.parseInt(
                request.get("invnum").toString()
        );

        List<Map<String, Object>> result =
                service.obtenerDetalleProductosVentaCredito(invnum);

        return ResponseEntity.ok(result);
    }

    // =========================================
    // OBTENER DETALLE PRODUCTOS VENTA CREDITO
    // =========================================
    @PostMapping("/obtenerDetalleProductosFormaPago")
    public ResponseEntity<?> obtenerDetalleFormasPagoProductos(
            @RequestBody Map<String, Object> request
    ) {

        Integer invnum = Integer.parseInt(
                request.get("invnum").toString()
        );

        List<Map<String, Object>> result =
                service.obtenerDetalleFormasPagoProductos(invnum);

        return ResponseEntity.ok(result);
    }

    // =========================================
    //  OBTENER DETALLE APLICADO
    // =========================================
    @PostMapping("/obtenerDetalleAplicado")
    public ResponseEntity<?> obtenerDetalleAplicado(
            @RequestBody Map<String, Object> request
    ) {

        Integer invnum = Integer.parseInt(
                request.get("invnum").toString()
        );

        List<Map<String, Object>> result =
                service.obtenerDetalleAplicado(invnum);

        return ResponseEntity.ok(result);
    }

    // =========================================
    // LISTAR HUERFANOS
    // =========================================
    @PostMapping("/listarHuerfanos")
    public ResponseEntity<?> listarHuerfanos(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio").toString();
        String fechaFin = request.get("fechaFin").toString();

        Integer invnumAper = request.get("invnumAper") != null
                ? Integer.parseInt(request.get("invnumAper").toString())
                : null;

        Integer siscod = request.get("siscod") != null
                ? Integer.parseInt(request.get("siscod").toString())
                : null;

        List<Map<String, Object>> result = service.listarHuerfanos(
                fechaInicio,
                fechaFin,
                invnumAper,
                siscod
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // LISTAR NOTAS DE CREDITO APLICADAS
    // =========================================
    @PostMapping("/listarNotaCreditoAplicadas")
    public ResponseEntity<?> listarNotaCreditoAplicadas(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio").toString();
        String fechaFin = request.get("fechaFin").toString();

        Integer invnumAper = request.get("invnumAper") != null
                ? Integer.parseInt(request.get("invnumAper").toString())
                : null;

        Integer siscod = request.get("siscod") != null
                ? Integer.parseInt(request.get("siscod").toString())
                : null;

        List<Map<String, Object>> result = service.listarNotaCreditoAplicadas(
                fechaInicio,
                fechaFin,
                invnumAper,
                siscod
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // OBTENER CABECERA PINPAD ANULADO
    // =========================================
    @PostMapping("/obtenerCabeceraPinpadAnulado")
    public ResponseEntity<?> obtenerCabeceraPinpadAnulado(
            @RequestBody Map<String, Object> request
    ) {

        String referencia = request.get("referencia").toString();

        List<Map<String, Object>> result =
                service.obtenerCabeceraPinpadAnulado(referencia);

        return ResponseEntity.ok(result);
    }

    // =========================================
    //  OBTENER DETALLE PINPAD ANULADO
    // =========================================
    @PostMapping("/obtenerDetallePinpadAnulado")
    public ResponseEntity<?> obtenerDetallePinpadAnulado(
            @RequestBody Map<String, Object> request
    ) {

        String referencia = request.get("referencia").toString();

        List<Map<String, Object>> result =
                service.obtenerDetallePinpadAnulado(referencia);

        return ResponseEntity.ok(result);
    }

    // =========================================
    // TRANSACCIONES USUARIO
    // =========================================
    @PostMapping("/listarTransaccionesUsuario")
    public ResponseEntity<?> obtenerTransaccionesUsuario(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio").toString();
        String fechaFin = request.get("fechaFin").toString();

        Integer usecod = request.get("usecod") != null
                ? Integer.parseInt(request.get("usecod").toString())
                : null;

        Integer siscod = request.get("siscod") != null
                ? Integer.parseInt(request.get("siscod").toString())
                : null;

        List<Map<String, Object>> result = service.obtenerTransaccionesUsuario(
                fechaInicio,
                fechaFin,
                usecod,
                siscod
        );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // HISTORIAL SUMATORIAS
    // =========================================
    @PostMapping("/listarHistorialSumatorias")
    public ResponseEntity<?> listarHistorialSumatorias(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio").toString();
        String fechaFin = request.get("fechaFin").toString();

        List<Map<String, Object>> result =
                service.listarHistorialSumatorias(fechaInicio,
                        fechaFin);

        return ResponseEntity.ok(result);
    }

    // =========================================
    // LISTAR SUMATORIA
    // =========================================
    @PostMapping("/listarSumatoria")
    public ResponseEntity<?> listarSumatoria(
            @RequestBody Map<String, Object> request
    ) {

        Integer operacionId = request.get("operacionId") != null
                ? Integer.parseInt(request.get("operacionId").toString())
                : null;

        List<Map<String, Object>> result =
                service.listarSumatoria(
                        operacionId
                );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // SUMATORIA FORMA PAGO - SAVE OR UPDATE
    // =========================================
    @PostMapping("/saveOrUpdateSumatoriaFormaPago")
    public ResponseEntity<?> saveOrUpdateSumatoriaFormaPago(
            @RequestBody Map<String, Object> request
    ) {

        Integer sumatoriaFormaPagoId =
                request.get("sumatoriaFormaPagoId") != null
                        ? Integer.parseInt(
                        request.get("sumatoriaFormaPagoId").toString()
                )
                        : null;

        Integer operacionId =
                request.get("operacionId") != null
                        ? Integer.parseInt(
                        request.get("operacionId").toString()
                )
                        : null;

        Integer siscod =
                request.get("siscod") != null
                        ? Integer.parseInt(
                        request.get("siscod").toString()
                )
                        : null;

        String fechaOperacion =
                request.get("fechaOperacion") != null
                        ? request.get("fechaOperacion").toString()
                        : null;

        Integer invnumAper =
                request.get("invnumAper") != null
                        ? Integer.parseInt(
                        request.get("invnumAper").toString()
                )
                        : null;

        String docpag =
                request.get("docpag") != null
                        ? request.get("docpag").toString()
                        : null;

        String docdes =
                request.get("docdes") != null
                        ? request.get("docdes").toString()
                        : null;

        Integer cantidad =
                request.get("cantidad") != null
                        ? Integer.parseInt(
                        request.get("cantidad").toString()
                )
                        : null;

        BigDecimal importeTotal =
                request.get("importeTotal") != null
                        ? new BigDecimal(
                        request.get("importeTotal").toString()
                )
                        : null;

        BigDecimal importeNC =
                request.get("importeNC") != null
                        ? new BigDecimal(
                        request.get("importeNC").toString()
                )
                        : null;

        BigDecimal total =
                request.get("total") != null
                        ? new BigDecimal(
                        request.get("total").toString()
                )
                        : null;

        Integer estado =
                request.get("estado") != null
                        ? Integer.parseInt(
                        request.get("estado").toString()
                )
                        : null;

        String usuario =
                request.get("usuario") != null
                        ? request.get("usuario").toString()
                        : null;

        List<Map<String, Object>> result =
                service.saveOrUpdateSumatoriaFormaPago(
                        sumatoriaFormaPagoId,
                        operacionId,
                        siscod,
                        fechaOperacion,
                        invnumAper,
                        docpag,
                        docdes,
                        cantidad,
                        importeTotal,
                        importeNC,
                        total,
                        estado,
                        usuario
                );

        return ResponseEntity.ok(result);
    }

    // =========================================
    // SUMATORIA FORMA DE PAGO COMPLETA
    // =========================================
    @PostMapping("/sumatoriaFormaPagoCompleta")
    public ResponseEntity<?> sumatoriaFormaPagoCompleta(
            @RequestBody Map<String, Object> request
    ) {

        String fechaInicio = request.get("fechaInicio") != null
                ? request.get("fechaInicio").toString()
                : null;

        String fechaFin = request.get("fechaFin") != null
                ? request.get("fechaFin").toString()
                : null;

        Integer siscod = request.get("siscod") != null
                ? Integer.parseInt(request.get("siscod").toString())
                : null;

        Integer invnumAper = request.get("invnumAper") != null
                ? Integer.parseInt(request.get("invnumAper").toString())
                : null;

        List<Map<String, Object>> result =
                service.sumatoriaFormaPagoCompleta(
                        fechaInicio,
                        fechaFin,
                        siscod,
                        invnumAper
                );

        return ResponseEntity.ok(result);
    }

}
