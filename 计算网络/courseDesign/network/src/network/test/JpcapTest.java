package network.test;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapBpfProgram;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Http;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JpcapTest {

    public static void main(String[] args) {

        List<PcapIf> alldevs = new ArrayList<PcapIf>(); // Will be filled with
        // NICs
        StringBuilder errbuf = new StringBuilder(); // For any error msgs

        Pcap pcap;


        final Ip4 ip = new Ip4();
        final Http http = new Http();
        /***************************************************************************
         * First get a list of devices on this system
         **************************************************************************/
        int r = Pcap.findAllDevs(alldevs, errbuf);
        if (r == Pcap.NOT_OK || alldevs.isEmpty()) {
            System.err.printf("Can’t read list of devices, error is %s", errbuf.toString());
            return;
        }

        System.out.println("Network devices found:");

        int i = 0;
        for (PcapIf device : alldevs) {
            String description = (device.getDescription() != null) ? device.getDescription()
                    : "No description available";
            System.out.printf("#%d: %s [%s]\n", i++, device.getName(), description);
        }

        PcapIf device = alldevs.get(0); // We know we have atleast 1 device
        System.out.printf("\nChoosing ‘%s’ on your behalf:\n",
                (device.getDescription() != null) ? device.getDescription() : device.getName());

        /***************************************************************************
         * Second we open up the selected device
         **************************************************************************/
        int snaplen = 64 * 1024; // Capture all packets, no trucation
        int flags = Pcap.MODE_PROMISCUOUS; // capture all packets
        int timeout = 10 * 1000; // 10 seconds in millis
        pcap = Pcap.openLive(device.getName(), snaplen, flags, timeout, errbuf);

        if (pcap == null) {
            System.err.printf("Error while opening device for capture:" + errbuf.toString());
            return;
        }

        /*
         * Compiling and appplying a filter to network interface
         */
        PcapBpfProgram filter = new PcapBpfProgram();
        String expression = "port 80";
        int optimize = 0;
        int netmask = 0;
        int m = pcap.compile(filter, expression, optimize, netmask);
        if (m != Pcap.OK) {
            System.out.println("Filter error: " + pcap.getErr());
        }
        pcap.setFilter(filter);

        /***************************************************************************
         * Third we create a packet handler which will receive packets from the
         * libpcap loop.
         **************************************************************************/

        PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() {

            public void nextPacket(PcapPacket packet, String user) {

                if (packet.hasHeader(ip)) {
                    System.out.println("ip vesiont"+ip.version());
                    System.out.println(packet);
                    System.out.println();
                }
                System.out.printf("Received packet at %s caplen=%-4d len=%-4d %s\n",
                        new Date(packet.getCaptureHeader().timestampInMillis()), packet.getCaptureHeader().caplen(), // Length
                        // actually // captured
                        packet.getCaptureHeader().wirelen(), // Original
                        // length
                        user // User supplied object
                );
                System.out.printf("数据包头%s\n", packet.getCaptureHeader().size());

            }
        };

        /***************************************************************************
         * Fourth we enter the loop and tell it to capture 10 packets. The loop
         * method does a mapping of pcap.datalink() DLT value to JProtocol ID,
         * which is needed by JScanner. The scanner scans the packet buffer and
         * decodes the headers. The mapping is done automatically, although a
         * variation on the loop method exists that allows the programmer to
         * sepecify exactly which protocol ID to use as the data link type for
         * this pcap interface.
         **************************************************************************/
        pcap.loop(10, jpacketHandler, "jNetPcap rocks!");

        /***************************************************************************
         * Last thing to do is close the pcap handle
         **************************************************************************/
        pcap.close();
    }
}