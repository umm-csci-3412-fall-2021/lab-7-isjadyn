package segmentedfilesystem;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class FileRetriever {

	private String server;
        private int port;
        private DatagramSocket socket = null; 

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

        
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
                socket.send(packet);

                PacketManager manager = new PacketManager();
                while (!manager.allPacketsReceived()){
                        byte[] newBuffer = new byte[1048];
                        DatagramPacket newPacket = new DatagramPacket(newBuffer,newBuffer.length);
                        socket.receive(newPacket);

                        byte[] anotherByte = newPacket.getData();
                        int length = newPacket.getLength();

                        manager.receive(newPacket);
                }


        // Do all the heavy lifting here.
        // This should
        //   * Connect to the server
        //   * Download packets in some sort of loop
        //   * Handle the packets as they come in by, e.g.,
        //     handing them to some PacketManager class
        // Your loop will need to be able to ask someone
        // if you've received all the packets, and can thus
        // terminate. You might have a method like
        // PacketManager.allPacketsReceived() that you could
        // call for that, but there are a bunch of possible
        // ways.
	}


}
