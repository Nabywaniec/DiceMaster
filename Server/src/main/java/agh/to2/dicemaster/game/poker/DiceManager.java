package agh.to2.dicemaster.game.poker;

import agh.to2.dicemaster.game.model.Dice;

import java.util.ArrayList;

class DiceManager {

   public static ArrayList<Dice> getBones(int n){

       ArrayList<Dice> bones = new ArrayList<>();
       bones.clear();
       for(int i=0;i<n;i++){
           bones.add(new Dice());
       }
       return bones;

   }

}
