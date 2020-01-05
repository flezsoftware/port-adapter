package com.example.portadapter.api.services.kafka.repositories;


import com.example.portadapter.api.domain.model.PaymentStatus;
import com.example.portadapter.api.services.kafka.model.PaymentSessionKafka;
import com.example.portadapter.api.services.kafka.model.PaymentSessionKafkaDomain;
import com.example.portadapter.api.services.kafka.processors.PaymentSessionProcessor;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Profile("kafka")
@Service
public class PaymentSessionKafkaRepositorySupport {

    private final Processor processor;

    private final InteractiveQueryService interactiveQueryService;

    private ReadOnlyKeyValueStore<String, PaymentSessionKafka> paymentSessionKafkaKeyValueStore;

    @Value("${spring.cloud.stream.kafka.streams.bindings.ktable.consumer.materializedAs}")
    private String STORAGE;

    private ReadOnlyKeyValueStore<String, PaymentSessionKafka> store() {
        return interactiveQueryService.getQueryableStore(STORAGE, QueryableStoreTypes.keyValueStore());
    }

    public PaymentSessionKafkaRepositorySupport(Processor processor, PaymentSessionProcessor paymentSessionProcessor, InteractiveQueryService interactiveQueryService) {
        this.processor = processor;
        this.interactiveQueryService = interactiveQueryService;
    }

    private Message<PaymentSessionKafka> createMessage(PaymentSessionKafka s) {
        return MessageBuilder.withPayload(s).setHeader(KafkaHeaders.MESSAGE_KEY, s.getAmountDueGuid()).build();
    }

    public PaymentSessionKafkaDomain save(PaymentSessionKafkaDomain paymentSession) {
        Message<PaymentSessionKafka> message = createMessage(fromDomain(paymentSession));
        processor.output().send(message);
        return paymentSession;
    }

    public PaymentSessionKafkaDomain findById(String id) {
        if (paymentSessionKafkaKeyValueStore == null) {
            paymentSessionKafkaKeyValueStore = store();
        }
        PaymentSessionKafka paymentSession = paymentSessionKafkaKeyValueStore.get(id);
        if (paymentSession == null)
            return null;
        return toDomain(paymentSessionKafkaKeyValueStore.get(id));
    }

    public PaymentSessionKafkaDomain toDomain(PaymentSessionKafka paymentSession) {
        return new PaymentSessionKafkaDomain(paymentSession.getId().toString(), paymentSession.getCurrency().toString(), paymentSession.getAmount(), PaymentStatus.valueOf(paymentSession.getStatus().name()), paymentSession.getAmountDueGuid().toString());
    }

    public PaymentSessionKafka fromDomain(PaymentSessionKafkaDomain paymentSession) {
        return PaymentSessionKafka.newBuilder().setId(paymentSession.getId()).setAmountDueGuid(paymentSession.getAmountDueGuid()).setAmount(paymentSession.getAmount()).setStatus(com.example.portadapter.api.services.kafka.model.PaymentStatus.valueOf(paymentSession.getStatus().name())).setCurrency(paymentSession.getCurrency()).build();
    }
}
