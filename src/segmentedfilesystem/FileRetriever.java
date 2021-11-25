package segmentedfilesystem;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class FileRetriever {

	private InetAddress server;
        private int port;
        
        public FileRetriever(String server, int port) throws SocketException {
        // Save the server and port for use in `downloadFiles()`
        //...
        this.server = server;
        this.port = port;
        DatagramSocket socket = new DatagramSocket();
        this.socket = socket;

	}

	public void downloadFiles() throws IOException {
                byte[] buffer = new byte[1048];
                InetAddress address = InetAddress.getByName(server);
                PacketManager manager = new PacketManager();
                try{
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
                        socket.send(packet);
                } catch(IOException e) {e.printStackTrace();}
                

                while (!manager.allPacketsReceived()){
                        byte[] newBuffer = new byte[1048];
                        DatagramPacket newPacket = new DatagramPacket(newBuffer,newBuffer.length);
                        socket.receive(newPacket);
                        manager.receive(newPacket);

                }
                manager.writeFiles();

	}

}
