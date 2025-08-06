package br.com.Banco.model;


import br.com.Banco.Exception.SaldoInsuficienteException;

public class ContaCorrente extends Conta {
    
    //Precisa ter um construtor na classe, visto que tem no pai
     public ContaCorrente(int numero, String titular, double saldo) {
        super(numero, titular, saldo);
    }

    @Override
    public void sacar(double valor) throws SaldoInsuficienteException{
        if(valor > getSaldo())
        throw new SaldoInsuficienteException("Saldo insuficiente");
        setSaldo(getSaldo() - valor);
    }

    @Override
    public void depositar(double valor) {
        setSaldo(getSaldo() + valor);

    }
}
