package com.ski.bootstart.concurrent;

/**
 * @author wangzijie
 * @date 2023/2/21
 */
public class IdGenerator {

    private static final long START_TIME = 1609459200000L;
    private static final long WORKER_ID_BITS = 10L;
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);

    private static final long SEQUENCE_BITS = 12L;
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    private static final long TIMESTAMP_SHIFT = WORKER_ID_BITS + SEQUENCE_BITS;
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);
    private static long workerId = 0L;
    private static long lastTimestamp = -1L;
    private static long sequence = 0L;

    public static synchronized long nextId(long currentId) {
        long timestamp = currentId >> TIMESTAMP_SHIFT;
        long sequence = currentId & SEQUENCE_MASK;
        if (timestamp < START_TIME) {
            throw new RuntimeException("时间戳错误");
        }
        if (timestamp > System.currentTimeMillis()) {
            throw new RuntimeException("时钟回拨");
        }
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                timestamp = waitNextMillis(timestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return ((timestamp - START_TIME) << TIMESTAMP_SHIFT) |
                (workerId << WORKER_ID_SHIFT) |
                sequence;
    }

    private static long waitNextMillis(long timestamp) {
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    public static synchronized void setWorkerId(long id) {
        if (id > MAX_WORKER_ID) {
            throw new IllegalArgumentException("工作机器ID超出范围");
        }
        workerId = id;
    }
}
