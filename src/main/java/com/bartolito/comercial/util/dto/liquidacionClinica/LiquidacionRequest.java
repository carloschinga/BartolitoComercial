package com.bartolito.comercial.util.dto.liquidacionClinica;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class LiquidacionRequest {
   private Integer invnumAper;
   private String fechaInicio;
   private String fechaFin;
}
