import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class MulticastClient
{
    public static void main(String[] args) throws IOException
    {
        byte[] bufferIN = new byte[1024];

        int porta = 6789;
        String gruppo = "255.4.5.6";
        NetworkInterface networkInterface = NetworkInterface.getByName("eth0");

        MulticastSocket socket = new MulticastSocket(porta);
        socket.joinGroup(new InetSocketAddress(gruppo, porta), networkInterface);
    }    
}
