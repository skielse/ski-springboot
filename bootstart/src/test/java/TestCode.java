import org.junit.jupiter.api.Test;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;

public class TestCode {

    @Test
    public void Test() throws UnsupportedEncodingException {
        String str = "0x31";
        str = URLEncoder.encode(str, "utf-8");
        for (int i = 0; i < 100000000; i++) {
            str = MD2(str);
        }
        System.out.println(str);
    }

    /**
     * MD2加密
     */
    public  String MD2(String src) {
        try {
            // 获取MD2加密工具
            MessageDigest md = MessageDigest.getInstance("MD2");
            // 加密
            byte[] digest = md.digest(src.getBytes());
            // 获取二进制十六进制互转工具
            HexBinaryAdapter hexBinaryAdapter = new HexBinaryAdapter();
            // 将二进制数组转换为十六进制字符串
            // 输出结果
            return hexBinaryAdapter.marshal(digest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
