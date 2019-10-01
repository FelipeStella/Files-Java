
package Application;

import Entities.Product;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Program {

    
    public static void main(String[] args) throws ParseException{
        
        Scanner sc = new Scanner(System.in);
        
        List<Product> lista = new ArrayList<>();
        
        System.out.print("Digite o caminho do arquivo: ");
        String fonteDoArquivo = sc.nextLine();
        
        File caminhoDoArquivo = new File(fonteDoArquivo);
        
        String fonteParaCriarPasta = caminhoDoArquivo.getParent();
        
        boolean success = new File(fonteParaCriarPasta + "\\out").mkdir();
        
        String criarArquivo = fonteParaCriarPasta + "\\out\\summary.csv";
        
        try(BufferedReader br = new BufferedReader(new FileReader(caminhoDoArquivo))){
            
            String item = br.readLine();
            while(item != null){
                
                String[] campos = item.split(",");
                String nome = campos[0];
                System.out.println(campos[0]);
                System.out.println(campos[1]);
                double preco = Double.parseDouble(campos[1]);
                int quantidade = Integer.parseInt(campos[2]);
                
                lista.add(new Product(nome, preco, quantidade));
             
                item = br.readLine();
            }
            
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(criarArquivo))){
                
                for(Product produto : lista){
                    
                    bw.write(produto.getName() + "," + produto.totalPrice());
                    bw.newLine();
                    
                }
                
                System.out.println(criarArquivo + " Criado!");
                
            }
            catch(IOException ioe){
                System.out.println("Erro ao escrever no arquivo: " + ioe.getMessage());
            }
            
        }
        catch(IOException ioe){
            System.out.println("Erro ao ler o arquivo: " + ioe.getMessage());
        }             
    }   
}
