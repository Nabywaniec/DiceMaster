package agh.to2.dicemaster.game.poker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

import agh.to2.dicemaster.game.model.Dice;


public class Algo {

    private HashMap<Dice.Value, Integer> counter = new HashMap<>();

    public Algo() {
        Stream.of(Dice.Value.values())
                .forEach(value -> counter.put(value, 0));
    }

    private int sumdices(Dice.Value[] dices) {
        int s = 0;
        for (Dice.Value b : dices) {
            s += b.ordinal();
        }
        return s;
    }

    public int getResult(Dice.Value[] dices) {

        initialiseCounter();
        setCounter(dices);

        if (have5same(dices)) {
            System.out.println("Poker");
            return 500 + sumdices(dices);
        }

        if (have4same()) {
            System.out.println("Kareta");
            return 300 + sumdices(dices);
        }
        if (have3and2same()) {
            System.out.println("Full");
            return 200 + sumdices(dices);
        }
        if (haveBigStrit()) {
            System.out.println("Duży strit");
            return 150;
        }
        if (haveSmallStrit()) {
            System.out.println("Mały strit");
            return 130;
        }
        if (sum3() != -1) {
            System.out.println("Trójka");
            return sum3() + 100;
        }
        if (sum2And2() != -1) {
            System.out.println("2 Dwójki");
            return sum2And2() + 50;
        }
        if (sum2() != -1) {
            System.out.println("Dwójka");
            return sum2() + 30;
        }
        System.out.println("Nic");
        return sumdices(dices);


    }

    public boolean have5same(Dice.Value[] dices) {
        initialiseCounter();
        setCounter(dices);
        if (counter.keySet().contains(5)) return true;
        return false;
    }


    public boolean have4same() {
        if (counter.keySet().contains(4)) return true;
        return false;

    }

    public boolean have3and2same() {
        if (counter.values().contains(3) && counter.values().contains(2)) {
            return true;
        }
        return false;
    }

    public boolean haveBigStrit() {
        if (!counter.values().contains(3) && !counter.values().contains(2)
                && counter.get(Dice.Value.ONE).equals(0)) return true;
        return false;
    }

    public boolean haveSmallStrit() {
        if (!counter.values().contains(3) && !counter.values().contains(2)
                && counter.get(Dice.Value.ONE).equals(1)) return true;
        return false;
    }

    public int sum3() {
        if (counter.values().contains(3)) {
            for (Dice.Value key : counter.keySet()) {
                if (counter.get(key).equals(3)) {
                    return 3 * key.ordinal();
                }
            }
        }
        return -1;

    }

    public int sum2And2() {
        System.out.println(counter);
        int s = 0;
        for (Dice.Value key : counter.keySet()) {
            if (counter.get(key).equals(2)) s += 1;
        }
        if (s == 2) {
            s = 0;
            for (Dice.Value key : counter.keySet()) {
                if (counter.get(key).equals(2)) s += 2 * key.ordinal();
            }
            return s;
        }
        return -1;
    }

    public int sum2() {
        if (counter.values().contains(2)) {
            for (Dice.Value key : counter.keySet()) {
                if (counter.get(key).equals(2)) {
                    return 2 * key.ordinal();
                }
            }
        }
        return -1;
    }


    public void initialiseCounter() {
        for (Dice.Value b : this.counter.keySet()) {
            counter.put(b, 0);
        }
    }

    public void setCounter(Dice.Value[] dices) {
        for (Dice.Value dice : dices) {
            this.counter.put(dice, counter.get(dice) + 1);
        }

    }

}