package MVCstructure.controller;

/**
 * @author 404 Bits Not Found (Henry Lai, Henry Fan, Gabby Kim, Bill Huynh)
 *
 */
public class ChoseFromHandMessage implements Message{
    /**
     * This class adds chosen card from hand message to blocking queue in Model package.
     * @param for "Choose this card" button
     * 		for the green cards
     */

    private int handIndex;

    public ChoseFromHandMessage(int handIndex) { this.handIndex = handIndex; }

    public int getIndex() { return handIndex; }
}
