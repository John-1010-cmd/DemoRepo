package com.sq.log;

import com.sq.nettydemo.HelloServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Slf4j
public class logTest {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(logTest.class);
        logger.info("123");
//        log.info("test...log is here....");
//        Log logger = LogFactory.getLog(logTest.class);
//        logger.info("qqq");
    }
//
//    @Test
//    public void test(){
//        Logger logger = LoggerFactory.getLogger(Object.class);
//        logger.error("123");
//    }
}
