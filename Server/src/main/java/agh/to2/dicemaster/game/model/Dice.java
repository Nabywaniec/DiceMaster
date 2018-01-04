package agh.to2.dicemaster.game.model;


import java.util.Random;

public class Dice {
    private Value value = Value.randomValue();

    public enum Value {

        ONE, TWO, THREE, FOUR, FIVE, SIX;

        private static final Random RANDOM = new Random();

        private static Value randomValue() {
            return values()[RANDOM.nextInt(values().length)];
        }
    }

    public void roll() {
        this.value = Value.randomValue();
    }

    public int getValue() {
        return value.ordinal() + 1;
    }
}