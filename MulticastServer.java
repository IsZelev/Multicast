import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Date;

public class MulticastServer {
    public static void main(String[] args) throws IOException {
        boolean attivo = true;
        byte[] bufferOUT;
        int conta = 20;
        int porta = 6789;
        InetAddress gruppo = InetAddress.getByName("230.0.0.1");

        MulticastSocket socket = new MulticastSocket();

        while (attivo) {
            String dString = new Date().toString();
            bufferOUT = dString.getBytes();

            DatagramPacket packet = new DatagramPacket(bufferOUT, bufferOUT.length, gruppo, porta);
            socket.send(packet);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }

            conta--;
            if (conta == 0) {
                System.out.println("Server in chiusura");
                attivo = false;
            } else
            {
                System.out.println("Server attivo per altri secondi " + conta);
            }
        }

        socket.close();
    }
}
