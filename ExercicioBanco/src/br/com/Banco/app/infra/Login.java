package br.com.Banco.app.infra;

import java.io.IOException;
import java.util.List;

import br.com.Banco.Dao.ContaCorrenteDao;
import br.com.Banco.model.ContaCorrente;

public class Login {
    static ContaCorrenteDao dao = new ContaCorrenteDao();
    // Carregar todos os dados.

    // TESTE DE FUNCIONAMENTO
    public static void main(String[] args) throws IOException {
        List<ContaCorrente> teste = dao.listar();
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
        return false;
    }
}
