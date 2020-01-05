package com.example.portadapter.api.services.kafka.processors;

import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PaymentSessionProcessor {
    String INPUT = "ktable";

    @Input(INPUT)
    KTable<?,?> input();

    @Output(INPUT)
    MessageChannel output();

}
