package network.test.message;

public class NMMessage {
    private String dstIP;
    private String srcIP;
    private String protocol;
    private Long length;
    private String srcPort;
    private String dstPort;
    private String content;

    public void setSip(String srcIP) {
        this.srcIP = srcIP;
    }

    public void setDip(String dstIP) {
        this.dstIP = dstIP;
    }

    public void setPacket(String content) {
        this.content = content;
    }

    public void setSport(String srcPort) {
        this.srcPort = srcPort;
    }

    public void setDport(String dstPort) {
        this.dstPort = dstPort;
    }

    @Override
    public String toString() {
        return "NMMessage{" +
                "dstIP='" + dstIP + '\'' +
                ", srcIP='" + srcIP + '\'' +
                ", srcPort='" + srcPort + '\'' +
                ", dstPort='" + dstPort + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
