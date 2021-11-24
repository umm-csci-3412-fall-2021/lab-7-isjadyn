package segmentedfilesystem;

import java.util.Arrays;

public class DataPacket extends Packet{
    byte statusByte;
    byte fileID;
    int packetNumber;
    byte[] data;

    public DataPacket(byte[] bytes, int packetLength) {
        super(bytes);
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


    public byte getStatus(){
        return statusByte;
    }
}