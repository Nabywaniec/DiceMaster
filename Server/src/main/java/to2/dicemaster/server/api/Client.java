package to2.dicemaster.server.api;

/**
 * @author Adam Gapiński
 */
public interface Client {
    void sendMessage(String message);
    void registerMessageHandler(MessageHandler handler);
}
