package br.com.Banco.app.infra;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SalvarExtrato {
    public static void salvar(ArrayList<String> transferencias) {
        Path caminho = Paths.get("extrato_numeroConta.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(caminho)) {
            String novosDados = "";
            for (String c : transferencias) {
                novosDados += c + "\n";

            }
            writer.write(novosDados);
        } catch (Exception e) {
            System.out.println("-- ERROR :" + e.getMessage() + "--");
        }
        System.out.println("--DADOS SALVOS-- ");
    }
}
