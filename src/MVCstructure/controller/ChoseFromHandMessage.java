package MVCstructure.controller;

public class ChoseFromHandMessage implements Message{
    private int handIndex;

    public ChoseFromHandMessage(int handIndex) { this.handIndex = handIndex; }

    public int getIndex() { return handIndex; }
}
