package agh.to2.dicemaster.server;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ServerBroadcaster implements CommandLineRunner {

    private String exchangeName = ServerApplication.getExchangeName();
    private RabbitTemplate rabbitTemplate;

    public ServerBroadcaster(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        Thread thread = new Thread(() -> {
            long messageCounter = 0;

            String message;
            while (true) {
                message = "server message " + messageCounter;
                rabbitTemplate.convertAndSend(exchangeName, "broadcast", message);
                messageCounter++;

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}
