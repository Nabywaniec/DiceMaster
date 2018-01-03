import java.util.Arrays;
import java.util.List;

public class EasyBot extends Bot{

    @Override
    DiceOutputDTO getDicesToThrow(DiceInputDTO input){
        DiceOutputDTO result = new DiceOutputDTO();


        //FIXME mocked result prepared
        List<Integer> tmp = input.getMyInput();
        result.setDicesToThrow(tmp);


        return result;
    }

}
