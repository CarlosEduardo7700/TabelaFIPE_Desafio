package br.com.alura.tabelafipe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosModelo(
        List<Dados> modelos
) {
}
