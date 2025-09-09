package br.com.Banco.model;

import br.com.Banco.exception.SaldoInsuficienteException;

public abstract class Conta {

    // Atributos
    private String cpf;
    private String titular;
    private double saldo;

    // Construtor
    public Conta(String cpf, String titular, double saldo) {
        this.cpf = cpf;
        this.titular = titular;
        this.saldo = saldo;
    }

    // Métodos sacar e depositar
    public void sacar(double valor) throws SaldoInsuficienteException {
    }

    public void depositar(double valor) {
    }

    // Imprimir dados
    public void imprimirDados() {
        System.out.println("Número: " + cpf + "\nTitular: " + titular + "\nSaldo: " + saldo);
    }

    // #region Getters and Setters
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    // #region

}