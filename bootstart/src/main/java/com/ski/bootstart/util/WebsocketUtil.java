package com.ski.bootstart.util;

import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author wangzijie
 * @date 2021/9/7
 */
public class WebsocketUtil {
    public static final String baseUrl = "wss://gray.stkouyu.com:8442/";
    public static final String appkey = "1624245483000174";
    public static final String secretKey = "c07e70758ad0b22db4aba00f11ca076a";

    public static void STWebsocketAPI(final String voiceUrl, String refText, String type,MyWebSocketClient.MessageHandler messageHandler) {
        String url = baseUrl + type + "?e=0&t=0";
        String userId = getRandomString(5);
        final ArrayList param = buildParam(appkey, secretKey, userId, refText, type);
        try {
            URI uri = new URI(url);
            /*调用客户端*/
            MyWebSocketClient client = new MyWebSocketClient(baseUrl + type + "?e=0&t=0", param, voiceUrl);
            client.addMessageHandler(messageHandler);
            client.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ArrayList buildParam(String appkey, String secretKey, String userId, String refText, String coreType) {
        MessageDigest digest = DigestUtils.getSha1Digest();
        long timeReqMillis = System.currentTimeMillis();
        String connectSigStr = appkey + timeReqMillis + secretKey;
        String connectSig = Hex.encodeHexString(digest.digest(connectSigStr.getBytes()));
        long timeStartMillis = System.currentTimeMillis();
        String startSigStr = appkey + timeStartMillis + userId + secretKey;
        String startSig = Hex.encodeHexString(digest.digest(startSigStr.getBytes()));
        String connect = "{"
                + "\"cmd\":\"connect\","
                + "\"param\":{"
                + "\"sdk\":{"
                + "\"protocol\":2,"
                + "\"version\":16777472,"
                + "\"source\":9"
                + "},"
                + "\"app\":{"
                + "\"applicationId\":\"" + appkey + "\","
                + "\"sig\":\"" + connectSig + "\","
                + "\"timestamp\":\"" + timeReqMillis + "\""
                + "}"
                + "}"
                + "}";
        String start = "{"
                + "\"cmd\":\"start\","
                + "\"param\":{"
                + "\"app\":{"
                + "\"applicationId\":\"" + appkey + "\","
                + "\"timestamp\":\"" + timeStartMillis + "\","
                + "\"sig\":\"" + startSig + "\","
                + "\"userId\":\"" + userId + "\""
                + "},"
                + "\"audio\":{"
                + "\"sampleBytes\":2,"
                + "\"channel\":1,"
                + "\"sampleRate\":16000,"
                + "\"audioType\":\"mp3\""
                + "},"
                + "\"request\":{"
                + "\"tokenId\":\"sfasdfadsfasewqaf\","
                + "\"refText\":\"" + refText + "\","
                + "\"coreType\":\"" + coreType + "\""
                + "}"
                + "}"
                + "}";
        String stop = "{"
                + "\"cmd\":\"stop\""
                + "}";
        ArrayList params = new ArrayList();
        params.add(connect);
        params.add(start);
        params.add(stop);
        return params;
    }

    private static int getRandom(int count) {
        return (int) Math.round(Math.random() * (count));
    }

    private static String charString = "abcdefghijklmnopqrstuvwxyz123456789";

    private static String getRandomString(int length) {
        StringBuffer sb = new StringBuffer();
        int len = charString.length();
        for (int i = 0; i < length; i++) {
            sb.append(charString.charAt(getRandom(len - 1)));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String refText = "where are you from";
        String voiceUrl = "/Users/wangzijie/Downloads/0d778f1a434c4451a54907b753e964ab.mp3";
        String type = "sent.eval";
//        STWebsocketAPI(voiceUrl, refText, type);
        /*模拟阻塞*/
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

