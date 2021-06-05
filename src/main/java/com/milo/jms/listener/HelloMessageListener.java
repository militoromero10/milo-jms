package com.milo.jms.listener;

import com.milo.jms.config.JmsConfig;
import com.milo.jms.model.HelloWorldMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component
public class HelloMessageListener {

    // se ejecuta cada que envian algo a la cola my_queue
    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers, Message message){
        System.out.println("LLego el mensaje");
        System.out.println(helloWorldMessage);
    }
}
