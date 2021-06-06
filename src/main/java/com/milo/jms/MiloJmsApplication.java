package com.milo.jms;

//import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
//import org.apache.activemq.artemis.core.server.ActiveMQServer;
//import org.apache.activemq.artemis.core.server.ActiveMQServers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiloJmsApplication {

    public static void main(String[] args) throws Exception {

        //servidor activemq embebido
        //inicio
        //volar esto si contamos con un broker **dockerisar un activemq ejemplo**
      /*  ActiveMQServer server = ActiveMQServers.newActiveMQServer(new ConfigurationImpl()
                .setPersistenceEnabled(false)
                .setJournalDirectory("target/data/journal")
                .setSecurityEnabled(false)
                .addAcceptorConfiguration("invm", "vm://0"));

        server.start();*/
        //fin
        SpringApplication.run(MiloJmsApplication.class, args);
    }

}
