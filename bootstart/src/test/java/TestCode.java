import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class TestCode {

    @Test
    public void Test() throws UnsupportedEncodingException {
        String str = "0x31";
        str = URLEncoder.encode(str, "utf-8");
        for (int i = 0; i < 10; i++) {
            str = MD2(str);
        }
        System.out.println(str);
    }

    /**
     * MD2加密
     */
    public String MD2(String src) {
        try {
            // 获取MD2加密工具
            MessageDigest md = MessageDigest.getInstance("MD2");
            // 加密
            byte[] digest = md.digest(src.getBytes());
            // 获取二进制十六进制互转工具
            HexBinaryAdapter hexBinaryAdapter = new HexBinaryAdapter();
            // 将二进制数组转换为十六进制字符串
            // 返回结果
            return hexBinaryAdapter.marshal(digest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Test
    public void TestFileSeparator() {
        String path = "/abc/123";
        if (!path.endsWith(File.separator)) {
            path = path + File.separator;
        }
        System.out.println(path);
    }

   /* @Test
    public void TestRemove() {
        List<TestBean> list = new ArrayList<>();
        TestBean b1 = new TestBean();
        b1.setId(1);
        TestBean b2 = new TestBean();
        b2.setId(2);
        TestBean b3 = new TestBean();
        b3.setId(3);
        list.add(b1);
        list.add(b2);
        list.add(b3);
        List<Integer> list2 = Lists.newArrayList(2, 3);
        for (int i = 0; i < list.size(); i++) {
//            list.removeIf(e -> (e % 2) == 0);
            list.removeIf(testBean -> list2.contains(testBean.getId()));
        }
        System.out.println(list);
    }

    @Data
    static
    class TestBean {
        Integer id;
    }

    @Test
    public void testConcurrentRemoveIf() throws InterruptedException {
        List<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 5);
        List<Integer> list2 = Lists.newArrayList(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        final CountDownLatch latch = new CountDownLatch(integers.size());
        for (Integer i : integers) {
            executorService.submit(() -> {
                try {
                    for (Integer integer : integers) {
                        integers.removeIf(list2::contains);
                    }
                    log.info(String.valueOf(integers));
                } catch (Exception e) {
                    log.info("", e);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        log.info("finally list is : {}", integers);
    }*/
}
