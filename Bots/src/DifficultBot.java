import java.util.Arrays;
import java.util.List;

public class DifficultBot extends Bot{
    @Override
    DiceOutputDTO getDicesToThrow(DiceInputDTO input){
        DiceOutputDTO result = new DiceOutputDTO();


        //FIXME mocked result prepared
        List<Integer> tmp = Arrays.asList(4,5,6);
        result.setDicesToThrow(tmp);


        return result;
    }
}
