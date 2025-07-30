package br.com.Banco.model;


import br.com.Banco.Exception.SaldoInsuficienteException;

public class ContaCorrente extends Conta {
    
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
