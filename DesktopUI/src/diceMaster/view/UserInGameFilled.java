package diceMaster.view;

import agh.to2.dicemaster.common.api.UserInGame;
import javafx.scene.Group;
import javafx.scene.text.Text;

public class UserInGameFilled extends Group {
    private Text nickText = new Text();
    private DicesField dices = new DicesField();
    private Text scoreText = new Text();

    public UserInGameFilled() {
    }

    public void init(UserInGame player) {
        this.nickText.setText(player.getUserName());
        this.nickText.setY(-10.0);
        this.scoreText.setText(player.getScore().toString());
        this.scoreText.setX(80.0);
        this.scoreText.setY(10.0);
        this.dices.setDicesFiledScale(0.15);
        this.dices.setDicesDots(player.getDices().getDicesScore());
        this.getChildren().clear();
        this.getChildren().add(nickText);
        this.getChildren().add(scoreText);
        this.getChildren().add(dices);
    }
}
