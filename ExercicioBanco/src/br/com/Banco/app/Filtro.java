// package br.com.Banco.app;

// import java.util.List;
// import java.util.stream.Collector;
// import java.util.stream.Collectors;

// import javax.swing.JPasswordField;

// import br.com.Banco.model.ContaCorrente;

// public class Filtro {
//         static private List<ContaCorrente> todasAsContas;


//     public static void SalarioMaiorQueDezMil(List<ContaCorrente> contas){
//         todasAsContas = contas.stream()
//             .filter(c -> c.getSaldo() > 1000)
//             .map(c -> c.getTitular())
//             .collect(Collectors.toList());
            
//             todasAsContas.forEach(System.out::println);
//     }

//     public static void TotalDeSaldos(List<ContaCorrente> contas){
//         contas.stream()
//             .filter(c -> c.getSaldo() > 1000)
//             .collect(Collectors.toList());
//     }

//     // public static void Faixas(List<ContaCorrente> contas){
//     //     contas.stream()
//     //         .collect(Collectors.groupingBy(c ->{
//     //             if( <= 5000)
//     //         }));
//     // }
// }
