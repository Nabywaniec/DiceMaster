public class Main {

    public static void main(String[] args) {
//        System.out.println("Hello World!");
        BotFactory factory = new BotFactory();

        Bot bot = null;

        GameDTO gameDTO = new GameDTO();
        try {
            bot = factory.createBot(BotType.EASY);
        } catch (IllegalBotTypeException e) {
            e.printStackTrace();
        }

        bot.makeMove(gameDTO);



    }
}