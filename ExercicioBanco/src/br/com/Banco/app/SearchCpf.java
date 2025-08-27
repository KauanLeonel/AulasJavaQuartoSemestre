package br.com.Banco.app;

import java.util.List;

import br.com.Banco.Dao.ContaCorrenteDao;
import br.com.Banco.model.Conta;
import br.com.Banco.model.ContaCorrente;

public class SearchCpf {
    

    //Verifica se existe a conta.
    public static int search(String cpf){
    
    //COM JDBC

    ContaCorrenteDao dao = new ContaCorrenteDao();
    ContaCorrente conta = dao.buscaEspecifica(cpf);
    if(conta.getTitular().equals("Teste")){
        return 1;
    } else{
        return - 10;
    }

    
    //COM ARQUIVO texto
    //     for (int i = 0; i < contas.size(); i++) {
    // if (contas.get(i).getCpf().equals(cpf)) {
    //     return i;
    //         }
    //     }
    //     return -10;
    // }
    }
}
