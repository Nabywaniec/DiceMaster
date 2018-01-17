package diceMaster.model.common;

import diceMaster.model.common.Dices;
import diceMaster.model.common.User;

public class UserInGame {
    private User user;
    private Dices dices;
    private Integer score;
    private boolean isHisTurn;

    public UserInGame(User user, Dices dices, Integer score, boolean isHisTurn) {
        this.user = user;
        this.dices = dices;
        this.score = score;
        this.isHisTurn = isHisTurn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Dices getDices() {
        return dices;
    }

    public void setDices(Dices dices) {
        this.dices = dices;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public boolean isHisTurn() {
        return isHisTurn;
    }

    public void setHisTurn(boolean hisTurn) {
        isHisTurn = hisTurn;
    }
}
