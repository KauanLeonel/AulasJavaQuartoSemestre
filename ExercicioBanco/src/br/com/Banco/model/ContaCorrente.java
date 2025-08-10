package br.com.Banco.model;


import br.com.Banco.Exception.SaldoInsuficienteException;

public class ContaCorrente extends Conta {
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

     //Precisa ter um construtor na classe, visto que tem no pai
     public ContaCorrente(String cpf, String titular, double saldo, String senha) {
        super(cpf, titular, saldo);
        this.senha = senha;
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
