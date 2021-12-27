package network.listener;


import network.cache.NetWorkDataCache;
import network.entity.NetWorkData;
import network.websocket.NetWorkDataSocket;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 上下文监听器，应用启动完毕就开始抓包
 */
public class InitListener implements ServletContextListener {
//    private static final String TAG = "InitListener";
//
//    private static final AtomicInteger index = new AtomicInteger(0);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // 上下文已准备好，开始抓包
        // 这里模拟抓包
//        new Thread(()->{
//            while (true){
//                NetWorkData netData = new NetWorkData();
//                netData.setId(index.get());
//                netData.setProtocol("TCP");
//                netData.setLength((int)(Math.random() * 100000) % 65535);
//                NetWorkDataCache.put(index.incrementAndGet(), netData);
//                System.out.println(TAG + "扑捉到数据包:" + index.get());
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//        // 开始推送WebSocket消息
//        new Thread(()->{
//            int oldIndex = -1;
//            while (true){
//                if(!NetWorkDataSocket.webSocketSet.isEmpty() && index.get() != oldIndex){
//                    System.out.println(TAG + "推送");
//                    NetWorkDataSocket.sendTextMessage(NetWorkDataCache.listToString());
//                    oldIndex = index.get();
//                }
//            }
//        }).start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
