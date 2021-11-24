package segmentedfilesystem;

import java.io.FileOutputStream;
import java.util.SortedMap;
import java.util.TreeMap;

public class ReceivedFile {
    private SortedMap<Integer, byte[]> packets;
    private String fileName;
    private boolean allReceived;
    private int packetNumber;
    private int counter;

    public ReceivedFile(){
        packets = new TreeMap<>();
        allReceived = false;
    }

    public boolean isAllReceived(){
        return allReceived;
    }

    public void addDP(DataPacket packet){
        packets.put(packet.getPacketNumber(),packet.getData());
        counter++;
        if(packet.isLastPacket()){
            packetNumber = packet.getPacketNumber();
        }
        checkAllReceived();
    }

    public void addHP(HeaderPacket packet){
        this.fileName= packet.getFileName();
        checkAllReceived();
    }

    public void checkAllReceived(){
        if(counter -1 == packetNumber && fileName != null){
            allReceived = true;
        }
    }

    public byte[] getBytes(){
        byte[] fileBytes = new byte[getFileSize()];
        int i = 0;
        for(byte[] bytes: packets.values()){
            for(byte abyte : bytes ){
                fileBytes[i] = abyte;
                i++;
            }
        }
        return fileBytes;
    }

    public int getFileSize(){
        int size =0;
        for(byte[] abyte: packets.values()){
            size += abyte.length;
        }
        return size;
    }

    //Credit to Jeff and Biruk- Thanks!
    public void writeToFile(){
        try{
            FileOutputStream output = new FileOutputStream(System.getProperty("user.dir") + "/" + fileName.trim());
            output.write(getBytes());
       }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}