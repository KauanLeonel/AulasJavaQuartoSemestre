package exemplosockets;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
     public static void main(String[] args) throws UnknownHostException, IOException {
     
        Socket cliente = new Socket("127.0.0.1", 12345);
     
        System.out.println("O cliente se conectou ao servidor!");
     
     Scanner teclado = new Scanner(System.in);
     
     PrintStream saida = new PrintStream(cliente.getOutputStream());

     Scanner entrada = new Scanner(cliente.getInputStream()); //O que o cliente enviou

     ThreadEntrada thEntrada = new ThreadEntrada(entrada);
     ThreadSaida thSaida = new ThreadSaida(teclado, saida);

     
     thSaida.start();
     
     thEntrada.start();
    

     
   //   System.out.print("Digite algo a ser enviado ao servidor: ");
   //   while (teclado.hasNextLine()) {
      
   //       System.out.print("Digite algo a ser enviado ao servidor: ");
   //       saida.println(teclado.nextLine());
   //       System.out.println("O cliente digitou: " + entrada.nextLine());
   //       if(teclado.equals("sair")){
   //          break;
   //       }
   //   }
     
   
   }
}
