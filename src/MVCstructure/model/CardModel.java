package MVCstructure.model;

/** Represents the Card or Cards in the Game
 * @author 404 Bits Not Found(Henry Lai, Henry Fan, Gabby Kim, Bill Huynh)
 */
public class CardModel {

    private static final int MAX_LINE_CHAR_NUM = 100;
    private String description = ""; //description and text of the card
    private boolean hidden = false;
    private boolean used = false;

    public CardModel(){

    }

    /** Creates Card model based on with String description
     * @param description is the card's information/string
     */
    public CardModel(String description) {
        if(!setLine(description))
            description = "";
    }

    /** Get the card's description
     * @return String to get the description
     */
    public String toString(){
        return description;
    }

    /** Get the card's description
     * @return String to get the description of card
     */
    public String line() { return description; }

    /** Check if the card is hidden or not
     * @return boolean to check if card is hidden or not
     */
    public boolean flipped() { return hidden; }


    /** Check if card is used or not
     * @return boolean to check if card is already used or not
     */
    public boolean used() { return used; }

    /**Set the new line description
     * @return boolean to set the New line information
     */
    public boolean setLine(String newDescription) {
        if(newDescription.length() > MAX_LINE_CHAR_NUM)
            return false;

        description = newDescription;
        return true;
    }

    /**Set the Card's hidden status
     * to set the card the hidden status to true
     */
    public void flipCard() { hidden = !hidden; }

    /**Set the Card's hidden status
     * @param used to set the card's status to used
     */
    public void setUsed(boolean used) { this.used = used; }
}
