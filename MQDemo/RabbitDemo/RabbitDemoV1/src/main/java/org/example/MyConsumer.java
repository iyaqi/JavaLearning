package org.example;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Evan
 */
public class MyConsumer {

    private final static String EXCHANGE_NAME = "SIMPLE_EXCHANGE";
    private final static String QUEUE_NAME = "SIMPLE_QUEUE";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("admin");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct",true);
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        System.out.println("Waiting for message...");

        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "gupao.best");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"UTF-8");
                System.out.println("Received message :" + msg + ".");
                System.out.println("ConsumerTag :" + consumerTag);
                System.out.println("DeliveryTag :" + envelope.getDeliveryTag());
            }
        };


        channel.basicConsume(QUEUE_NAME,true,consumer);
    }

}
