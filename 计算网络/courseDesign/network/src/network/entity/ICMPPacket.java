package network.entity;

public class ICMPPacket {

    //类型
    private String type;
    //重定向地址
    private String redirIP;
    //序列号
    private int seq;
    //子网掩码
    private String subnetMask;
    //校验和
    private int checkSum;
    //数据
    private String data;

    @Override
    public String toString() {
        return "ICMPPacket{" +
                "type='" + type + '\'' +
                ", redirIP='" + redirIP + '\'' +
                ", seq=" + seq +
                ", subnetMask='" + subnetMask + '\'' +
                ", checkSum=" + checkSum +
                ", data='" + data + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRedirIP() {
        return redirIP;
    }

    public void setRedirIP(String redirIP) {
        this.redirIP = redirIP;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getSubnetMask() {
        return subnetMask;
    }

    public void setSubnetMask(String subnetMask) {
        this.subnetMask = subnetMask;
    }

    public int getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(int checkSum) {
        this.checkSum = checkSum;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
