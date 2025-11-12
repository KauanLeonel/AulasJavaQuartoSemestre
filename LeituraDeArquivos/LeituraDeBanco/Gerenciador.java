package LeituraDeBanco;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Gerenciador {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        ArrayList<Cliente> listaDeClientes = new ArrayList<>();
        String arquivo = "dados.csv";

        System.out.println("Bem vido ao gerenciador de clientes! :)");

        lerArquivo(arquivo, listaDeClientes);

        listaDeClientes.sort(null);
        System.out.println(listaDeClientes);
        for (int i = 0; i < listaDeClientes.size(); i++) {
            System.out.println(listaDeClientes.get(i).getNome());
        }
        gravarArquivoBinario(listaDeClientes, "dados_ordenados.txt");
    }

    public static void lerArquivo(String arquivo, ArrayList listaDeClientes) throws FileNotFoundException, IOException {

        BufferedReader buffRead = new BufferedReader(new FileReader(arquivo, StandardCharsets.ISO_8859_1));
        String linha = buffRead.readLine();
        while (linha != null) {
            // System.out.println(linha);
            Cliente c = new Cliente();
            c.registrarCliente(linha);
            listaDeClientes.add(c);
            linha = buffRead.readLine();
        }
        buffRead.close();
    }

    public static void gravarArquivoBinario(ArrayList<Cliente> lista, String nomeArq) {

        File arq = new File(nomeArq);
        try {
            arq.delete();
            arq.createNewFile();

            ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq));
            objOutput.writeObject(lista);
            objOutput.close();

        } catch (IOException erro) {
            System.out.printf("Erro: %s", erro.getMessage());
        }
    }

}
