package exemplosockets;

import java.io.PrintStream;
import java.util.Scanner;

public class ThreadSaida extends Thread {
    Scanner teclado;
    PrintStream saida;

    ThreadSaida(Scanner teclado, PrintStream saida) {
        this.saida = saida;
        this.teclado = teclado;
    }

    public void run() {
        System.out.println("Thread Saida ligada");

        while (teclado.hasNextLine()) {
            System.out.println("Thread Saida ligada");
            System.out.print("Digite algo a ser enviado ao servidor: ");
            saida.println(teclado.nextLine());
            String linha = teclado.nextLine();
            if (linha.equalsIgnoreCase("sair")) {
                break;
            }
            saida.println(linha);
        }
        saida.close();

        teclado.close();
    }
}
