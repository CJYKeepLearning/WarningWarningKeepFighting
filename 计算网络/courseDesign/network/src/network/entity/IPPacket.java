package network.entity;

public class IPPacket {
    //版本
    private String version;
    //头部长度
    private long headLength;
    //服务类型
    private String serviceType;
    //总长度
    private long length;
    //标志
    private long ident;
    //是否能分片
    private boolean dFrag;
    //是否有分片
    private boolean mFrag;
    //偏偏移
    private long offset;
    //生存时间
    private long timeToLive;
    //协议
    private String protocol;
    //源IP
    private String srcIP;
    //目的IP
    private String secIP;
    //头部选项
    private String option;
    //数据部分
    private String data;

    @Override
    public String toString() {
        return "IPPacket{" +
                "version='" + version + '\'' +
                ", headLength=" + headLength +
                ", serviceType='" + serviceType + '\'' +
                ", length=" + length +
                ", ident=" + ident +
                ", dFrag=" + dFrag +
                ", mFrag=" + mFrag +
                ", offset=" + offset +
                ", timeToLive=" + timeToLive +
                ", protocol='" + protocol + '\'' +
                ", srcIP='" + srcIP + '\'' +
                ", secIP='" + secIP + '\'' +
                ", option='" + option + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public long getHeadLength() {
        return headLength;
    }

    public void setHeadLength(long headLength) {
        this.headLength = headLength;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getIdent() {
        return ident;
    }

    public void setIdent(long ident) {
        this.ident = ident;
    }

    public boolean isdFrag() {
        return dFrag;
    }

    public void setdFrag(boolean dFrag) {
        this.dFrag = dFrag;
    }

    public boolean ismFrag() {
        return mFrag;
    }

    public void setmFrag(boolean mFrag) {
        this.mFrag = mFrag;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public long getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(long timeToLive) {
        this.timeToLive = timeToLive;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getSrcIP() {
        return srcIP;
    }

    public void setSrcIP(String srcIP) {
        this.srcIP = srcIP;
    }

    public String getSecIP() {
        return secIP;
    }

    public void setSecIP(String secIP) {
        this.secIP = secIP;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }


}

