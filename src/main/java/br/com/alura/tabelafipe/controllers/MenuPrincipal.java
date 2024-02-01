package br.com.alura.tabelafipe.controllers;

import br.com.alura.tabelafipe.models.Dados;
import br.com.alura.tabelafipe.models.DadosModelo;
import br.com.alura.tabelafipe.models.DadosVeiculo;
import br.com.alura.tabelafipe.services.ConsumirAPI;
import br.com.alura.tabelafipe.services.ConversorDeDados;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MenuPrincipal {

    private ConsumirAPI consumirAPI = new ConsumirAPI();

    private ConversorDeDados conversor = new ConversorDeDados();

    private Scanner scan = new Scanner(System.in);

    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";

    public void exibir() {
        // Fase 1

        System.out.println("\nInforme o tipo de veículo que você procura (Carros, Motos ou Caminhões):");
        String veiculo = scan.nextLine();

        if (veiculo.toLowerCase().contains("carr")) {
            veiculo = "carros";
        } else if (veiculo.toLowerCase().contains("moto")) {
            veiculo = "motos";
        } else {
            veiculo = "caminhoes";
        }

        var json = consumirAPI.obterDados(ENDERECO + veiculo + "/marcas");
        List<Dados> dados = conversor.converterLista(json, Dados.class);

        System.out.println(" ");
        dados.stream()
                        .forEach(marca -> System.out.println("Código: " + marca.codigo() + " | Marcas: " + marca.nome()));

        // Fase 2

        System.out.println("\nInforme o código da marcar que você está buscando:");
        String codMarca = scan.nextLine();

        var marcaEscolhida = dados.stream()
                .filter(marca -> Objects.equals(marca.codigo(), codMarca))
                .findFirst();

        json = consumirAPI.obterDados(ENDERECO + veiculo + "/marcas/" + marcaEscolhida.get().codigo() + "/modelos");
        DadosModelo dadosModelos = conversor.converterObjeto(json, DadosModelo.class);

        System.out.println(" ");
        dadosModelos.modelos().stream()
                        .forEach(modelo -> System.out.println("Modelo: " + modelo.nome()));

        // Fase 3

        System.out.println("\nAgora informe uma parte ou em completo o nome do modelo que você está buscando:");
        String nomeModelo = scan.nextLine();

        System.out.println(" ");
        dadosModelos.modelos().stream()
                .filter(modelo -> modelo.nome().toUpperCase().contains(nomeModelo.toUpperCase()))
                .forEach(modelo -> System.out.println("Código: " + modelo.codigo() + " | Modelo: " + modelo.nome()));

        // Fase 4

        System.out.println("\nAgora informe o código do modelo para confirmar os valores:");
        String codModelo = scan.nextLine();

        var modeloEscolhido = dadosModelos.modelos().stream()
                .filter(modelo -> Objects.equals(modelo.codigo(), codModelo))
                .findFirst();

        json = consumirAPI.obterDados(ENDERECO + veiculo + "/marcas/" + marcaEscolhida.get().codigo() + "/modelos/" + modeloEscolhido.get().codigo() + "/anos");
        List<Dados> dadosAnos = conversor.converterLista(json, Dados.class);

        System.out.println(" ");
        String finalVeiculo = veiculo;
        dadosAnos.forEach(ano -> {
            String jsonVeiculo = consumirAPI.obterDados(ENDERECO + finalVeiculo + "/marcas/" + marcaEscolhida.get().codigo() + "/modelos/" + modeloEscolhido.get().codigo() + "/anos/" + ano.codigo());
            DadosVeiculo dadosVeiculo = conversor.converterObjeto(jsonVeiculo, DadosVeiculo.class);
            System.out.println(dadosVeiculo);
        });

    }
}
