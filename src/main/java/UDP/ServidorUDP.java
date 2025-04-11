
package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Random;

public class ServidorUDP {
    
    public static void main(String[] args) throws SocketException{
        
        Random random = new Random();
        int cotacao = random.nextInt(7);
        
        try {
        
            DatagramSocket ds = new DatagramSocket(12345);
            System.out.println("Servidor em execução!");
            
            while(true){
                
                byte[] msgReceived = new byte[100];
                DatagramPacket packetReceived = new DatagramPacket(msgReceived, msgReceived.length);                
                ds.receive(packetReceived);
                String txtReceived = new String(packetReceived.getData(), 0, packetReceived.getLength());
                
                String[] txtSplit = txtReceived.split("@");
                System.out.println(Arrays.toString(txtSplit));
                
                int valor = Integer.parseInt(txtSplit[0]);
                int moeda = Integer.parseInt(txtSplit[1]);
                
                String content;
                
                switch(moeda) {
                    case 1:         
                        int res = valor*cotacao;
                        content = "Na cotação atual: " + cotacao + ", " + "o valor convertido e: " + res + " dolares";         
                    break;
                    default:
                        content = "Código de moeda inválido";
                }
                
                // Response ao cliente
                byte[] msg = content.getBytes();
                // Obtendo os dados do remetente (ip e porta) a partir
                // do packet recebido anteriormente (packetReceived)
                InetAddress ipCliente = packetReceived.getAddress();
                int portaCliente = packetReceived.getPort();  
                
                DatagramPacket packet = new DatagramPacket(msg, msg.length, ipCliente, portaCliente);
                ds.send(packet);
            }
            
            //ds.close();
            
            
        } catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }       
    }
}
