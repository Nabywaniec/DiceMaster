package agh.to2.dicemaster.client;

import agh.to2.dicemaster.server.ServerPrototypeApplication;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Adam Gapiński
 */
public class ClientImpl implements Client {

    private String exchangeName = ServerPrototypeApplication.getExchangeName();
    private String serverQueueName = ServerPrototypeApplication.getQueueName();

    private Channel channel;

    public ClientImpl() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = factory.newConnection();
        channel = connection.createChannel();
    }

    @Override
    public void sendMessage(String message) {
        System.out.printf("Sending '%s'\n", message);

        try {
            channel.basicPublish(exchangeName, serverQueueName, null, message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registerMessageHandler(MessageHandler messageHandler) {
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                messageHandler.onMessage(message);
            }
        };

        try {
            String queueName = channel.queueDeclare().getQueue();
            channel.exchangeDeclare(exchangeName, "topic", true);
            channel.queueBind(queueName, exchangeName, "broadcast");
            channel.basicConsume(queueName, true, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
