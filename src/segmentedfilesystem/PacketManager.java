package segmentedfilesystem;

import java.net.DatagramPacket;
import java.util.Arrays;

public class PacketManager {
    public int finalID = 0;

    public boolean allPacketsReceived() {
        return true;
    }

    public void receive(DatagramPacket packet){
        byte[] abyte = packet.getData();
        byte status = abyte[0];
        byte fileID = abyte[1];
        byte[] fileName = Arrays.copyOfRange(abyte,2, abyte.length -1);

        //header packet
        if(status % 2 == 0){

        } 
        //data packet
        else {
            if(status % 4 ==3){
                finalID = fileID;
            }

        }
    }

}
