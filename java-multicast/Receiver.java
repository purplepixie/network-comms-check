import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

public class Receiver {

    public static void main(String[] args) {
        String MULTICAST_ADDRESS = "230.0.0.0";
        int PORT = 4446;

        if (args.length>0) PORT = Integer.parseInt(args[0]);
        if (args.length>1) MULTICAST_ADDRESS = args[1];
        
        
        System.out.println("Listening on "+MULTICAST_ADDRESS+":"+PORT);

        try {
            InetAddress group = InetAddress.getByName(MULTICAST_ADDRESS);
            SocketAddress groupAddress = new InetSocketAddress(group, PORT);

            // Create and bind the socket
            MulticastSocket socket = new MulticastSocket(PORT);

            // Join multicast group on ALL multicast-capable interfaces
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();

                try {
                    if (ni.isUp() && ni.supportsMulticast()) {
                        System.out.println("Joining on interface: " + ni.getName());
                        socket.joinGroup(groupAddress, ni);
                    }
                } catch (Exception ignored) {
                    // Some interfaces cannot be joined (e.g. virtual, loopback)
                }
            }

            System.out.println("Listening for multicast packets on all interfaces...");
            byte[] buf = new byte[2048];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                String data = new String(packet.getData(), 0, packet.getLength());
                System.out.printf("Received from %s: %s%n",
                        packet.getAddress().getHostAddress(), data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
