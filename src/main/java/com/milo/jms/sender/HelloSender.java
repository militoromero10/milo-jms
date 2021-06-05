package com.milo.jms.sender;

import com.milo.jms.config.JmsConfig;
import com.milo.jms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloSender {

    private final JmsTemplate jmsTemplate;


    // mandando mensaje cada 2 segs a cola my_queue
    @Scheduled(fixedRate = 2000)
    public void sendMessage(){
        System.out.println("Enviando mensaje");

        HelloWorldMessage message = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hola mundo!")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);
        System.out.println("Mensaje enviado");

    }

}
