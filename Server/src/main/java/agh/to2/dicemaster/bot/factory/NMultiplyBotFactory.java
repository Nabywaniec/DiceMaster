package agh.to2.dicemaster.bot.factory;

import agh.to2.dicemaster.bot.BotType;
import agh.to2.dicemaster.bot.IllegalBotTypeException;
import agh.to2.dicemaster.bot.model.Bot;
import agh.to2.dicemaster.bot.model.NMultiplyBot;

/**
 * Created by kamkalet on 03.01.2018.
 */
public class NMultiplyBotFactory extends AbstractBotFactory {

    @Override
    void adjustVeryEasyDifficulty() {

    }

    @Override
    void adjustVeryHardDifficulty() {

    }

    @Override
    public Bot createBot(BotType type) throws IllegalBotTypeException {
        super.determineDifficulty(type);
        return new NMultiplyBot();
    }
}
