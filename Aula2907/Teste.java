package Aula2907;

 class Conta{
        double saldo;
        void depositar(double valor){
            saldo += valor;
        }
    }
public class Teste {

   
    public static void main(String[] args) {
        Conta ContaDaMaria = new Conta();
        Conta ContaDoJoao = new Conta();


        ContaDoJoao.depositar(500.0);
        ContaDoJoao.depositar(300.0);
        System.out.println(ContaDaMaria.saldo);

    }
}