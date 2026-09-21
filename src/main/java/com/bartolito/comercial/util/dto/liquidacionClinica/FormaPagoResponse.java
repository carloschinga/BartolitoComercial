package com.bartolito.comercial.util.dto.liquidacionClinica;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FormaPagoResponse {
    private String docpag;        // Código de documento de pago
    private String docdes;        // Descripción (ej: "EFECTIVO")
    private Integer qtydoc;       // Cantidad
    private BigDecimal totdoc;    // IMPORTE CALC (Sistema)
    private BigDecimal totcalc;   // IMPORTE ENTREGADO (Cajero)
    private BigDecimal diferencia; // totdoc - totcalc (se calcula)
}