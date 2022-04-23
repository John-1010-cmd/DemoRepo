package com.example.kafkademo.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaConsumer {
//    // 新建一个异常处理器，用@Bean注入
//    @Bean
//    public ConsumerAwareListenerErrorHandler consumerAwareErrorHandler() {
//        return (message, exception, consumer) -> {
//            System.out.println("消费异常：" + message.getPayload());
//            return null;
//        };
//    }

//    @KafkaListener(id = "testGroup1", topics = "kafkademo")
//    public void onMessage(ConsumerRecord<?, ?> record) {
//        System.out.println("KafkaConsumer... "+ record.value().toString());
//    }

//    /**
//     * @return void
//     * @Title 指定topic、partition、offset消费
//     * @Description 同时监听topic1和topic2，监听topic1的0号分区、topic2的 "0号和1号" 分区，指向1号分区的offset初始值为8
//     **/
//    @KafkaListener(id = "testGroup", groupId = "felix-group", topicPartitions = {
//            @TopicPartition(topic = "topic1", partitions = {"0"}),
//            @TopicPartition(topic = "topic2", partitions = "0", partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "8"))
//    })
//    public void onMessage2(ConsumerRecord<?, ?> record) {
//        System.out.println("topic:" + record.topic() + "|partition:" + record.partition() + "|offset:" + record.offset() + "|value:" + record.value());
//
//    }

//    @KafkaListener(id = "testGroup2",groupId = "felix-group", topics = "topic1")
//    public void onMessage3(List<ConsumerRecord<?, ?>> records) {
//        System.out.println(">>>批量消费一次，records.size()=" + records.size());
//        for (ConsumerRecord<?, ?> record : records) {
//            System.out.println(record.value());
//        }
//    }

//    // 新建一个异常处理器，用@Bean注入
//    @Bean
//    public ConsumerAwareListenerErrorHandler consumerAwareErrorHandler() {
//        return (message, exception, consumer) -> {
//            System.out.println("消费异常：" + message.getPayload());
//            return null;
//        };
//    }
//
//    @KafkaListener(id = "testGroup", topics = "kafkademo", errorHandler = "consumerAwareErrorHandler")
//    public void onMessage4(ConsumerRecord<?, ?> record) {
//        System.out.println("KafkaConsumer... " + record.value().toString());
//    }

    // 消息过滤监听
    @KafkaListener(topics = "kafkademo",containerFactory = "filterContainerFactory")
    public void onMessage6(ConsumerRecord<?, ?> record) {
        System.out.println(record.value());
    }
}
