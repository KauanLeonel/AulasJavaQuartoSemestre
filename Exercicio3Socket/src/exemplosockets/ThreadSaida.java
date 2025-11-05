package exemplosockets;

import java.io.PrintStream;
import java.util.Scanner;

public class ThreadSaida extends Thread {
     static Scanner teclado;
     static PrintStream saida;
        ThreadSaida(Scanner teclado, PrintStream saida){
            this.saida = saida;
            this.teclado = teclado;
        }
    public static void run(String[] args) {
        System.out.println("Thread Saida ligada");

        while (teclado.hasNextLine()) {
            System.out.println("Thread Saida ligada");
            System.out.print("Digite algo a ser enviado ao servidor: ");
            saida.println(teclado.nextLine());
            if(teclado.equals("sair")){
               break;
            }
        }
        saida.close();
     
        teclado.close();
    }
}
