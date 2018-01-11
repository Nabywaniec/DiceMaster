package agh.to2.dicemaster.client.api;

public class UserInGame {
    public final User user;
    public final boolean hisTurn;
    public final int score = 0;
    public final Dices playerDices;

    public UserInGame(User user, boolean hisTurn, Dices playerDices){
        this.user = user;
        this.hisTurn = hisTurn;
        this.playerDices = playerDices;
    }
}
