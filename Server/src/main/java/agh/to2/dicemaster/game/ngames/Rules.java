import java.util.List;

public interface Rules {
    Integer getresult(Player player);
    int initializeRound(List<Player> players);
    void initializeDices(Player player);
    void drawDices(Player player, MoveDTO move);
    Integer getAim();



}
