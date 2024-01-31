package br.com.alura.tabelafipe.controllers;

import br.com.alura.tabelafipe.models.DadosMarca;
import br.com.alura.tabelafipe.services.ConsumirAPI;
import br.com.alura.tabelafipe.services.ConversorDeDados;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MenuPrincipal {

    private ConsumirAPI consumirAPI = new ConsumirAPI();

    private ConversorDeDados conversor = new ConversorDeDados();

    private Scanner scan = new Scanner(System.in);

    public void exibir() {
        System.out.println("\nInforme o tipo de veículo que você procura ('carros', 'motos', 'caminhoes'):");
        String veiculo = scan.nextLine();

        var json = consumirAPI.obterDados("https://parallelum.com.br/fipe/api/v1/" + veiculo + "/marcas");
        List<DadosMarca> dadosMarca = conversor.converterLista(json, DadosMarca.class);

        System.out.println(" ");
        dadosMarca.stream()
                        .forEach(marca -> System.out.println("Código: " + marca.codigo() + " | Nome: " + marca.nome()));

        System.out.println("\nInforme o código da marcar que você está buscando:");
        String codMarca = scan.nextLine();

        var marcaEscolhida = dadosMarca.stream()
                .filter(marca -> Objects.equals(marca.codigo(), codMarca))
                .findFirst();

        json = consumirAPI.obterDados("https://parallelum.com.br/fipe/api/v1/" + veiculo + "/marcas/" + marcaEscolhida.get().codigo() + "/modelos");

        System.out.println(json);

    }
}
