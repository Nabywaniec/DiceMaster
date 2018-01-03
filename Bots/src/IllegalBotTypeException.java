public class IllegalBotTypeException extends Exception {
    @Override
    public String getMessage() {
        return "Illegal bot type. Please provide EASY or DIFFICULT";
    }
}
