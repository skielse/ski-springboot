package com.ski.bootstart.concurrent;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @author wangzijie
 * @date 2023/2/21
 */
public class DistributedNumberGenerator2 {

    private static final String KEY = "number";
    private static final int RETRY_TIMES = 3;
    private static final long LOCK_TIMEOUT = 30L;
    private static final long WAIT_TIMEOUT = 5L;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    private final RedissonClient redissonClient;

    public DistributedNumberGenerator2() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        redissonClient = Redisson.create(config);
    }

    /**
     * 获取分布式锁 处理next值
     * @return next值
     */
    public long getNextNumber() {
        RLock lock = redissonClient.getLock(KEY);
        try {
            boolean success = lock.tryLock(LOCK_TIMEOUT, WAIT_TIMEOUT, TIME_UNIT);
            if (!success) {
                throw new RuntimeException("获取分布式锁失败");
            }
            RAtomicLong atomicLong = redissonClient.getAtomicLong(KEY);
            long currentId = atomicLong.get();
            long nextId = IdGenerator.nextId(currentId);
            atomicLong.set(nextId);
            return nextId;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DistributedNumberGenerator2 generator = new DistributedNumberGenerator2();
        for (int i = 0; i < 10; i++) {
            int threadNum = i + 1;
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    long number = generator.getNextNumber();
                    System.out.printf("Thread %d: %d%n", threadNum, number);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        Thread.sleep(10000);
        System.exit(0);
    }
}
