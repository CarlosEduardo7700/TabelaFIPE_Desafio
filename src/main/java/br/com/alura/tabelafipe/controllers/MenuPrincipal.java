package br.com.alura.tabelafipe.controllers;

import br.com.alura.tabelafipe.service.ConsumirAPI;

import java.util.Scanner;

public class MenuPrincipal {

    private ConsumirAPI consumirAPI = new ConsumirAPI();

    private Scanner scan = new Scanner(System.in);

    public void exibir() {
        System.out.println("\nInforme o tipo de veículo que você procura ('carros', 'motos', 'caminhoes'):");
        String veiculo = scan.nextLine();
        var json = consumirAPI.obterDados("https://parallelum.com.br/fipe/api/v1/" + veiculo + "/marcas");
        System.out.println(json);
    }
}
