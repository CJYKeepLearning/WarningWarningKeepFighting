package network.entity;

public class UDPPacket {

    //源端口
    private String srcPort;
    //目的端口
    private String desPort;
    //16位UDP长度
    private int UDPLength;
    //16位UDP检验和
    private int UDPAck;
    //数据
    private String data;

    @Override
    public String toString() {
        return "UDPPacket{" +
                "srcPort='" + srcPort + '\'' +
                ", desPort='" + desPort + '\'' +
                ", UDPLength=" + UDPLength +
                ", UDPAck=" + UDPAck +
                ", data='" + data + '\'' +
                '}';
    }

    public String getSrcPort() {
        return srcPort;
    }

    public void setSrcPort(String srcPort) {
        this.srcPort = srcPort;
    }

    public String getDesPort() {
        return desPort;
    }

    public void setDesPort(String desPort) {
        this.desPort = desPort;
    }

    public int getUDPLength() {
        return UDPLength;
    }

    public void setUDPLength(int UDPLength) {
        this.UDPLength = UDPLength;
    }

    public int getUDPAck() {
        return UDPAck;
    }

    public void setUDPAck(int UDPAck) {
        this.UDPAck = UDPAck;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
