

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import LeituraDeBanco.Cliente;

public class Arquivos {

    public static void main(String[] args) throws IOException {
        String x = "boa tarde;voce esta ouvindo;a radio caragua fm;seja bem vindo !";
        // String y[] = x.split(";");
        
        // for (int i = 0; i < y.length; i++) {
        //     System.out.println(y[i]);
        // }
        escrever(x);
        ler();
        
    }

    private static void escrever(String x) throws IOException {

    //------------------------------------------------------------------------------------------------
        
        String arqEscrita = "bd.txt";
        // ESCRITA

        System.out.println("Escrevendo no arquivo " + arqEscrita);
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(arqEscrita,false));
        buffWrite.append(x + "\n");
        buffWrite.append("isso eh um teste" + "\n");
        buffWrite.append("isso eh OUTRO teste" + "\n");
        buffWrite.close();
    }

    private static void ler() throws FileNotFoundException, IOException {
         // LEITURA

        // Escolhendo o arquivo que será lido
        String arqLeitura = "bd.txt";
                
        BufferedReader buffRead = new BufferedReader(new FileReader(arqLeitura));
        System.out.println("Lendo o arquivo " + arqLeitura);
        String linha = buffRead.readLine();
        while (linha != null) {
            System.out.println(linha);
            
            linha = buffRead.readLine();
        }
        buffRead.close();

    }
    
}