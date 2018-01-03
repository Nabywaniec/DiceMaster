public class BotFactory {

    public Bot createBot(BotType type) throws IllegalBotTypeException{
        switch (type) {
            case EASY:
                return new EasyBot();
            case DIFFICULT:
                return new DifficultBot();
        }

        throw new IllegalBotTypeException();
    }
}
