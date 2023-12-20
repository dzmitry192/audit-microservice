package com.innowise.auditmicroservice.kafka;

import avro.ClientActionRequest;
import avro.UserDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
@RequiredArgsConstructor
public class KafkaListeners {
    private final CountDownLatch countDownLatch = new CountDownLatch(1);
    private UserDetailsResponse userDetailsResponse;

    @KafkaListener(topics = "${kafka.topics.user_details_response}", groupId = "user_details_response_id", containerFactory = "listenerContainerFactory")
    public void userDetailsListener(UserDetailsResponse userDetailsResponse) {
        this.userDetailsResponse = userDetailsResponse;
        countDownLatch.countDown();
    }

    @KafkaListener(topics = "${kafka.topics.user_action_request}", groupId = "user_details_response_id", containerFactory = "listenerContainerFactory")
    public void userDetailsListener(ClientActionRequest clientActionRequest) {

    }

    public UserDetailsResponse waitForUserDetailsResponse() throws InterruptedException {
        countDownLatch.await();
        return userDetailsResponse;
    }
}