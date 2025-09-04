package br.com.Banco.app;

public class validacaoCpf {

    public static boolean validacao(String cpf) {
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
