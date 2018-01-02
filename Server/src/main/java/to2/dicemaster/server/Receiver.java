package to2.dicemaster.server;

import org.springframework.stereotype.Component;

@Component
public class Receiver {
    public void receiveMessage(byte[] message) {
        System.out.println("Received: " + new String(message));
    }

    public void receiveMessage(String message) {
        System.out.println("Received: " + message);
    }
}
