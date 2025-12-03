import java.io.IOException;
import java.net.*;

public class Sender {

    public static void main(String[] args) {
        String MULTICAST_ADDRESS = "230.0.0.0";
        int PORT = 4446;

        if (args.length>0) PORT = Integer.parseInt(args[0]);
        if (args.length>1) MULTICAST_ADDRESS = args[1];

        try {
            InetAddress group = InetAddress.getByName(MULTICAST_ADDRESS);

            // Use a normal DatagramSocket (no need for MulticastSocket for sending)
            try (DatagramSocket socket = new DatagramSocket()) {

                String message = "hello";
                byte[] data = message.getBytes();

                DatagramPacket packet = new DatagramPacket(
                        data,
                        data.length,
                        group,
                        PORT
                );

                // Optional: Control time-to-live for multicast
                // (default is fine for LAN, but you can set it if needed)
                // socket.setOption(StandardSocketOptions.IP_MULTICAST_TTL, 4);

                System.out.println("Sending: \"" + message + "\" to " + MULTICAST_ADDRESS + ":" + PORT);

                socket.send(packet);

                System.out.println("Packet sent.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
