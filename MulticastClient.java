import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class MulticastClient {
    public static void main(String[] args) throws IOException {
        byte[] bufferIN = new byte[1024];

        int porta = 6789;
        String gruppo = "230.0.0.1";
        InetAddress groupAddress = InetAddress.getByName(gruppo);

        // Ottenere automaticamente l'interfaccia di rete corretta
        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());

        MulticastSocket socket = new MulticastSocket(porta);
        socket.joinGroup(new InetSocketAddress(groupAddress, porta), networkInterface);

        System.out.println("Client in ascolto...");
        while (true) {
            DatagramPacket packetIN = new DatagramPacket(bufferIN, bufferIN.length);
            socket.receive(packetIN);

            System.out.println("Ricevuto pacchetto di lunghezza: " + packetIN.getLength() +
                    " da: " + packetIN.getAddress().toString() +
                    " porta: " + packetIN.getPort());
            System.out.write(packetIN.getData(), 0, packetIN.getLength());
            System.out.println();
        }
    }
}
