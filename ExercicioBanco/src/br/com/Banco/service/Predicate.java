package br.com.Banco.service;

import java.util.List;
import java.util.stream.Collectors;

import br.com.Banco.model.Conta;
import br.com.Banco.model.ContaCorrente;

public class Predicate {
    
   public Predicate(Conta conta){
       
   }

   public static void Maior5K(List<ContaCorrente> contas){
    List<ContaCorrente> contas5k = contas.stream()
        .filter(c -> c.getSaldo() > 5000)
        .collect(Collectors.toList());
   }

   public static void par(List<ContaCorrente> contas){
    List<ContaCorrente> contasPares = contas.stream()
        .filter(c -> Integer.parseInt(String.valueOf(c.getCpf().charAt(c.getCpf().length() - 1))) % 2 == 0)
        .collect(Collectors.toList());
   }

   
}
