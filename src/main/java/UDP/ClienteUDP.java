
package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteUDP {
    
    public static void main(String[] args) throws SocketException, IOException{
        
        try {
            
            DatagramSocket ds = new DatagramSocket();
            
            System.out.println("///// Conversor de Moedas /////");
            Scanner in = new Scanner(System.in);
            System.out.println("Insira o valor em reais que voce deseja converter:  ");
            int valor = in.nextInt();
            System.out.println("Escolha a moeda para qual voce deseja converter [1 = dólar]:  ");
            int moeda = in.nextInt();
            
            String content = (valor + "@" + moeda);
                       
            // Request
            byte[] msg = new byte[100];
            msg = content.getBytes();
            InetAddress ip = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(msg, msg.length, ip, 12345);
            
            ds.send(packet);
            
            // Processando response do servidor
            byte[] msgReceived = new byte[100];
            DatagramPacket packetReceived = new DatagramPacket(msgReceived, msgReceived.length);
            ds.receive(packetReceived);
            String StringReceived = new String(packetReceived.getData());
            System.out.println("Resposta do servidor: " + StringReceived);
            
            ds.close();
    
           
        } catch(SocketException | UnknownHostException e){
            System.out.println("Erro: " + e.getMessage());
        }       
    }
}
