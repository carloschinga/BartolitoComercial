package com.bartolito.comercial.util.dto.liquidacionClinica;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class LiquidacionResponse {

    private Integer liquidacionId;
    private Integer invnumAper;
    private Integer invnum;

    private String fechaCierre;
    private String establecimiento;
    private String cajero;
    private String dni;
    private String turno;

    private Integer siscod;
    private Integer usecod;

    private String fechaInicio;
    private String fechaFin;

    private List<FormaPagoResponse> formasPago;
    private FormaPagoResponse subtotal;

    private Map<String, Object> notasCredito;
    private Map<String, Object> ingresos;
    private Map<String, Object> egresos;

    private String observacion;
}
