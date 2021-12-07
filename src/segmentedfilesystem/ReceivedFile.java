package segmentedfilesystem;

import java.io.FileOutputStream;
import java.util.SortedMap;
import java.util.TreeMap;

public class ReceivedFile {
    SortedMap<Integer, byte[]> packets;
    String fileName;
    byte ID;
    boolean done;
    boolean finalPacketHere;
    int finalPacketNumber;
    int counter;

    public ReceivedFile(){
        this.packets = new TreeMap<>();
        this.done = false;
        this.ID = ID;
        this.finalPacketNumber = -1;
    }

    public boolean isAllReceived(){
        return done;
    }

    public void addDP(DataPacket packet){
        System.out.println("processing packet # " + packet.getPacketNumber() + " for file ID " + ID + " with name " + fileName);
        packets.put(packet.getPacketNumber(),packet.getData());

        boolean done = true;

        if (packet.isLastPacket()){
            finalPacketHere = true;
            finalPacketNumber = packet.getPacketNumber();
        }
        if (!finalPacketHere) 
            {done = false;}

        for(int i=0; i< finalPacketNumber; i++){
            if (!packets.containsKey(i)) {
                done = false;
                break;
            }
        }
        this.done= done;
        

        System.out.println("\tDone for file " + ID + " is " + done);
    }

    public void addHP(HeaderPacket packet){
        ID= packet.getFileID();
        fileName = packet.getFileName();
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

    public void writeToFile() {
        try {
			FileOutputStream stream = new FileOutputStream(System.getProperty("user.dir") + "/" + fileName);
			stream.write(getBytes());
            stream.close();
		} catch(Exception e) {
			e.printStackTrace();
        }
        
    }


}