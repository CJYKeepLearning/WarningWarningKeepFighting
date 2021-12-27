package network.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

@ServerEndpoint("/network-data")
public class NetWorkDataSocket {
    private static final String TAG = "NetWorkDataSocket";

    private Session session;

    public static final CopyOnWriteArrayList<NetWorkDataSocket> webSocketSet = new CopyOnWriteArrayList();

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);
        System.out.println(TAG + "【WebSocket消息】有新的连接，总数：" + webSocketSet.size());
    }

    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        System.out.println(TAG + "【WebSocket消息】连接断开，总数：" + webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message){
        System.out.println(TAG + "【WebSocket消息】 收到客户端的消息：" + message);
    }

    public static void sendTextMessage(String message){
        for(NetWorkDataSocket webSocket: webSocketSet) {
            System.out.println(TAG + "【WebSocket消息】广播消息：message=" + message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
