package com.bartolito.comercial.util.dto.liquidacionClinica;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LiquidacionSaveRequest {

    private Integer liquidacionId;
    private Integer invnumAper;

    private LocalDateTime fechaLiquidacion;

    private String establecimiento;
    private String usenam;
    private String usedoc;
    private String turno;

    private Integer siscod;
    private Integer usecod;

    private BigDecimal cantidadSubtotal;
    private BigDecimal subtotalCalculado;
    private BigDecimal subtotalImporte;
    private BigDecimal subtotalDiferencia;

    private BigDecimal notaEfectivoImporte;
    private BigDecimal notaPinpadImporte;
    private BigDecimal notaCreditoImporte;

    private Integer notaEfectivoCantidad;
    private Integer notaPinpadCantidad;
    private Integer notaCreditoCantidad;

    private BigDecimal ingresosImporte;
    private BigDecimal egresosImporte;

    private Integer ingresosCantidad;
    private Integer egresosCantidad;

    private Boolean estado;

    private String validadoPor;
    private LocalDateTime fechaValidacion;

    private Integer numeroGrabados;

    private String observacion;

    private String usuario;
}