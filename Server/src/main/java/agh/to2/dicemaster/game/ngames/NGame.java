import java.util.*;

public class NGame {
    GameDTO gameDTO;
    Rules rules;
    List<Player> players = new ArrayList<Player>();

    public NGame(int id,GameDTO gameDTO){
        this.gameDTO = gameDTO;
        if(gameDTO.getGameConfig().getGameType() == GameType.NPLUS){
            rules = new NPlus();
        }
        if(gameDTO.getGameConfig().getGameType() == GameType.NTIMES){
            rules = new NTimes();
        }
        for(User u : gameDTO.getPlayers()){
            players.add(new Player(u));
        }
    }

    GameDTO notifyGameChange(){
        return gameDTO;
    }
    /*
    public void initializeRound(){
        for(User p : gameDTO.getPlayers()){

        }

        Random generator = new Random();
        int pierwszy = generator.nextInt(this.gameDTO.getPlayers().size());
        for(int i = 0; i<5;i++){
            this.kosci[i] = generator.nextInt(6)+1;
        }
        int cel = 0;
        if(typ) {
            for (int i = 0; i < 5; i++) {
                cel += kosci[i];
            }
        }

            else{
                cel = 1;
                for(int i = 0; i<5;i++){
                    cel *= kosci[i];
                }
            }

    }

    public void requestMove(Player gracz){
        if(gracz.makeMove(this.typ, int...args) == this.cel) gracz.Zwyciestwo(); ZacznijRunde();

    }
    */




}
