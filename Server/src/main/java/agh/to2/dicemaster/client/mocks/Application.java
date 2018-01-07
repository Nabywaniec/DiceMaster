package agh.to2.dicemaster.client.mocks;

import agh.to2.dicemaster.client.api.Server;

public class Application {
    public static void main(String[] args) {
        Server server = Server.createDiceMasterServer("localhost");

        System.out.println(server.registerClient("adam"));
    }
}
