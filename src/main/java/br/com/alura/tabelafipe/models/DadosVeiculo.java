package br.com.alura.tabelafipe.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosVeiculo(
        @JsonAlias("Valor")
        String valor,
        @JsonAlias("AnoModelo")
        Integer ano,
        @JsonAlias("Marca")
        String marca,
        @JsonAlias("Modelo")
        String modelo,
        @JsonAlias("Combustivel")
        String combustivel,
        @JsonAlias("CodigoFipe")
        String codigoFipe,
        @JsonAlias("MesReferencia")
        String mesReferencia,
        @JsonAlias("SiglaCombustivel")
        String siglaCombustivel

) {
}
