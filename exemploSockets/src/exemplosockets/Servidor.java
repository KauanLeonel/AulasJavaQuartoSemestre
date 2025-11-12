package exemplosockets;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {
    public static void main(String[] args) throws IOException {
     ServerSocket servidor = new ServerSocket(12345);
     
     System.out.println("Porta 12345 aberta! Aguardando conexão...");
     
     Socket cliente = servidor.accept();
     
     System.out.println("Nova conexão com o cliente " + cliente.getInetAddress().getHostAddress());
        System.out.println("Esse socket ficará aberto até que o cliente se desconecte");
     

     Scanner entrada = new Scanner(cliente.getInputStream());
     
     while (entrada.hasNextLine()) {
       System.out.println("O cliente digitou: " + entrada.nextLine());
     }
     
     entrada.close();
     servidor.close();
   }
}
