package org.example.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Evan
 */
@Configuration
public class RabbitConfig {

    @Bean("TopicExchange")
    public TopicExchange getTopicExchange(){
        return new TopicExchange("TOPIC_EXCHANGE");
    }

    @Bean("FanoutExchange")
    public FanoutExchange getFanoutExchange(){
        return new FanoutExchange("FANOUT_EXCHANGE");
    }

    @Bean(value = "FirstQueue")
    public Queue getFirstQueue(){
        Map<String, Object> args = new HashMap<String,Object>();
        args.put("x-message-ttl",6000);
        Queue queue = new Queue("FIRST_QUEUE", true, false, false, args);
        return queue;
    }

    @Bean(value = "SecondQueue")
    public Queue getSecondQueue(){
        return  new Queue("SECOND_QUEUE");
    }

    @Bean(value = "ThirdQueue")
    public Queue getThirdQueue(){
        return  new Queue("THIRD_QUEUE");
    }

    @Bean
    public Binding bindSecond(@Qualifier("SecondQueue") Queue queue,
                              @Qualifier("TopicExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("#.gupao.#");
    }


    @Bean
    public Binding bindThird(@Qualifier("ThirdQueue") Queue queue,
                              @Qualifier("FanoutExchange") FanoutExchange exchange){
        return BindingBuilder.bind(queue).to(exchange);
    }


}


