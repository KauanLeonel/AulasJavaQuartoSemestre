package br.com.Banco.app.util;

public class validacaoCpf {

    public boolean validacao(String cpf) {
        boolean vali = true;
        if (cpf.length() != 11)
            vali = false;

        for (int i = 0; i < cpf.length(); i++) {
            if (!Character.isDigit(cpf.charAt(i))) {
                vali = false;
                break;
            }
        }
        return vali;
    }
}
