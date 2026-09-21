package com.bartolito.comercial.util.dto.consolidadoCaja;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ConsolidadoCajaResponse {

    private Integer invnumAper;
    private Integer siscod;
    private String establecimiento;
    private Integer usecod;
    private String usuario;
    private String fecha;

    private BigDecimal efectivoRecibido;
    private BigDecimal notaCredito;
    private BigDecimal efectivoSistema;
    private BigDecimal diferencia;

    private BigDecimal pinpad;
    private BigDecimal ncPinpad;

    private BigDecimal pos;
    private BigDecimal ncPos;

    private BigDecimal emergenciaPinpad;
    private BigDecimal ncEmergenciaPinpad;

    private BigDecimal notaCreditoVentas;
    private BigDecimal nc;

    private BigDecimal credito;
    private BigDecimal ncCredito;

}
