/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercarqtexto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author ufa00906
 */
public class ExercArqTexto {

    /**
     * @param args the command line arguments
     */
    
    public static ArrayList <Cliente> clientesLista = new ArrayList();
    
    public static void importarCSV(String nomeArq) {
        try {
        Scanner scanner = new Scanner(new File(nomeArq)); //lê arquivo externo
        while (scanner.hasNext()) {
            try {
                String linha = (scanner.nextLine());
                String[] vetor = linha.split(";");
                Cliente c = new Cliente(vetor[0], vetor[1], vetor[2], vetor[3]);
                clientesLista.add(c);
                }
            catch (NoSuchElementException excecaoElemento)  { //trata erro
                System.err.println("Arquivo com formato incorreto");
                scanner.close();
                System.exit(1);
                }
            }
        } catch (FileNotFoundException e) { //trata erro
        System.err.println("Arquivo não encontrado");
        }
        
        
        
    }
    
    public static void exportarCSV(String nomeArq) {
        try{
        File arquivo = new File(nomeArq);
        arquivo.createNewFile();
        FileWriter fileWriter = new FileWriter(arquivo, false);
        PrintWriter writer = new PrintWriter(fileWriter);
        for(int i = 0; i < clientesLista.size(); i++){
            writer.println(clientesLista.get(i).toCSV());
        }
        
        writer.flush();
        writer.close();
        }
        catch (IOException excecaoCriacaoArquivo){
            System.out.printf("\nExceção: %s\n", excecaoCriacaoArquivo);
        }
        
    }
   
    public static void main(String[] args) throws IOException {
        importarCSV("Clientes.csv");
        System.out.println(clientesLista);
        Cliente c11 = new Cliente("Tristan", "123.456.123-19", "20/12/1993", "emailteste@gmail.com");
        Cliente c12 = new Cliente("Vanessa", "192.129.129-10", "10/02/1973", "emailteste05@gmail.com");
        clientesLista.add(c11);
        clientesLista.add(c12);
        System.out.println("");
        System.out.println(clientesLista);
        
        exportarCSV("clientes2.csv");
        
    }
    
}
