package MVCstructure.controller;

public class CzarChoseCardMessage implements Message{
    private int chosenIndex;

    public CzarChoseCardMessage(int chosenIndex) { this.chosenIndex = chosenIndex; }

    public int getIndex() { return chosenIndex; }
}
