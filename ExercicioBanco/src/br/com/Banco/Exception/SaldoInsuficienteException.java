package br.com.Banco.exception;


public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String mensagem){
        super(mensagem);
    }
}
