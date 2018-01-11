package agh.to2.dicemaster.common.api;

public abstract class GameConfigDTO {
    public final String tableName;
    public final int maxPlayers;
    public final int roundsToWin;
    public final GameType gameType;
    public final int hardBotsCount;
    public final int easyBotsCount;

    public GameConfigDTO(int maxPlayers, int roundsToWin, GameType gameType, int hardBotsCount, int easyBotsCount,String tableName ){
        this.maxPlayers = maxPlayers;
        this.roundsToWin = roundsToWin;
        this.gameType = gameType;
        this.hardBotsCount = hardBotsCount;
        this.easyBotsCount = easyBotsCount;
        this.tableName = tableName;
    }

    public abstract void fromJSON(String JSON);
    public abstract void toJSON(String JSON);
}
