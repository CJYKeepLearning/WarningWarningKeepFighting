package network.test.capture;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;

public class MyPcapPacketHandler<Object> implements PcapPacketHandler<Object>  {//抓到包后送去检测


    @Override
    public void nextPacket(PcapPacket packet, Object obj) {
        PacketMatch packetMatch = PacketMatch.getInstance();
        //处理数据判断是什么类型
        packetMatch.handlePacket(packet);
    }
}