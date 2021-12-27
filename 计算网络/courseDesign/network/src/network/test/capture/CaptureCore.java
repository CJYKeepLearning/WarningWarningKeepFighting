package network.test.capture;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CaptureCore {

    public static List<PcapIf> getDevs() {//获取机器上的网卡列表
        List<PcapIf> devs = new ArrayList<PcapIf>();
        StringBuilder errsb = new StringBuilder();
        int r = Pcap.findAllDevs(devs, errsb);
        if (r == Pcap.NOT_OK || devs.isEmpty()) {
            JOptionPane.showMessageDialog(null, errsb.toString(), "错误", JOptionPane.ERROR_MESSAGE);
            return null;
        } else {
            return devs;
        }
    }

    /**
     * 选择一个网卡开始捕获数据包
     * @param num
     */
    public static void startCaptureAt(int num) {//选择一个网卡开启抓包
        List<PcapIf> devs = new ArrayList<PcapIf>();
        StringBuilder errsb = new StringBuilder();
        int r = Pcap.findAllDevs(devs, errsb);
        if (r == Pcap.NOT_OK || devs.isEmpty()) {
            JOptionPane.showMessageDialog(null, errsb.toString(), "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //选择网卡
        PcapIf device = devs.get(num);
        int snaplen = Pcap.DEFAULT_SNAPLEN;//长度65536
        int flags = Pcap.MODE_PROMISCUOUS;//混杂模式
        int timeout = 10 * 1000;
        //StringBuilder errsb = null;
        Pcap pcap = Pcap.openLive(device.getName(), snaplen, flags, timeout, errsb);
        if (pcap == null) {
            JOptionPane.showMessageDialog(null, errsb.toString(), "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //这里获取一个PacketMatch对象
        //PacketMatch packetMatch = PacketMatch.getInstance();
//        //加载规则
//        packetMatch.loadRules();

        //这里将捕获到的数据包处理，判断是什么类型
        MyPcapPacketHandler<Object> myhandler = new MyPcapPacketHandler<Object>();
        pcap.loop(Integer.MAX_VALUE, myhandler, "jnetpcap");
        pcap.close();
    }

    public static void main(String[] args) {
        List<PcapIf> devices = getDevs();
        System.out.println("共搜索到" + devices.size() + "个网卡");
        for (PcapIf pcapIf : devices) {
            System.out.println(pcapIf);
        }
        startCaptureAt(3);
    }
}