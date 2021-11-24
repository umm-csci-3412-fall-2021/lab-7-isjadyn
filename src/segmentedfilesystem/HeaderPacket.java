package segmentedfilesystem;

import java.util.Arrays;

public class HeaderPacket extends Packet{
    byte statusByte;
    byte fileID;
    String fileName;

    public HeaderPacket(byte[] bytes) {
        super(bytes);
        this.fileName = new String (Arrays.copyOfRange(bytes, 2, bytes.length -1));
        
    }

    public String getFileName(){
        return fileName;
    }

    public byte getStatus(){
        return statusByte;
    }

}