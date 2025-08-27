package br.com.Banco.app;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import br.com.Banco.Dao.ContaCorrenteDao;
import br.com.Banco.model.ContaCorrente;

public class SalvarDados {
    public static void salvar(ContaCorrente conta) {
        
        //Com JDBC
        //É só usar a função UPTADE do dao


        
        //Com arquivo txt.
        // Path caminho = Paths.get("conta.txt");
        // try (BufferedWriter writer = Files.newBufferedWriter(caminho)) {
        //     String novosDados = "";
        //     for (ContaCorrente c : contas) {
        //         novosDados += c.getCpf() + "," + c.getTitular() + "," + c.getSaldo() + "," + c.getSenha() + "\n";

        //     }
        //     writer.write(novosDados);
        // } catch (Exception e) {
        //     System.out.println("-- ERROR :" + e.getMessage() + "--");
        // }
        // System.out.println("--DADOS SALVOS-- ");
    }
}
