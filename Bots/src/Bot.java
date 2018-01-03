public abstract class Bot extends Player{
    //public abstract DiceOutputDTO makeMove(DiceInputDTO input);

    abstract DiceOutputDTO getDicesToThrow(DiceInputDTO input);

    public void makeMove(GameDTO input) {
        IOConverter converter = new IOConverter();

        DiceInputDTO diceInput = converter.getDiceInputDTO(input);

        DiceOutputDTO diceOutput = this.getDicesToThrow(diceInput);

        converter.setGameState(diceOutput);

    }

    @Override
    public void notifyTableState(){
        // TODO implement this method
    }
}
