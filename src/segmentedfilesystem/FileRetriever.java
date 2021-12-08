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
                
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
                socket.send(packet);
                

                while (!manager.allPacketsReceived()){
                        System.out.println("About to process a packet in FileRetriever");
                        byte[] newBuffer = new byte[1048];
                        DatagramPacket newPacket = new DatagramPacket(newBuffer,newBuffer.length);
                        System.out.println("About to request another packet");
                        socket.receive(newPacket);
                        System.out.println("\tGot another packet");
                        manager.receive(newPacket);
                        System.out.println("\tProcessed a packet in FileRetriever");
                }
                manager.writeFiles();
                socket.close();

	}

}
