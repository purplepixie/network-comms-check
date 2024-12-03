import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client
{
    public static void main(String[] args)
    {
        int remoteport = 5000;
        String remoteip = "127.0.0.1";
        if (args.length!=2) 
        {
            System.out.println("Usage: client remoteip remoteport");
            return;
        }    
        remoteport = Integer.parseInt(args[1]);
        remoteip = args[0];

        try 
        {
            String data = "Hello";
            byte[] bytes = data.getBytes();
            InetAddress inet = InetAddress.getByName(remoteip);
            DatagramSocket ds = new DatagramSocket();
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, inet, remoteport);
            System.out.print("Sending Hello to "+remoteip+":"+remoteport);
            ds.send(packet);
            System.out.println(" ... DONE");
        } 
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    }
}