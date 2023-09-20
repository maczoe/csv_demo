package com.bowpi.visalinkcsv.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LinkDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nombre;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String codigo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal precio;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String titulo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String descripcion;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String imagen;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer visitas;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer ventas;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String estado;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<LinkRedes> redes;

}
