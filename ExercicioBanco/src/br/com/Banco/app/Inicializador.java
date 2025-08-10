package br.com.Banco.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import br.com.Banco.model.Conta;
import br.com.Banco.model.ContaCorrente;

public class Inicializador {
    //Carregar todos os dados.

    public static List<ContaCorrente> carregarContas() throws IOException{

        Path caminho = Paths.get("conta.txt");

        List<String> linhas = Files.readAllLines(caminho);
        List<ContaCorrente> contas = new ArrayList<>();
        for(int i = 0; i < linhas.size(); i++){
        String[] dados = linhas.get(i).split(",");
        String num = dados[0];
        String nome = dados[1];
        double saldo = Double.parseDouble(dados[2]);
        String senha = dados[3];
        
        contas.add(new ContaCorrente(num, nome, saldo, senha));
        
        }
        return contas;
    }
        
    public static void main(String[] args) throws IOException{
        List<ContaCorrente> teste =  carregarContas();
        for (int i = 0; i < teste.size(); i++) {
            System.out.println("CPF: " + teste.get(i).getCpf() + "\n" + "Nome: " + teste.get(i).getTitular() + "\nSaldo: " + teste.get(i).getSaldo() + "\nSenha: " + teste.get(i).getSenha());
        }
    }

    public static boolean verificacaoDeLogin(String cpf, String senha, List<ContaCorrente> contas){
        for(ContaCorrente c : contas){
            if(c.getCpf().equals(cpf) && c.getSenha().equals(senha)){
                return true;
            }
        }
        return false;
    }
}

