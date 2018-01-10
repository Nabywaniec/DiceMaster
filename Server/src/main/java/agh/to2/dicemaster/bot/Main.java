package agh.to2.dicemaster.bot;


import agh.to2.dicemaster.bot.factory.BotFactory;
import agh.to2.dicemaster.bot.factory.NMultiplyBotFactory;
import agh.to2.dicemaster.bot.model.Bot;

public class Main {

    public static void main(String[] args) {
//        System.out.println("Hello World!");
        BotFactory factory = new NMultiplyBotFactory();

        Bot bot = null;


//        try {
//            bot = factory.createBot(BotType.EASY);
//        } catch (IllegalBotTypeException e) {
//            e.printStackTrace();
//        }

       // bot.makeMove(gameDTO);

    }
}