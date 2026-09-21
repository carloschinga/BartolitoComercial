package com.bartolito.comercial.service;

import com.bartolito.comercial.repository.LiquidacionClinicaRepository;
import com.bartolito.comercial.util.dto.liquidacionClinica.FormaPagoResponse;
import com.bartolito.comercial.util.dto.liquidacionClinica.LiquidacionDataResponse;
import com.bartolito.comercial.util.dto.liquidacionClinica.LiquidacionItemResponse;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class LiquidacionPDFService {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private LiquidacionClinicaRepository repository;

    public byte[] generarPDFLiquidacion(String fechaInicio, String fechaFin, Integer invnumAper) throws Exception {
        try {

            // 2. Obtener la cabecera del cierre
            List<Map<String, Object>> cabeceraList = repository.listarCabecera(invnumAper);

            if (cabeceraList == null || cabeceraList.isEmpty()) {
                throw new RuntimeException("No se encontró cabecera para el cierre");
            }

            Map<String, Object> cabecera = cabeceraList.get(0);
            Integer invnum = (Integer) cabecera.get("invnum");

            // 3. Obtener los métodos de pago disponibles
            List<Map<String, Object>> metodosPago = repository.listarMetodosPago(fechaInicio, fechaFin, invnumAper);

            // 4. Obtener las formas de pago con montos del cierre
            List<Map<String, Object>> formasPagoData = repository.listarFormasPago(invnum);

            // 5. Construir el DTO de respuesta
            LiquidacionDataResponse datos = new LiquidacionDataResponse();

            // Datos de cabecera
            datos.setEstablecimiento("INSANOR");
            datos.setInvnumAper(invnumAper);
            datos.setInvnum(invnum);
            datos.setCajero((String) cabecera.get("cajero"));
            datos.setDni((String) cabecera.get("dni"));
            datos.setTurno((String) cabecera.get("turno"));
            datos.setFechaInicio(fechaInicio);
            datos.setFechaFin(fechaFin);

            // Formatear fecha
            if (cabecera.get("fecha") != null) {
                Date fecha = (Date) cabecera.get("fecha");

                LocalDate fechaPeru = fecha.toInstant()
                        .atZone(ZoneId.of("America/Lima"))
                        .toLocalDate();

                datos.setFechaCierre(fechaPeru.toString());
            }
            // 6. Mapear formas de pago
            List<FormaPagoResponse> formasPago = new ArrayList<>();

            // Crear un mapa para búsqueda rápida de montos por docpag
            Map<String, Map<String, Object>> montosPorDocpag = new HashMap<>();
            for (Map<String, Object> fp : formasPagoData) {
                String docpag = (String) fp.get("docpag");
                montosPorDocpag.put(docpag, fp);
            }

            // Iterar sobre los métodos de pago disponibles
            for (Map<String, Object> metodo : metodosPago) {
                String docpag = (String) metodo.get("docpag");
                String docdes = (String) metodo.get("docdes");

                FormaPagoResponse forma = new FormaPagoResponse();
                forma.setDocpag(docpag);
                forma.setDocdes(docdes);

                // Buscar si tiene montos en este cierre
                if (montosPorDocpag.containsKey(docpag)) {
                    Map<String, Object> montoData = montosPorDocpag.get(docpag);
                    forma.setQtydoc(((Number) montoData.get("qtydoc")).intValue());
                    forma.setTotdoc((BigDecimal) montoData.get("totdoc"));
                    forma.setTotcalc((BigDecimal) montoData.get("totcalc"));

                    // Calcular diferencia
                    BigDecimal diferencia = ((BigDecimal) montoData.get("totdoc"))
                            .subtract((BigDecimal) montoData.get("totcalc"));
                    forma.setDiferencia(diferencia);
                } else {
                    // Método de pago sin montos en este cierre
                    forma.setQtydoc(0);
                    forma.setTotdoc(BigDecimal.ZERO);
                    forma.setTotcalc(BigDecimal.ZERO);
                    forma.setDiferencia(BigDecimal.ZERO);
                }

                formasPago.add(forma);
            }

            datos.setFormasPago(formasPago);

            // 7. Calcular subtotal
            FormaPagoResponse subtotal = new FormaPagoResponse();
            subtotal.setDocdes("SUB TOTAL:");

            int totalQty = 0;
            BigDecimal totalTotdoc = BigDecimal.ZERO;
            BigDecimal totalTotcalc = BigDecimal.ZERO;
            BigDecimal totalDiferencia = BigDecimal.ZERO;

            for (FormaPagoResponse fp : formasPago) {
                totalQty += fp.getQtydoc();
                totalTotdoc = totalTotdoc.add(fp.getTotdoc());
                totalTotcalc = totalTotcalc.add(fp.getTotcalc());
                totalDiferencia = totalDiferencia.add(fp.getDiferencia());
            }

            subtotal.setQtydoc(totalQty);
            subtotal.setTotdoc(totalTotdoc);
            subtotal.setTotcalc(totalTotcalc);
            subtotal.setDiferencia(totalDiferencia);
            datos.setSubtotal(subtotal);

            // 8. Observación
            datos.setObservacion("Liquidación generada automáticamente");

            // 9. Procesar plantilla Thymeleaf
            Context context = new Context();
            context.setVariable("datos", datos);
            context.setVariable("fechaActual", new Date());

            String logoBase64 = getLogoBase64();
            context.setVariable("logoBase64", logoBase64);

            String htmlContent = templateEngine.process("plantillaLiquidacionInsanor", context);

            // 10. Convertir a PDF
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(htmlContent, null);
            builder.toStream(outputStream);
            builder.run();

            return outputStream.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String getLogoBase64() {
        try {
            ClassPathResource resource = new ClassPathResource("static/images/logo-insanor.png");
            byte[] imageBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (Exception e) {
            System.out.println("Logo no encontrado, continuando sin logo");
            return "";
        }
    }
}