package com.company;


import com.rabbitmq.client.*;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeoutException;

public class TestConsumer implements Runnable{
    private static final String EXCHANGE_NAME = "direct_logs";

    private Integer threadCount;
    public TestConsumer(Integer threadCount) {
        this.threadCount = threadCount;
    }

    @Override
    public void run(){
        System.out.println("Starting thread :" + this.threadCount);
        try {
            try {
                startConsumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void startConsumer() throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("gft-wnsqa.gofrugalconnect.com");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("client");
        factory.setPassword("dwnd2$sck0");
        factory.setConnectionTimeout(20000);
        factory.setHandshakeTimeout(20000);
        factory.setShutdownTimeout(20000);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        String queueName = channel.queueDeclare().getQueue();

        channel.queueBind(queueName, EXCHANGE_NAME, "info");
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);

    }
}
