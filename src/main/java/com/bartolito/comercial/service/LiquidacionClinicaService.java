package com.bartolito.comercial.service;

import com.bartolito.comercial.repository.LiquidacionCajaRepository;
import com.bartolito.comercial.repository.LiquidacionClinicaRepository;
import com.bartolito.comercial.util.dto.liquidacionClinica.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
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


    // =========================================
    // LIQUIDACIÓN - OBTENER
    // =========================================
    public LiquidacionResponse obtenerDatosLiquidacion(
            LiquidacionRequest t) {

        // 1. Obtener liquidación guardada
        Map<String, Object> liquidacion =
                repository.obtenerLiquidacion(t.getInvnumAper());

        if (liquidacion == null) {
            throw new RuntimeException(
                    "No se encontró liquidación para la apertura "
                            + t.getInvnumAper()
            );
        }

        // 2. Obtener cabecera del cierre
        List<Map<String, Object>> cabeceraList =
                repository.listarCabecera(t.getInvnumAper());

        if (cabeceraList == null || cabeceraList.isEmpty()) {
            throw new RuntimeException(
                    "No se encontró cabecera para el cierre"
            );
        }

        Map<String, Object> cabecera = cabeceraList.get(0);

        Integer invnum = ((Number) cabecera.get("invnum")).intValue();

        // 3. Métodos de pago disponibles
        List<Map<String, Object>> metodosPago =
                repository.listarMetodosPago(
                        t.getFechaInicio(),
                        t.getFechaFin(),
                        t.getInvnumAper()
                );

        // 4. Montos reales del cierre
        List<Map<String, Object>> formasPagoData =
                repository.listarFormasPago(invnum);

        // 5. Información adicional
        Map<String, Object> ingresos =
                repository.listarIngresos(t.getInvnumAper());

        Map<String, Object> egresos =
                repository.listarEgresos(t.getInvnumAper());

        Map<String, Object> notasCredito =
                repository.listarNotasCredito(t.getInvnumAper());

        LiquidacionResponse datos =
                new LiquidacionResponse();

        // ============================
        // CABECERA
        // ============================

        datos.setLiquidacionId(
                ((Number) liquidacion.get("liquidacion_id")).intValue()
        );

        datos.setInvnumAper(t.getInvnumAper());
        datos.setInvnum(invnum);

        datos.setEstablecimiento(
                (String) liquidacion.get("establecimiento")
        );

        datos.setCajero(
                (String) liquidacion.get("usenam")
        );

        datos.setDni(
                (String) liquidacion.get("usedoc")
        );

        datos.setTurno(
                (String) liquidacion.get("turno")
        );

        datos.setSiscod(
                liquidacion.get("siscod") != null
                        ? ((Number) liquidacion.get("siscod")).intValue()
                        : null
        );

        datos.setUsecod(
                liquidacion.get("usecod") != null
                        ? ((Number) liquidacion.get("usecod")).intValue()
                        : null
        );

        // ============================
        // FECHA
        // ============================

        if (liquidacion.get("fecha_liquidacion") != null) {

            Timestamp fecha =
                    (Timestamp) liquidacion.get("fecha_liquidacion");

            LocalDate fechaPeru = fecha.toInstant()
                    .atZone(ZoneId.of("America/Lima"))
                    .toLocalDate();

            datos.setFechaCierre(
                    fechaPeru.toString()
            );
        }

        // ============================
        // INGRESOS / EGRESOS / NC
        // ============================

        datos.setIngresos(ingresos);
        datos.setEgresos(egresos);
        datos.setNotasCredito(notasCredito);

        // ============================
        // FORMAS DE PAGO
        // ============================

        List<FormaPagoResponse> formasPago =
                new ArrayList<>();

        Map<String, Map<String, Object>> montosPorDocpag =
                new HashMap<>();

        for (Map<String, Object> fp : formasPagoData) {

            String docpag = (String) fp.get("docpag");

            montosPorDocpag.put(docpag, fp);
        }

        for (Map<String, Object> metodo : metodosPago) {

            String docpag = (String) metodo.get("docpag");
            String docdes = (String) metodo.get("docdes");

            FormaPagoResponse forma =
                    new FormaPagoResponse();

            forma.setDocpag(docpag);
            forma.setDocdes(docdes);

            Map<String, Object> montoData =
                    montosPorDocpag.get(docpag);

            if (montoData != null) {

                forma.setQtydoc(
                        ((Number) montoData.get("qtydoc")).intValue()
                );

                forma.setTotdoc(
                        (BigDecimal) montoData.get("totdoc")
                );

                forma.setTotcalc(
                        (BigDecimal) montoData.get("totcalc")
                );

                BigDecimal diferencia =
                        forma.getTotdoc()
                                .subtract(forma.getTotcalc());

                forma.setDiferencia(diferencia);

            } else {

                forma.setQtydoc(0);
                forma.setTotdoc(BigDecimal.ZERO);
                forma.setTotcalc(BigDecimal.ZERO);
                forma.setDiferencia(BigDecimal.ZERO);
            }

            formasPago.add(forma);
        }

        datos.setFormasPago(formasPago);

        // ============================
        // SUBTOTAL
        // ============================

        FormaPagoResponse subtotal =
                new FormaPagoResponse();

        subtotal.setDocdes("SUB TOTAL:");

        int totalQty = 0;

        BigDecimal totalTotdoc =
                BigDecimal.ZERO;

        BigDecimal totalTotcalc =
                BigDecimal.ZERO;

        BigDecimal totalDiferencia =
                BigDecimal.ZERO;

        for (FormaPagoResponse fp : formasPago) {

            totalQty += fp.getQtydoc();

            totalTotdoc =
                    totalTotdoc.add(fp.getTotdoc());

            totalTotcalc =
                    totalTotcalc.add(fp.getTotcalc());

            totalDiferencia =
                    totalDiferencia.add(fp.getDiferencia());
        }

        subtotal.setQtydoc(totalQty);
        subtotal.setTotdoc(totalTotdoc);
        subtotal.setTotcalc(totalTotcalc);
        subtotal.setDiferencia(totalDiferencia);

        datos.setSubtotal(subtotal);

        // ============================
        // OBSERVACIÓN
        // ============================

        datos.setObservacion(
                (String) liquidacion.get("observacion")
        );

        return datos;
    }

    public Map<String, Object> saveOrUpdate(
            LiquidacionSaveRequest request) {

        return repository.saveOrUpdate(request);
    }
}
