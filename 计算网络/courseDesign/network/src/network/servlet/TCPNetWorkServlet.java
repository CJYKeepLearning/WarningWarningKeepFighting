package network.servlet;

import network.cache.NetWorkDataCache;
import network.entity.NetWorkData;
import network.test.capture.CaptureCore;
import network.websocket.NetWorkDataSocket;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/capture-tcp")
public class TCPNetWorkServlet extends HttpServlet {

    private static final String TAG = "InitListener";

    private static final AtomicInteger index = new AtomicInteger(0);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String netId = req.getParameter("netId");
        System.out.println(netId);

        // 上下文已准备好，开始抓包
        // 这里抓包
        new Thread(()->{

            CaptureCore.startCaptureAt(Integer.parseInt(netId));

        }).start();

        // 开始推送WebSocket消息
        new Thread(()->{
            while (true){
                if(!NetWorkDataSocket.webSocketSet.isEmpty()){
                    System.out.println(TAG + "推送");
                    System.out.println(TAG + NetWorkDataCache.getSize());
                    NetWorkDataSocket.sendTextMessage(NetWorkDataCache.listToString());
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        resp.sendRedirect("/network/show.jsp");
    }
}
