package com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AtendimentoResponseDTO {
    @NotBlank
    @JsonProperty("medico")
    private String medico;

    @NotBlank
    @JsonProperty("paciente")
    private String paciente;

    @NotBlank
    @JsonProperty("data_hora")
    private LocalDateTime dataHora;
}
