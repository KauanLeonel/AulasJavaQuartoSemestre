package br.com.Banco.service;

import java.util.List;

import br.com.Banco.Dao.ContaCorrenteDao;
import br.com.Banco.model.ContaCorrente;

public class SalariosDecrescentes {

    public static void main(String[] args) {
        ContaCorrenteDao dao = new ContaCorrenteDao();
        List<ContaCorrente> contas = dao.listar();

        // Ordem decrescente
        System.out.println("Por ordem decrescente");
        contas.stream()
                .sorted((c1, c2) -> Double.compare(c2.getSaldo(), c1.getSaldo()))
                .forEach(System.out::println);
        // .collect(Collectors.toList()); Transforma em uma lista

        // Por nome
        System.out.println("Por nome");
        contas.stream()
                .sorted((c1, c2) -> c1.getTitular().compareToIgnoreCase(c2.getTitular()))
                .forEach(System.out::println);
    }
}
