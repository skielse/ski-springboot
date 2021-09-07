package com.ski.bootstart.util;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

/**
 * 自定义WebSocketClient
 */
public class MyWebSocketClient extends WebSocketClient {
    public int bytes;
    public ArrayList param;
    public String voiceUrl;
    private Map<String, Object> result;
    private MessageHandler messageHandler;


    public MyWebSocketClient(String url, ArrayList<Object> param, String voiceUrl) throws URISyntaxException {
        super(new URI(url));
        this.param = param;
        this.voiceUrl = voiceUrl;
    }

    public interface MessageHandler {
        /**
         * @param message 返回消息
         */
        public void handleMessage(String message);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        this.send(param.get(0).toString());
        this.send(param.get(1).toString());
        try {
            FileInputStream fs = new FileInputStream(voiceUrl);
            byte[] buffer = new byte[1024];
            while ((bytes = fs.read(buffer)) > 0) {
                this.send(buffer);
            }
            fs.close();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.send(param.get(2).toString());
    }

    @Override
    public void onMessage(String s) {
        System.out.println(s);
        messageHandler.handleMessage(s);
        this.close();
    }

    @Override
    public void onClose(int i, String s, boolean b) {

    }

    @Override
    public void onError(Exception e) {

    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    /**
     * register message handler
     *
     * @param msgHandler
     */
    public void addMessageHandler(MessageHandler msgHandler) {
        this.messageHandler = msgHandler;
    }
}
