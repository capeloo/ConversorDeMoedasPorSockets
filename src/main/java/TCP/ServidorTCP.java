
package TCP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;

public class ServidorTCP {
    
    public static void main(String[] args){
        
        Random random = new Random();
        int cotacao = random.nextInt(7);
        
        try {
            // Instancia o ServerSocket ouvindo a porta 12345
            ServerSocket ss = new ServerSocket(12345);
            System.out.println("Servidor ouvindo na porta 12345");
            
            while(true){
                // O método accept() bloqueia a execução até que
                // o servidor receba um pedido de conexão
                Socket socket = ss.accept();
                System.out.println("Cliente conectado");
                
                // Processando request do cliente 
                InputStreamReader in = new InputStreamReader(socket.getInputStream());
                BufferedReader bf = new BufferedReader(in);
                
                String str = bf.readLine();
                String[] strSeparada = str.split("@");
                System.out.println(Arrays.toString(strSeparada));
                
                int valor = Integer.parseInt(strSeparada[0]);
                int moeda = Integer.parseInt(strSeparada[1]);
                
                PrintWriter pr = new PrintWriter(socket.getOutputStream());
                
                switch(moeda) {
                    case 1:       
                        int res;
                        res = valor*cotacao;
                        
                        // Response ao cliente
                        pr.println("Na cotação atual: " + cotacao + ", " + "o valor convertido e: " + res + " dolares");
                        pr.flush();
                    break;
                    default:
                        // Response ao cliente
                        pr.println("Moeda nao encontrada");
                        pr.flush();
                }
                
            }
        } catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
