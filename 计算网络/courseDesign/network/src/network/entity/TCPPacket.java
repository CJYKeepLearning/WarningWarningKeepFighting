    package network.entity;

    public class TCPPacket {

        //源端口
        private String srcPort;
        //目的端口
        private String desPort;
        //确认序号
        private long ackNum;
        //URG
        private int urg;
        //ACK
        private int ack;
        //PSH
        private int psh;
        //RST
        private int rst;
        //SYN
        private int SYN;
        //窗口大小
        private int window;
        //紧急指针
        private int urgentPointer;
        //TCP选项
        private int option;
        //数据
        private String data;
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

        public long getAckNum() {
            return ackNum;
        }

        public void setAckNum(long ackNum) {
            this.ackNum = ackNum;
        }

        public int getUrg() {
            return urg;
        }

        public void setUrg(int urg) {
            this.urg = urg;
        }

        public int getAck() {
            return ack;
        }

        public void setAck(int ack) {
            this.ack = ack;
        }

        public int getPsh() {
            return psh;
        }

        public void setPsh(int psh) {
            this.psh = psh;
        }

        public int getRst() {
            return rst;
        }

        public void setRst(int rst) {
            this.rst = rst;
        }

        public int getSYN() {
            return SYN;
        }

        public void setSYN(int SYN) {
            this.SYN = SYN;
        }

        public int getWindow() {
            return window;
        }

        public void setWindow(int window) {
            this.window = window;
        }

        public int getUrgentPointer() {
            return urgentPointer;
        }

        public void setUrgentPointer(int urgentPointer) {
            this.urgentPointer = urgentPointer;
        }

        public int getOption() {
            return option;
        }

        public void setOption(int option) {
            this.option = option;
        }

        @Override
        public String toString() {
            return "TCPPacket{" +
                    "srcPort='" + srcPort + '\'' +
                    ", desPort='" + desPort + '\'' +
                    ", ackNum=" + ackNum +
                    ", urg=" + urg +
                    ", ack=" + ack +
                    ", psh=" + psh +
                    ", rst=" + rst +
                    ", SYN=" + SYN +
                    ", window=" + window +
                    ", urgentPointer=" + urgentPointer +
                    ", option=" + option +
                    ", data='" + data + '\'' +
                    '}';
        }
    }
