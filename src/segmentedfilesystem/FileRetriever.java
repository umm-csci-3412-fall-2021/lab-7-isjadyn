package segmentedfilesystem;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class FileRetriever {

	private String server;
        private int port;

        public FileRetriever(String server, int port) throws SocketException {
        // Save the server and port for use in `downloadFiles()`
        //...
        this.port = port;
        this.server = server;

	}

	public void downloadFiles() throws IOException {
                byte[] buffer = new byte[1048];
                DatagramSocket socket = new DatagramSocket();
                PacketManager manager = new PacketManager();
                InetAddress address = InetAddress.getByName(server);
                
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
                socket.close();

	}

}
