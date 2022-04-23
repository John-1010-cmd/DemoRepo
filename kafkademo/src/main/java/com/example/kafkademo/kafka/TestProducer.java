//package com.example.kafkademo.kafka;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class TestProducer {
//    @Autowired
//    private KafkaTemplate<String,String> kafkaTemplate;
//
//    @GetMapping(value = "/aaa")
//    public  void aaa(){
//        for (int i = 0; i < 10 ; i++) {
//            kafkaTemplate.send("heartbeat4", "key", "{\"index\":\"1000\",\"ip\":\"666666666666666\",\"timestamp\":1638263084,\"type\":\"6\",\"value\":[]}");
//            System.out.println("发送成功");
//        }
//    }
//}
