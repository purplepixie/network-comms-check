import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server
{
    public static void main(String[] args) throws SocketException
    {
        int port = 5000;
        if (args.length>0) port = Integer.parseInt(args[0]);
        System.out.println("Listening on port "+port);
        
        DatagramSocket socket = new DatagramSocket(port);
        DatagramPacket packet;
        while(true)
        {
            try
            {
                byte[] receive = new byte[65535];
                packet = new DatagramPacket(receive, receive.length);
                socket.receive(packet);
                System.out.println("Data received from network");

                StringBuilder ret = new StringBuilder();
                int i=0;
                while(receive[i]!=0)
                {
                    ret.append((char) receive[i]);
                    i++;
                }
                String data = ret.toString();
                String remoteip = packet.getAddress().toString().substring(1);
                System.out.println("Received: "+data+" from "+remoteip);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}