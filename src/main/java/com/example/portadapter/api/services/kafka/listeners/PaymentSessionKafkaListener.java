package com.example.portadapter.api.services.kafka.listeners;

import com.example.portadapter.api.services.kafka.model.PaymentSessionKafka;
import com.example.portadapter.api.services.kafka.processors.PaymentSessionProcessor;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binder.kafka.streams.KafkaStreamsBinderSupportAutoConfiguration;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.Message;

@Profile("kafka")
@EnableBinding({PaymentSessionProcessor.class, Processor.class})
@RequiredArgsConstructor
public class PaymentSessionKafkaListener {

    @StreamListener
    public void process(@Input(PaymentSessionProcessor.INPUT) KTable<String, PaymentSessionKafka> message) {
        message.toStream().foreach((k,v) -> System.out.println(v));
    }

    @StreamListener(Processor.INPUT)
    public void process(Message<PaymentSessionKafka> message) {
        System.out.println("message " + message);
    }

}
