package diceMaster.view;

import javafx.scene.Group;

import java.util.LinkedList;
import java.util.List;

public class DicesField extends Group {
    private List<DiceView> diceViews = new LinkedList<>();
    private double dicesFiledScale = 1;

    public DicesField() {
        for (int i = 0; i < 5; i++) {
            DiceView d = new DiceView();
            d.setLayoutX(0 + 100 * i * dicesFiledScale);

            d.setLayoutY(0);
            this.diceViews.add(d);
            this.getChildren().add(d);
        }
    }


    public void setDicesDots(List<Integer> dotsToSet) {
        for (int i = 0; i < 5; i++) {
            diceViews.get(i).setNumberOfDots(dotsToSet.get(i));
        }
    }

    public List<DiceView> getDiceViews() {
        return diceViews;
    }

    public double getDicesFiledScale() {
        return dicesFiledScale;
    }

    public void setDicesFiledScale(double dicesFiledScale) {
        this.dicesFiledScale = dicesFiledScale;
        for (DiceView d : diceViews) {
            d.setDiceScale(dicesFiledScale);
        }
        reLocate();
    }
    private void reLocate(){
        int i =0;
        for (DiceView d: diceViews) {
            d.setLayoutX(0 + 100 * i * dicesFiledScale);
            d.setLayoutY(0);
            i++;
        }
    }
}
