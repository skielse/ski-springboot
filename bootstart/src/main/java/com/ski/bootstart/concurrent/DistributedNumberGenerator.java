package com.ski.bootstart.concurrent;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.*;

/**
 * @author wangzijie
 * @date 2023/2/21
 */
public class DistributedNumberGenerator {
    private static final String LOCK_KEY = "number-generator-lock";
    private static final String ID_KEY = "number-generator-id";
    private static RedissonClient redisson = null;

    public static synchronized long getNextNumber() {
        RLock lock = redisson.getLock(LOCK_KEY);
        long number = 0;
        try {
            // 尝试获取锁，最多等待10秒钟
            if (lock.tryLock(10, TimeUnit.SECONDS)) {
                // 从Redis中读取当前ID
                long currentId = redisson.getAtomicLong(ID_KEY).get();
                // 生成新的ID
                number = IdGenerator.nextId(currentId);
                // 将新的ID写回Redis
                redisson.getAtomicLong(ID_KEY).set(number);
            } else {
                throw new RuntimeException("获取分布式锁失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return number;
    }

    static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNamePrefix("DistributedNumberGenerator-pool").build();

    static ExecutorService executorService = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        // 初始化Redisson客户端
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        redisson = Redisson.create(config);

        // 启动多个线程并发取号
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 5; j++) {
                    long num = getNextNumber();
                    System.out.println(Thread.currentThread().getName() + " - " + num);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
