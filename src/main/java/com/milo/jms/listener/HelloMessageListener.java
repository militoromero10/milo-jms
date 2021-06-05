package com.milo.jms.listener;

import com.milo.jms.config.JmsConfig;
import com.milo.jms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloMessageListener {

    private final JmsTemplate jmsTemplate;

    // se ejecuta cada que envian algo a la cola my_queue
    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers, Message message){
//        System.out.println("LLego el mensaje");
//        System.out.println(helloWorldMessage);
    }

    @JmsListener(destination = JmsConfig.MY_SEND_RCV_QUEUE)
    public void listenerParaHola(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers, Message message) throws JMSException {

        HelloWorldMessage mundo = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Mundoooooooo!")
                .build();
        System.out.println(message.getBody(String.class));
        jmsTemplate.convertAndSend(message.getJMSReplyTo(), mundo);
    }
}
