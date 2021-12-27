package network.test.capture;

import network.cache.NetWorkDataCache;
import network.entity.NetWorkData;
import network.test.message.MessageCenter;
import network.test.message.NMMessage;
import network.test.vo.Rule;
import org.jnetpcap.nio.JBuffer;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.network.Arp;
import org.jnetpcap.protocol.network.Icmp;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Http;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PacketMatch {
    private AtomicInteger index = new AtomicInteger(0);
    private static PacketMatch pm;
    //这里捕获六个协议
    private Ip4 ip = new Ip4();
    private Icmp icmp = new Icmp();
    private Tcp tcp = new Tcp();
    private Udp udp = new Udp();

    private List<Rule> icmpRules;
    private List<Rule> tcpRules;
    private List<Rule> udpRules;
    private List<Rule> httpRules;
    private List<Rule> arpRules;
    private List<Rule> ipRules;

    private NMMessage message;

    private PcapPacket pcapPacket;
    private StringBuffer sb;

    private String strPacket;

    private boolean flag1 = true;
    private boolean flag2 = true;
    private boolean flag3 = true;
    private boolean flag4 = true;

    public static PacketMatch getInstance() {
        if (pm == null) {
            pm = new PacketMatch();
        }
        return pm;
    }

    public void loadRules() {//加载规则
        icmpRules = new ArrayList<Rule>();
        tcpRules = new ArrayList<Rule>();
        udpRules = new ArrayList<Rule>();
        httpRules = new ArrayList<Rule>();
        arpRules = new ArrayList<Rule>();
        ipRules = new ArrayList<Rule>();
    }

    public void handlePacket(PcapPacket packet) {//根据包头选择不同的规则
        message = new NMMessage();
        if (packet.hasHeader(ip)) {
            handleIp(packet);
        }
        if (packet.hasHeader(icmp)) {
            handleIcmp(packet);
        }
        if (packet.hasHeader(tcp) ) {
            handleTcp(packet);
        }
        if (packet.hasHeader(udp) ) {
            handleUdp(packet);
        }
    }


    /**
     *   这里是IP协议的处理
     * @param packet
     */
    private void handleIp(PcapPacket packet) {
        sb = new StringBuffer();
        byte[] sIP = new byte[4], dIP = new byte[4];
        sIP = ip.source();
        dIP = ip.destination();
        String srcIP = org.jnetpcap.packet.format.FormatUtils.ip(sIP);
        String dstIP = org.jnetpcap.packet.format.FormatUtils.ip(dIP);

        byte[] buff = new byte[packet.getTotalSize()];
        packet.transferStateAndDataTo(buff);
        JBuffer jb = new JBuffer(buff);
        String content = jb.toHexdump();
        sb.append("序号：" + index.get());
        sb.append("------IP数据包分析-----\n");
        sb.append( "版本：" + packet.getHeader(ip).version() + "\n");
        sb.append( "首部长度：" + packet.getHeader(ip).length() + "\n");
        sb.append( "服务类型tos：" + packet.getHeader(ip).tos() + "\n");
        sb.append( "总长度：" + packet.size() + "\n");
        sb.append( "标识：" + packet.getHeader(ip).flags() + "\n");
        sb.append( "Don't Frag?" + packet.getHeader(ip).flags_MFDescription() + "\n");
        sb.append( "More Frag?" + packet.getHeader(ip).flags_DFDescription() + "\n");
        sb.append( "片偏移：" + packet.getHeader(ip).offset() + "\n");
        sb.append( "生存时间：" + packet.getHeader(ip).ttl() + "\n");
        sb.append( "首部检验和：" + packet.getHeader(ip).checksum() + "\n");
        sb.append( "源地址：" + srcIP + "\n");
        sb.append( "目的地址：" + dstIP + "\n");
        sb.append( "数据：" + "\n" + content + "\n");

        if (flag1) {
            System.out.println("捕获到IP数据包" + "\n");
            NetWorkDataCache.put(index.getAndIncrement(), new String(sb));
            sendMessage();
        }
    }

    /**
     * 这里处理ICMP协议
     * @param packet
     */
    private void handleIcmp(PcapPacket packet) {
        sb = new StringBuffer();
        //NetWorkData workData = new NetWorkData();
        //pcapPacket = packet;
        packet.getHeader(icmp);
        byte[] buff = new byte[packet.getTotalSize()];
        packet.transferStateAndDataTo(buff);
        JBuffer jb = new JBuffer(buff);
        String content = jb.toHexdump();

        sb.append("序号：" + index.get());
        sb.append("------ICMP数据包分析-----\n");
        sb.append("类型：" + packet.getHeader(icmp).type() + "\n");
        sb.append("代码：" + packet.getHeader(icmp).code() + "\n");
        sb.append("校验和：" + packet.getHeader(icmp).checksum() + "\n");
        sb.append("检查是否有效：" + packet.getHeader(icmp).isChecksumValid() + "\n");
        sb.append("数据：" + "\n" + content + "\n");

        //判断是否捕获此数据包
        if (flag2) {
            System.out.println("捕获到ICMP数据包");
            NetWorkDataCache.put(index.getAndIncrement(), sb.toString());
            sendMessage();
        }
    }

    /**
     * 这里处理TCP协议
     * @param packet
     */
    private void handleTcp(PcapPacket packet) {
        sb = new StringBuffer();
        //pcapPacket = packet;
        packet.getHeader(tcp);
        String srcPort = String.valueOf(tcp.source());
        String dstPort = String.valueOf(tcp.destination());

        byte[] buff = new byte[packet.getTotalSize()];
        packet.transferStateAndDataTo(buff);
        JBuffer jb = new JBuffer(buff);
        String content = jb.toHexdump();

        sb.append("序号：" + index.get());
        sb.append("------TCP数据包分析-----\n");
        sb.append("源端口：" + srcPort + "\n");
        sb.append("目的端口：" + dstPort + "\n");
        sb.append("序列号：" + packet.getHeader(tcp).seq() + "\n");
        sb.append("确认号：" + packet.getHeader(tcp).ack() + "\n");
        sb.append("头部长度：" + packet.getHeader(tcp).hlen() + "\n");
        sb.append("URG：" + packet.getHeader(tcp).flags_URG() + "\n");
        sb.append("ACK：" + packet.getHeader(tcp).flags_ACK() + "\n");
        sb.append("PSH：" + packet.getHeader(tcp).flags_PSH() + "\n");
        sb.append("RST：" + packet.getHeader(tcp).flags_RST() + "\n");
        sb.append("SYN：" + packet.getHeader(tcp).flags_SYN() + "\n");
        sb.append("FIN：" + packet.getHeader(tcp).flags_FIN() + "\n");
        sb.append("窗口大小：" + packet.getHeader(tcp).window() + "\n");
        sb.append("校验和：" + packet.getHeader(tcp).checksum() + "\n");
        sb.append("紧急指针：" + packet.getHeader(tcp).urgent() + "\n");
        sb.append("数据：" + "\n"  + content + "\n");

        //判断是否捕获此包
        if (flag3) {
            System.out.println("捕获到TCP数据包");
            NetWorkDataCache.put(index.getAndIncrement(), new String(sb));
            sendMessage();
        }
    }

    /**
     * 这里处理UDP协议
     * @param packet
     */
    private void handleUdp(PcapPacket packet) {
        sb = new StringBuffer();
        //pcapPacket = packet;
        packet.getHeader(udp);
        String srcPort = String.valueOf(udp.source());
        String dstPort = String.valueOf(udp.destination());
        byte[] buff = new byte[packet.getTotalSize()];
        packet.transferStateAndDataTo(buff);
        JBuffer jb = new JBuffer(buff);
        String content = jb.toHexdump();

        sb.append("序号：" + index.get());
        sb.append("------UDP数据包分析-----\n");
        sb.append("源端口：" + srcPort + "\n");
        sb.append("目的端口：" + dstPort + "\n");
        sb.append("长度：" + packet.getHeader(udp).length() + "\n");
        sb.append("校验和：" + packet.getHeader(udp).checksum() + "\n");
        sb.append("数据：" + "\n"  + content + "\n");

        //判断是否捕获此数据包
        if (flag4) {
            System.out.println("捕获到UDP数据包");
            NetWorkDataCache.put(index.getAndIncrement(), sb.toString());
            sendMessage();
        }

    }

    /**
     * 发送报文信息
     */
    public String sendMessage() {
        if (flag1 || flag2 || flag3 || flag4) {
            MessageCenter.sendMessage2(sb.toString());
        }
        return sb.toString();
    }

    /**
     * 开始捕获
     */
    public void startCatch() {
        flag1 = true;
        flag2 = true;
        flag3 = true;
        flag4 = true;
        System.out.println("开始捕获数据包");
    }

    /**
     * 暂停捕获
     */
    public void pauseCatch() {
        flag1 = false;
        flag2 = false;
        flag3 = false;
        flag4 = false;
        System.out.println("暂停捕获数据包");
    }

    /**
     * 捕获IP数据包
     */
    public void catchIp() {
        flag1 = true;
        flag2 = false;
        flag3 = false;
        flag4 = false;
        System.out.println("捕获IP数据包");
    }

    /**
     * 捕获ICMP数据包
     */
    public void catchICMP() {
        flag1 = false;
        flag2 = true;
        flag3 = false;
        flag4 = false;
        System.out.println("捕获ICMP数据包");
    }
    /**
     * 捕获TCP数据包
     */
    public void catchTCP() {
        flag1 = false;
        flag2 = false;
        flag3 = true;
        flag4 = false;
        System.out.println("捕获TCP数据包");
    }
    /**
     * 捕获UDP数据包
     */
    public void catchUDP() {
        flag1 = false;
        flag2 = false;
        flag3 = false;
        flag4 = true;
        System.out.println("捕获UDP数据包");
    }
}