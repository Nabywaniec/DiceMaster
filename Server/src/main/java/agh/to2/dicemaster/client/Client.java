package agh.to2.dicemaster.client;

/**
 * @author Adam Gapiński
 */
public interface Client {
    void sendMessage(String message);
    void registerMessageHandler(MessageHandler handler);
}
