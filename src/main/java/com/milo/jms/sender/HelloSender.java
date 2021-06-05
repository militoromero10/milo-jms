package com.milo.jms.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.milo.jms.config.JmsConfig;
import com.milo.jms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloSender {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;


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

    @Scheduled(fixedRate = 2000)
    public void sendAndReceivedMessage() throws JMSException{
        HelloWorldMessage hello = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Holaaaaaaaaa ")
                .build();

        Message receivedMessage = jmsTemplate.sendAndReceive(JmsConfig.MY_SEND_RCV_QUEUE, session -> {
            Message helloMessage = null;
            try {
                helloMessage = session.createTextMessage(objectMapper.writeValueAsString(hello));
                helloMessage.setStringProperty("_mykey", "com.milo.jms.model.HelloWorldMessage");
                System.out.println("Enviando ->> hola");
                return helloMessage;
            } catch (JsonProcessingException e) {
                throw new JMSException("Booom!");
            }
        });
        System.out.println(receivedMessage.getBody(String.class));

    }

}
