package diceMaster.view;

import diceMaster.model.common.UserInGame;
import javafx.scene.Group;

import java.util.LinkedList;
import java.util.List;

public class UserInGameListView extends Group {

    public UserInGameListView(){}

    public void init(List<UserInGame> usersInGame) {
        int i = 0;
        for (UserInGame userInGame: usersInGame) {
            UserInGameFilled u = new UserInGameFilled();
            u.init(userInGame);
            u.setLayoutY(40 * i);
            this.getChildren().add(u);

            i++;
        }
    }
}
