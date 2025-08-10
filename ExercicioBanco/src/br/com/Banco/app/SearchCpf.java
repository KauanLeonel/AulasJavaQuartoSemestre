package br.com.Banco.app;

import java.util.List;

import br.com.Banco.model.Conta;
import br.com.Banco.model.ContaCorrente;

public class SearchCpf {
    
    public static int search(List<ContaCorrente> contas, String cpf){
        for (int i = 0; i < contas.size(); i++) {
    if (contas.get(i).getCpf().equals(cpf)) {
        return i;
            }
        }
        return -1;
    }
}
