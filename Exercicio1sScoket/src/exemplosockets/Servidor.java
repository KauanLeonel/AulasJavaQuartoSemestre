package exemplosockets;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {
    public static void main(String[] args) throws IOException {
     ServerSocket servidor = new ServerSocket(8000);
     System.out.println("AIAIAI");
     System.out.println("Porta 8000 aberta! Aguardando conexão...");
     
     Socket cliente = servidor.accept();
     
     System.out.println("Nova conexão com o cliente " + cliente.getInetAddress().getHostAddress());
        System.out.println("Esse socket ficará aberto até que o cliente se desconecte");
     

      // Corpo da resposta HTML
      String html = "<html> <head><title>Servidor Java</title></head> <body><h1>Olá, mundo bão!</h1></body> </html> ";

      // Cabeçalhos HTTP obrigatórios
      String resposta = "HTTP/1.1 200 OK\r\n"
              + "Content-Type: text/html; charset=UTF-8\r\n"
              + "Content-Length: " + html.getBytes().length + "\r\n"
              + "Connection: close\r\n\r\n"
              + html;
    
             

              PrintStream saida = new PrintStream(cliente.getOutputStream()); //O que você vai escrever
      saida.println(resposta);
     servidor.close();
   }
}
