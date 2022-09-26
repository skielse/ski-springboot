package com.ski.bootstart.util;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author wangzijie
 * @date 2021/7/13
 */
@Slf4j
public class ReactorUtils {

    static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNamePrefix("ReactorUtils-pool").build();
    static ExecutorService executorService = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    private static final int DEFAULT_LIST_LENGTH = 20;

    /**
     * 提交任务,判断当前线程是否允许调Reactor的阻塞API，允许直接调用，否则提交到线程池调用
     *
     * @param supplier 任务
     * @param <T>      任务返回结果类型
     * @return 任务返回结果
     */
    public static <T> T reactorSubmit(Supplier<T> supplier) {
        if (Schedulers.isInNonBlockingThread()) {
            try {
                Map<String, String> mdc = MDCUtils.getCopyMDC();
                return executorService.submit(() -> {
                    try {
                        MDCUtils.setMDC(mdc);
                        return supplier.get();
                    } finally {
                        MDCUtils.clear();
                    }
                }).get();
            } catch (Exception e) {
                log.error("ReactorUtils executorService error ", e);
            }
        }
        return supplier.get();
    }


    /**
     * @param supplier 任务
     * @param <T>      返回的泛型
     * @return 返回Mono类型，并且是
     */
    public static <T> Mono<Optional<T>> elasticOptionalMono(Supplier<T> supplier) {
        Map<String, String> mdc = MDCUtils.getCopyMDC();
        return Mono.fromSupplier(() -> {
            MDCUtils.setMDC(mdc);
            try {
                return Optional.ofNullable(supplier.get());
            } catch (Exception e) {
                log.error("elasticOptionalMono error ", e);
                return Optional.<T>empty();
            }
        }).subscribeOn(Schedulers.elastic()).doFinally(it -> MDCUtils.clear());
    }


    /**
     * 批量调用通用方法，用于根据list请求数据返回list
     *
     * @param params   请求参数，暂时只支持一个，并且是list类型的
     * @param function 需要调用的方法，每次是批量请求
     * @param buffer   每次截取个数
     * @param <U>      请求单数List泛型类型
     * @param <R>      响应参数list泛型类型
     * @return 返回数据
     */
    public static <U, R> List<R> elasticListRequest(List<U> params, Function<List<U>, List<R>> function, int buffer) {
        if (CollectionUtils.isEmpty(params)) {
            return Collections.emptyList();
        }
        return reactorSubmit(() -> Flux.fromIterable(params)
                .buffer(buffer)
                .flatMap(it -> elasticOptionalMono(() -> function.apply(it)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .flatMapIterable(Function.identity())
                .collectList()
                .block());
    }

    /**
     * 批量调用通用方法，用于根据list请求数据返回list
     *
     * @param params   请求参数，暂时只支持一个，并且是list类型的
     * @param function 需要调用的方法，每次是批量请求
     * @param <U>      请求单数List泛型类型
     * @param <R>      响应参数list泛型类型
     * @return 返回数据
     */
    public static <U, R> List<R> elasticListRequest(List<U> params, Function<List<U>, List<R>> function) {
        return elasticListRequest(params, function, DEFAULT_LIST_LENGTH);
    }


    /**
     * 批量调用通用方法，用于根据list请求数据返回list
     *
     * @param params   请求参数，暂时只支持一个，并且是list类型的
     * @param function 需要调用的方法，每次是单个请求
     * @param <U>      请求单数List泛型类型
     * @param <R>      响应参数list泛型类型
     * @return 返回数据
     */
    public static <U, R> List<R> elasticRequest(List<U> params, Function<U, R> function) {
        if (CollectionUtils.isEmpty(params)) {
            return Collections.emptyList();
        }
        return reactorSubmit(() -> Flux.fromIterable(params)
                .flatMap(it -> elasticOptionalMono(() -> function.apply(it)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collectList()
                .block());
    }


    /**
     * @param params   请求参数
     * @param function 调用方式
     * @param <U>      请求单数
     * @param <R>      响应参数
     * @return map数据
     */
    public static <U, R> Map<U, R> mapRequest(List<U> params, Function<U, R> function) {
        if (CollectionUtils.isEmpty(params)) {
            return Collections.emptyMap();
        }
        return Flux.fromIterable(params)
                .flatMap(it -> elasticOptionalMono(() -> Tuples.of(it, function.apply(it))))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collectMap(Tuple2::getT1, Tuple2::getT2)
                .block();
    }
}
