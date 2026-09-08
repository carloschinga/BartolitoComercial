package com.bartolito.comercial.util.dto.consolidadoCaja;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ConsolidadoCajaBatchResponse {
    private List<ConsolidadoCajaResponse> registros;
    private String creadoPor;
    private String modificadoPor;
}
