package agh.to2.dicemaster.common.api;

public abstract class GameConfigDTO implements DTO {
    private String tableName;
    private int maxPlayers;
    private GameType gameType;
    private int hardBotsCount;
    private int easyBotsCount;

    public GameConfigDTO(String tableName, int maxPlayers, GameType gameType, int hardBotsCount, int easyBotsCount) {
        this.tableName = tableName;
        this.maxPlayers = maxPlayers;
        this.gameType = gameType;
        this.hardBotsCount = hardBotsCount;
        this.easyBotsCount = easyBotsCount;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public int getHardBotsCount() {
        return hardBotsCount;
    }

    public void setHardBotsCount(int hardBotsCount) {
        this.hardBotsCount = hardBotsCount;
    }

    public int getEasyBotsCount() {
        return easyBotsCount;
    }

    public void setEasyBotsCount(int easyBotsCount) {
        this.easyBotsCount = easyBotsCount;
    }

    @Override
    public void fromJSON() {

    }

    @Override
    public String toJSON() {
        return null;
    }

}
