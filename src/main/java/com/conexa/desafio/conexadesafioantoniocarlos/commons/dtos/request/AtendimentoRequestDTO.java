package com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AtendimentoRequestDTO {
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("dataHora")
    private LocalDateTime dataHora;

    @JsonProperty("paciente")
    private PacienteRequestDTO paciente;
}
