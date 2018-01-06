package agh.to2.dicemaster.bot.factory;


import agh.to2.dicemaster.bot.BotType;
import agh.to2.dicemaster.bot.IllegalBotTypeException;
import agh.to2.dicemaster.bot.model.Bot;
import agh.to2.dicemaster.bot.model.NPlusBotDifficult;
import agh.to2.dicemaster.bot.model.NPlusBotEasy;

/**
 * Created by kamkalet on 03.01.2018.
 */
public class NPlusBotFactory extends AbstractBotFactory {

    @Override
    void adjustVeryEasyDifficulty() {

    }

    @Override
    void adjustVeryHardDifficulty() {

    }

    @Override
    public Bot createBot(BotType type) throws IllegalBotTypeException {
        super.determineDifficulty(type);
        switch (type) {
            case EASY:
                return new NPlusBotEasy();
            case DIFFICULT:
                return new NPlusBotDifficult();
        }
        return new NPlusBotEasy();
    }
}