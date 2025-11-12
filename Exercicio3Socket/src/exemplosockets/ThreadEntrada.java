/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemplosockets;

import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author cg080111
 */
public class ThreadEntrada extends Thread {
    Scanner entrada;
    ThreadEntrada(Scanner entrada){
        this.entrada = entrada;
    }

    public void run() {
       while (entrada.hasNextLine()) {
       System.out.println("O cliente digitou: " + entrada.nextLine());
     }

     entrada.close();
    }
    
}
