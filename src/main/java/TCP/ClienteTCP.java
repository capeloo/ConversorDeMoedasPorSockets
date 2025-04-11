
package TCP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTCP {
    
    public static void main(String[] args){
    
        try {
        
        Socket socket = new Socket("localhost", 12345);
        
        System.out.println("///// Conversor de Moedas /////");
        Scanner in = new Scanner(System.in);
        System.out.println("Insira o valor em reais que voce deseja converter:  ");
        int valor = in.nextInt();
        System.out.println("Escolha a moeda para qual voce deseja converter [1 = dólar]:  ");
        int moeda = in.nextInt();
        
        // Request
        PrintWriter pr = new PrintWriter(socket.getOutputStream());
        pr.println(valor + "@" + moeda);
        pr.flush();
        
        // Processando response do servidor
        InputStreamReader inStream = new InputStreamReader(socket.getInputStream());
        BufferedReader bf = new BufferedReader(inStream);
        
        String str = bf.readLine();
        System.out.println("Resposta do servidor: " + str);
        
        } catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    
}
