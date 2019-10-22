package com.example.reactive.kafkaconsumer.adapter.kafka;


import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverRecord;

@Slf4j
@Service
public class KafkaConsumer {

  private final AtomicInteger counter = new AtomicInteger(0);
  private final KafkaReceiver<String, String> kafkaReceiver;

  public KafkaConsumer(KafkaReceiver<String, String> kafkaReceiver) {
    this.kafkaReceiver = kafkaReceiver;
  }

  @PostConstruct
  public void listenToEvents() {
    kafkaReceiver
        .receive()
        .flatMap(this::handleReceiverRecord)
        .subscribe(a -> log.info("Number of events received {}", counter.incrementAndGet()));
  }

  private Mono<String> handleReceiverRecord(final ReceiverRecord<String, String> receiverRecord) {
//    return Mono.just(receiverRecord)
//        .flatMap(this::createMessage)
//        .single();
    return Mono.empty();
  }

//  private Mono<String> createMessage(ReceiverRecord<String, String> receiverRecord) {
//    return Mono.just(receiverRecord.value());
//  }
}
