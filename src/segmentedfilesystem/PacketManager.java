package segmentedfilesystem;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.HashMap;

public class PacketManager {
    HashMap<String, ReceivedFile> files;

    public PacketManager(){
        files = new HashMap<>();
    }

    public boolean allPacketsReceived() {
        ArrayList<Boolean> allReceived = new ArrayList<>();

        for(ReceivedFile file : files.values()){
            if (file.isAllReceived()){
                allReceived.add(true);
            }
            else{
                allReceived.add(false);
            }
        }

        //checks is the hashmap is empty or contains any incomplete files
        if(allReceived.contains(false) || allReceived.isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }

    public void receive(DatagramPacket packet){
        byte[] abyte = packet.getData();
        byte status = abyte[0];
        
        //header packet
        if(status % 2 == 0){
           HeaderPacket header = new HeaderPacket(packet.getData());
           String fileID = header.getFileID() + "";

           if(!files.containsKey(fileID)){
                ReceivedFile newFile = new ReceivedFile();
                newFile.addHP(header);
                files.put(fileID,newFile);
           }
           else{
               files.get(fileID).addHP(header);
           }
        } 
        //data packet
        else {
           DataPacket data = new DataPacket(packet.getData(), packet.getLength());
           String fileID = data.getFileID() + "";

           if(!files.containsKey(fileID)){
            ReceivedFile newFile = new ReceivedFile();
            newFile.addDP(data);
            files.put(fileID,newFile);
            }
            else{
                files.get(fileID).addDP(data);
            }

        }

    }

    public void writeFiles(){
        for(ReceivedFile file : files.values()){
            file.writeToFile();
        }
    }
}
