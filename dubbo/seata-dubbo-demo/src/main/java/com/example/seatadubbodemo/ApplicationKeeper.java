package com.example.seatadubbodemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ApplicationKeeper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationKeeper.class);

    private final ReentrantLock LOCK = new ReentrantLock();
    private final Condition STOP = LOCK.newCondition();

    /**
     * Instantiates a new Application keeper.
     *
     * @param applicationContext the application context
     */
    public ApplicationKeeper(AbstractApplicationContext applicationContext) {
        addShutdownHook(applicationContext);
    }

    private void addShutdownHook(final AbstractApplicationContext applicationContext) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    applicationContext.close();
                    LOGGER.info("ApplicationContext " + applicationContext + " is closed.");
                } catch (Exception e) {
                    LOGGER.error("Failed to close ApplicationContext", e);
                }

                LOCK.lock();
                try {
                    STOP.signal();
                } finally {
                    LOCK.unlock();
                }
            }
        }));
    }

    /**
     * Keep.
     */
    public void keep() {
        LOCK.lock();
        try {
            LOGGER.info("Application is keep running ... ");
            STOP.await();
        } catch (InterruptedException e) {
            LOGGER.error("ApplicationKeeper.keep() is interrupted by InterruptedException!", e);
        } finally {
            LOCK.unlock();
        }
    }
}

