package com.example.portadapter.api.services.kafka.listeners;


import com.example.portadapter.api.services.kafka.model.PaymentSessionKafka;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.Message;


@EnableBinding(Processor.class)
@RequiredArgsConstructor
public class PaymentSessionKafkaService {

    private final Processor processor;

    @StreamListener(Processor.INPUT) public void process(Message<PaymentSessionKafka> message){
       // System.out.println(message);
    }

    public void send(Message<PaymentSessionKafka> message){
        processor.output().send(message);
    }

}
