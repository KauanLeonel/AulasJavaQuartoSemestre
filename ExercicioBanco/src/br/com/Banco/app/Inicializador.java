package br.com.Banco.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import br.com.Banco.Dao.ContaCorrenteDao;
import br.com.Banco.model.Conta;
import br.com.Banco.model.ContaCorrente;

public class Inicializador {
    static ContaCorrenteDao dao = new ContaCorrenteDao();
    // Carregar todos os dados.

    public static List<ContaCorrente> carregarContas() throws IOException {
        //É meio inútil, é só usar o dao
        // Com JDBC
        List<ContaCorrente> contas = dao.listar();

        // Com arquivo
        // Path caminho = Paths.get("conta.txt");
        // List<String> linhas = Files.readAllLines(caminho);
        // for(int i = 0; i < linhas.size(); i++){
        // String[] dados = linhas.get(i).split(",");
        // String num = dados[0];
        // String nome = dados[1];
        // double saldo = Double.parseDouble(dados[2]);
        // String senha = dados[3];
        // contas.add(new ContaCorrente(num, nome, saldo, senha));
        // }

        return contas;
    }

    // TESTE DE FUNCIONAMENTO
    public static void main(String[] args) throws IOException {
        List<ContaCorrente> teste = carregarContas();
        for (int i = 0; i < teste.size(); i++) {
            System.out.println("CPF: " + teste.get(i).getCpf() + "\n" + "Nome: " + teste.get(i).getTitular()
                    + "\nSaldo: " + teste.get(i).getSaldo() + "\nSenha: " + teste.get(i).getSenha());
        }
    }

    public static boolean verificacaoDeLogin(String cpf, String senha) {

        // COM JDBC
        ContaCorrente c = dao.buscaEspecifica(cpf);
        if (c.getCpf().equals(cpf) && c.getSenha().equals(senha)) {
            return true;
        }

        // COM ARQUIVO TEXTO
        // for(ContaCorrente c : contas){
        // if(c.getCpf().equals(cpf) && c.getSenha().equals(senha)){
        // return true;
        // }
        // }
        return false;
    }
}
