package segmentedfilesystem;

public abstract class Packet {
    byte statusByte;
    byte fileID;
    byte[] bytes;

    public Packet(byte[] bytes){
        this.bytes = bytes;
    }


    public byte getFileID(){
        return fileID;

    }

    public byte getStatusByte(){
        return statusByte;
    }
}