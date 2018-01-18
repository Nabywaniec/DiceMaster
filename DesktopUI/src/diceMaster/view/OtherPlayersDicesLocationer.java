package diceMaster.view;

import javafx.scene.Group;

import java.util.List;

public class OtherPlayersDicesLocationer extends Group{
    private int tableSize = 0;
    private List<DicesField> otherPlayersDices;

    public OtherPlayersDicesLocationer(int tableSize) {
        this.tableSize = tableSize;
    }
}
