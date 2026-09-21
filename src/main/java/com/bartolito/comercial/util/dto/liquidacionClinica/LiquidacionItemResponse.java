package com.bartolito.comercial.util.dto.liquidacionClinica;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor

public class LiquidacionItemResponse {
    private Integer cantidad;
    private BigDecimal importeCalc;
    private BigDecimal importeEntregado;
    private BigDecimal diferencia;
    private BigDecimal importeDeclarado;
}
