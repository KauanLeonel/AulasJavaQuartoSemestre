package br.com.Banco.model;

import br.com.Banco.Exception.SaldoInsuficienteException;

public abstract class Conta {
    private int numero;
    private String titular;
    private double saldo;
    
    public void sacar(double valor) throws SaldoInsuficienteException{
    }
    public void depositar(double valor){
    }

    public void imprimirDados(){
        System.out.println("Número: "+ numero + "\nTitular: " + titular + "\nSaldo: " + saldo);
    }

    //#region Getters And Setters
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
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
    //#region
    
}