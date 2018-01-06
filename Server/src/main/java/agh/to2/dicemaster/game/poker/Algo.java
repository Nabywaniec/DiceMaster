package agh.to2.dicemaster.game.poker;

import java.util.ArrayList;
import java.util.HashMap;
import agh.to2.dicemaster.game.model.Dice;


public class Algo {

    private static HashMap<Dice, Integer> states;

    private HashMap<Dice.Value, Integer> counter = new HashMap<>();

    public Algo() {
        counter.put(Dice.Value.ONE, 0);
        counter.put(Dice.Value.TWO, 0);
        counter.put(Dice.Value.THREE, 0);
        counter.put(Dice.Value.FOUR, 0);
        counter.put(Dice.Value.FIVE, 0);
        counter.put(Dice.Value.SIX, 0);
    }

    private int sumdices(ArrayList<Dice.Value> dices) {
        int s = 0;
        for (Dice.Value b : dices) {
            s += b.ordinal();
        }
        return s;
    }

    public int getResult(ArrayList<Dice.Value> dices) {

        initialise_counter();
        setCounter(dices);

        if (have_5_same(dices)) {
            System.out.println("Poker");
            return 500 + sumdices(dices);
        }

        if (have_4_same(dices)) {
            System.out.println("Kareta");
            return 300 + sumdices(dices);
        }
        if (have_3and2_same(dices)) {
            System.out.println("Full");
            return 200 + sumdices(dices);
        }
        if (have_bigStrit(dices)) {
            System.out.println("Duży strit");
            return 150;
        }
        if (have_smallStrit(dices)) {
            System.out.println("Mały strit");
            return 130;
        }
        if (sum3(dices) != -1) {
            System.out.println("Trójka");
            return sum3(dices) + 100;
        }
        if (sum2_2(dices) != -1) {
            System.out.println("2 Dwójki");
            return sum2_2(dices) + 50;
        }
        if (sum2(dices) != -1) {
            System.out.println("Dwójka");
            return sum2(dices) + 30;
        }
        System.out.println("Nic");
        return sumdices(dices);


    }

    public boolean have_5_same(ArrayList<Dice.Value> dices) {
        initialise_counter();
        //  setCounter(dices);
        if (counter.keySet().contains(new Integer(5))) return true;
        return false;
    }


    public boolean have_4_same(ArrayList<Dice.Value> dices) {

        if (counter.keySet().contains(new Integer(4))) return true;
        return false;
    }

    public boolean have_3and2_same(ArrayList<Dice.Value> dices) {


        if (counter.values().contains(new Integer(3)) && counter.values().contains(new Integer(2))) {
            return true;
        }
        return false;
    }

    public boolean have_bigStrit(ArrayList<Dice.Value> dices) {
        if (!counter.values().contains(new Integer(3)) && !counter.values().contains(new Integer(2))
                && counter.get(Dice.Value.ONE).equals(new Integer(0))) return true;
        return false;
    }

    public boolean have_smallStrit(ArrayList<Dice.Value> dices) {
        if (!counter.values().contains(new Integer(3)) && !counter.values().contains(new Integer(2))
                && counter.get(Dice.Value.ONE).equals(new Integer(1))) return true;
        return false;
    }

    public int sum3(ArrayList<Dice.Value> dices) {
        if (counter.values().contains(new Integer(3))) {
            for (Dice.Value key : counter.keySet()) {
                if (counter.get(key).equals(new Integer(3))) return 3 * key.ordinal();
            }
        }
        return -1;

    }

    public int sum2_2(ArrayList<Dice.Value> dices) {
        System.out.println(counter);
        int s = 0;
        for (Dice.Value key : counter.keySet()) {
            if (counter.get(key).equals(new Integer(2))) s += 1;
        }
        if (s == 2) {
            s = 0;
            for (Dice.Value key : counter.keySet()) {
                if (counter.get(key).equals(new Integer(2))) s += 2 * key.ordinal();
            }
            return s;
        }
        return -1;
    }

    public int sum2(ArrayList<Dice.Value> dices) {
        if (counter.values().contains(new Integer(2))) {
            for (Dice.Value key : counter.keySet()) {
                if (counter.get(key).equals(new Integer(2))) return 2 * key.ordinal();
            }
        }
        return -1;
    }


    public void initialise_counter() {
        for (Dice.Value b : this.counter.keySet()) {
            counter.put(b, 0);
        }
    }

    public void setCounter(ArrayList<Dice.Value> dices) {
        for (int i = 0; i < 5; i++) {
            this.counter.put(dices.get(i), counter.get(dices.get(i)) + 1);
        }

    }

    public static void main(String[] args) {


    }
}