package com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MedicoRequestDTO {
    @NotBlank
    @JsonProperty("nome")
    private String nome;

    @NotBlank
    @JsonProperty("cpf")
    private String cpf;

    @NotBlank
    @JsonProperty("email")
    private String email;

    @NotBlank
    @JsonProperty("senha")
    private String senha;

    @NotBlank
    @JsonProperty("confirmacaoSenha")
    private String confirmacaoSenha;

    @NotBlank
    @JsonProperty("especialidade")
    private String especialidade;

    @JsonFormat(pattern="dd/MM/yyyy")
    @JsonProperty("dataNascimento")
    private LocalDate dataNascimento;

    @NotBlank
    @JsonProperty("telefone")
    private String telefone;
}
