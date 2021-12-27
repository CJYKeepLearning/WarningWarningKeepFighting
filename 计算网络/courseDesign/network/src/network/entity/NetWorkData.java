package network.entity;

/**
 * 网络数据包描述对象
 */
public class NetWorkData {
    private long id;
    private String srcIp;
    private String desIP;
    private String protocol;
    private long length;
    private long headLength;

    public void setHeadLength(long headLength) {
        this.headLength = headLength;
    }

    public void setSrcIp(String srcIp) {
        this.srcIp = srcIp;
    }

    public void setDesIP(String desIP) {
        this.desIP = desIP;
    }


    private String headerContent;

    public String getSrcIp() {
        return srcIp;
    }

    public String getDesIP() {
        return desIP;
    }


    // ....

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getHeaderContent() {
        return headerContent;
    }

    public void setHeaderContent(String headerContent) {
        this.headerContent = headerContent;
    }

    @Override
    public String toString() {
        return "NetWorkData{" +
                "id=" + id +
                ", srcIp='" + srcIp + '\'' +
                ", desIP='" + desIP + '\'' +
                ", protocol='" + protocol + '\'' +
                ", length=" + length +
                ", headerContent='" + "\n" + headerContent + '\'' +
                '}';
    }

    private int version;
    public void setVersion(int version) {
        this.version = version;
    }

    private int flags;
    public void setSinal(int flags) {
        this.flags = flags;
    }

    private int flags_df;
    public void setDF(int flags_df) {
        this.flags_df = flags_df;
    }

    private int flags_mf;
    public void setMF(int flags_mf) {
        this.flags_mf = flags_mf;
    }

    private int offSet;
    public void setOff(int offset) {
        this.offSet = offset;
    }
}
