package segmentedfilesystem;

import java.util.Arrays;

public class DataPacket extends Packet{
    byte statusByte;
    byte fileID;
    int packetNumber;
    byte[] data;

    public DataPacket(byte[] bytes) {
        super(bytes);
        statusByte = bytes[0];
        fileID = bytes[1];
        int x = Byte.toUnsignedInt(bytes[2]);
        int y = Byte.toUnsignedInt(bytes[3]);
        packetNumber = 256 * x + y;
        data = Arrays.copyOfRange(bytes, 4, bytes.length);
    
    }

    public byte[] getData(){
        return data;
    }

    public boolean isLastPacket(){
        return this.statusByte % 4 == 3;

    }

    public int getPacketNumber(){
        return packetNumber;
    }
}