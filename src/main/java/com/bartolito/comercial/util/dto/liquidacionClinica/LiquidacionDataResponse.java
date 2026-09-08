package com.bartolito.comercial.util.dto.liquidacionClinica;


import lombok.Data;

import java.util.List;

@Data
public class LiquidacionDataResponse {

    // Datos de cabecera
    private String establecimiento;
    private Integer invnumAper;      // S-APERTURA
    private Integer invnum;          // Número de cierre
    private String cajero;
    private String dni;
    private String turno;
    private String fechaInicio;
    private String fechaFin;
    private String fechaCierre;      // Fecha del cierre formateada

    // Lista de formas de pago con montos
    private List<FormaPagoResponse> formasPago;

    // Subtotal calculado
    private FormaPagoResponse subtotal;

    // Observación
    private String observacion;
}