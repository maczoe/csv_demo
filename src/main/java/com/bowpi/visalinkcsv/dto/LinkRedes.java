package com.bowpi.visalinkcsv.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LinkRedes {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nombre;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String url;
}
