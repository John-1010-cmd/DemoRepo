package com.sq.bio.io;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 作者：刘兴 时间:2019/5/13
 **/
public class TimeServerHandlerExecutor {

    private ExecutorService executorService;


    public TimeServerHandlerExecutor(int maxPoolSize, int queueSize) {
        executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize, 120L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueSize));
    }


    public void executeTask(TimeServerHandler handler){
        executorService.execute(handler);
    }
}


