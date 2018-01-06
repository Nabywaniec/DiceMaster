package agh.to2.dicemaster.server.managers;

import agh.to2.dicemaster.server.User;

import java.util.*;

public class UsersManager {

//    FixMe: Maybe storing this in HashMaps isn't the best idea?
    private HashMap<String, String> userIdByQueueName = new HashMap<>();
    private HashMap<String, User> users = new HashMap<>();

    public User createUser(String username, String clientQueueName) {
        User user = new User(username, clientQueueName);
        userIdByQueueName.put(user.getServerQueueName(), user.getId());
        users.put(user.getId(), user);
        return user;
    }

    public Optional<User> getUserById(String userId) {
        return Optional.of(users.get(userId));
    }

    public Optional<User> getUserByQueueName(String queueName){
        return Optional.of(users.get(userIdByQueueName.get(queueName)));
    }

    public Collection<User> getAll() {
        return users.values();
    }

    public void removeUser(String userId) {
        User removedUser = users.remove(userId);
        userIdByQueueName.remove(removedUser.getServerQueueName());
    }
}
