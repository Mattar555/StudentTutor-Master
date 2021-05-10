package com.hmses.demo.service;

import com.hmses.demo.domain.EmailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Component
public class RabbitMqListener {

    @Autowired
    private StudentTutorService studentTutorService;

    @RabbitListener(queues = "${receive.rabbitmq.queue}")
    public void receiveMessage(EmailResponse emailResponse) {
        int mappedResponse = mapResponse(emailResponse.getResponse());
        if (mappedResponse != 0) {
            studentTutorService.updateEntry(
                    emailResponse.getEmailAddress(),
                    mapResponse(emailResponse.getResponse())
            );
        }
    }

    private int mapResponse(String response) {
        switch (response) {
            case "yes": return 1;
            case "no": return 2;
            default: return 0;
        }
    }
}
