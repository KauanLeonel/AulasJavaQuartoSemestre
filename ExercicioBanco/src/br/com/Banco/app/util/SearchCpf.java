package br.com.Banco.app.util;

import br.com.Banco.dao.ContaCorrenteDao;
import br.com.Banco.model.ContaCorrente;

public class SearchCpf {

    // Verifica se existe a conta.
    public boolean search(String cpf) {

        // COM JDBC
        ContaCorrenteDao dao = new ContaCorrenteDao();
        ContaCorrente conta = dao.buscaEspecifica(cpf);
        if (conta.getTitular().equals("Teste")) {
            return false;
        } else {
            return true;
        }

    }
}
