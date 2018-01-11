package diceMaster.model.common;

public class User {
    public User(String nick) {
        this.nick = nick;
    }

    private String nick;

    public String getNick() {
        return nick;
    }
}
