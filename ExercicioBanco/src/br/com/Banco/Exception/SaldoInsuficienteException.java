package br.com.Banco.Exception;


public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String mensagem){
        super(mensagem);
    }
}
