package org.example.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyProducer {

    @Autowired
    AmqpTemplate template;

    public void send(){
        template.convertAndSend("","FIRST_QUEUE","---------------- a direct msg");
        template.convertAndSend("TOPIC_EXCHANGE","shanghai.gupao.teacher",
                "---------------- a topic msg");
        template.convertAndSend("TOPIC_EXCHANGE","shanghai.gupao.x",
                "---------------- a topic msg");
        template.convertAndSend("FANOUT_EXCHANGE","", "---------------- a fanout msg");
    }
}
