package segmentedfilesystem;

public abstract class Packet {
    byte statusByte;
    byte fileID;
    byte[] bytes;

    public Packet(byte[] bytes){
        this.bytes = bytes;
        this.statusByte = bytes[0];
        this.fileID = bytes[1];
    }


    public byte getFileID(){
        return fileID;

    }

    public byte getStatusByte(){
        return statusByte;
    }
}