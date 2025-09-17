package br.com.Banco.service;

import java.util.List;
import java.util.function.Predicate;

import br.com.Banco.dao.ContaCorrenteDao;
import br.com.Banco.model.ContaCorrente;

public class SalarioMaior5k {

    public static Predicate<ContaCorrente> maior5K() {
        return c -> c.getSaldo() > 5000;
    }

    public static void main(String[] args) {
        ContaCorrenteDao dao = new ContaCorrenteDao();
        List<ContaCorrente> contas = dao.listar();

        contas.stream()
                .filter(maior5K())
                .forEach(System.out::println);
    }
}
