package br.com.Banco.app;

import java.io.BufferedWriter;
import java.nio.file.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.Banco.Exception.SaldoInsuficienteException;
import br.com.Banco.model.Conta;
import br.com.Banco.model.ContaCorrente;

public class App {

    public static void extrato(Conta c) {
        System.out.println("-- EXTRATO ATUAL : R$" + c.getSaldo() + "--");
    }

    public static void main(String[] args) throws Exception {

        // Ler dados
        Path caminho = Paths.get("conta.txt");
        List<String> linhas = Files.readAllLines(caminho);

        // Transformar dados
        
        String[] dados = linhas.get(0).split(",");
        int num = Integer.parseInt(dados[0]);
        String nome = dados[1];
        double saldo = Double.parseDouble(dados[2]);

        // Criar conta
        ContaCorrente c = new ContaCorrente(num, nome, saldo);

        Scanner sc = new Scanner(System.in);

        // Operações
        System.out.println("Bem-vindo: " + nome);
        while (true) {
            try {
                System.out.println("\n[0] - Extrato\n[1] - Depositar\n[2] - Sacar\n[3] - Salvar dados\n[4] - Sair");
                int opcao = sc.nextInt();

                switch (opcao) {
                    case 0:
                        extrato(c);
                        break;
                    case 1:
                        System.out.println("Digite o valor a ser depositado: ");
                        c.depositar(sc.nextInt());
                        extrato(c);

                        break;
                    case 2:
                        System.out.println("Digite o valor a ser sacado: ");
                        try {
                            c.sacar(sc.nextDouble());
                            extrato(c);
                        } catch (SaldoInsuficienteException e) {
                            System.out.println("-- ERROR :" + e.getMessage() + "--");
                        }

                        break;

                    case 3:
                        Path novoCaminho = Paths.get("conta_atualizada.txt");
                        try (BufferedWriter writer = Files.newBufferedWriter(novoCaminho)) {
                            String novosDados = c.getNumero() + "," + c.getTitular() + "," + c.getSaldo();
                            writer.write(novosDados);
                        } catch (Exception e) {
                            System.out.println("-- ERROR :" + e.getMessage() + "--");
                        }
                        System.out.println("--DADOS SALVOS-- ");
                        break;

                    case 4:
                        System.out.println("Obrigado por usar o banco Leonel. Até logo!");
                        return;
                    default:
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("-- ERROR : ENTRADA INVÁLIDA--");
                sc.nextLine(); // Limpa o buffer do scanner para evitar loop infinito (Estava ficando)

            }
        }
    }
}
