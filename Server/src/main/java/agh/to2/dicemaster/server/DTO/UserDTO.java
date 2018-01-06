package agh.to2.dicemaster.server.DTO;

public class UserDTO {
    private String username;
    private String clientQueueName;

    public UserDTO(String username, String clientQueueName) {
        this.username = username;
        this.clientQueueName = clientQueueName;
    }

    public String getClientQueueName() {
        return clientQueueName;
    }

    public void setClientQueueName(String clientQueueName) {
        this.clientQueueName = clientQueueName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
