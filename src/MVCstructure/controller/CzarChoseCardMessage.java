package MVCstructure.controller;

/**
 * @author 404 Bits Not Found (Henry Lai, Henry Fan, Gabby Kim, Bill Huynh)
 *
 */
public class CzarChoseCardMessage implements Message{
    /**
     * This class adds czar's chosen card message to blocking queue in Model package.
     * @param for "Choose this card" button
     * 		for the white cards
     */
    private int chosenIndex;

    public CzarChoseCardMessage(int chosenIndex) { this.chosenIndex = chosenIndex; }

    public int getIndex() { return chosenIndex; }
}