package com.bartolito.comercial.service;


import com.bartolito.comercial.repository.ConsolidadoCajaRepository;
import com.bartolito.comercial.util.dto.consolidadoCaja.ConsolidadoCajaBatchResponse;
import com.bartolito.comercial.util.dto.consolidadoCaja.ConsolidadoCajaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ConsolidadoCajaService {

    @Autowired
    private ConsolidadoCajaRepository repository;

    // =========================================
    // CONSOLIDADO CAJA - BATCH
    // =========================================
    public List<Map<String, Object>> guardarConsolidadoCajaBatch(
            ConsolidadoCajaBatchResponse request
    ) {

        String xml = construirXml(request);

        return repository.guardarConsolidadoCajaBatch(
                xml,
                request.getCreadoPor(),
                request.getModificadoPor()
        );
    }

    // =========================================
    // CONSTRUIR XML
    // =========================================
    private String construirXml(ConsolidadoCajaBatchResponse request) {

        StringBuilder xml = new StringBuilder();

        xml.append("<root>");

        if (request.getRegistros() != null) {

            for (ConsolidadoCajaResponse item : request.getRegistros()) {

                xml.append("<registro>");

                appendXml(xml, "invnum_aper", item.getInvnumAper());
                appendXml(xml, "siscod", item.getSiscod());
                appendXml(xml, "establecimiento", item.getEstablecimiento());
                appendXml(xml, "usecod", item.getUsecod());
                appendXml(xml, "usuario", item.getUsuario());
                appendXml(xml, "fecha", item.getFecha());

                appendXml(xml, "efectivo_recibido", item.getEfectivoRecibido());
                appendXml(xml, "nota_credito", item.getNotaCredito());
                appendXml(xml, "efectivo_sistema", item.getEfectivoSistema());
                appendXml(xml, "diferencia", item.getDiferencia());

                appendXml(xml, "pinpad", item.getPinpad());
                appendXml(xml, "nc_pinpad", item.getNcPinpad());

                appendXml(xml, "pos", item.getPos());
                appendXml(xml, "nc_pos", item.getNcPos());

                appendXml(xml, "emergencia_pinpad", item.getEmergenciaPinpad());
                appendXml(xml, "nc_emergencia_pinpad", item.getNcEmergenciaPinpad());

                appendXml(xml, "nota_credito_ventas", item.getNotaCreditoVentas());
                appendXml(xml, "nc", item.getNc());

                appendXml(xml, "credito", item.getCredito());
                appendXml(xml, "nc_credito", item.getNcCredito());

                xml.append("</registro>");
            }
        }

        xml.append("</root>");

        return xml.toString();
    }

    // =========================================
    // AGREGAR ELEMENTO XML
    // =========================================
    private void appendXml(
            StringBuilder xml,
            String nombre,
            Object valor
    ) {

        xml.append("<")
                .append(nombre)
                .append(">");

        if (valor != null) {
            xml.append(escapeXml(valor.toString()));
        }

        xml.append("</")
                .append(nombre)
                .append(">");
    }

    // =========================================
    // ESCAPAR XML
    // =========================================
    private String escapeXml(String valor) {

        if (valor == null) {
            return "";
        }

        return valor
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }

    // =========================================
    // CONSOLIDADO CAJA - LISTAR
    // =========================================
    public List<Map<String, Object>> listarConsolidadoCaja(
            String fechaInicio,
            String fechaFin
    ) {

        return repository.listarConsolidadoCaja(
                fechaInicio,
                fechaFin
        );
    }

    // =========================================
    // CONSOLIDADO CAJA - CERRAR CONSOLIDADOS
    // =========================================
    public List<Map<String, Object>> cerrarConsolidado(
            String consolidadoCajaIds,
            String usuario
    ) {

        return repository.cerrarConsolidado(
                consolidadoCajaIds,
                usuario
        );
    }

    // =========================================
    // CONSOLIDADO CAJA - ACTUALIZAR OBSERVACIÓN
    // =========================================
    public List<Map<String, Object>> actualizarObservacion(
            Integer invnumAper,
            String observacion,
            String usuario
    ) {

        return repository.actualizarObservacion(
                invnumAper,
                observacion,
                usuario
        );
    }
}