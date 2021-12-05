package MVCstructure.model;

public class CardModel {

    private static final int MAX_LINE_CHAR_NUM = 100;
    private String description = ""; //description and text of the card
    private boolean hidden = false;
    private boolean used = false;

    public CardModel(){

    }

    /**
     *
     * @param description is the card's information/string
     */
    public CardModel(String description) {
        if(!setLine(description))
            description = "";
    }

    /**
     *
     * @return String to get the description
     */
    public String toString(){
        return description;
    }

    /**
     *
     * @return String to get the description
     */
    public String line() { return description; }

    /**
     *
     * @return boolean to check if card is hidden or not
     */
    public boolean flipped() { return hidden; }


    /**
     *
     * @return boolean to check if card is already used or not
     */
    public boolean used() { return used; }

    /**
     *
     * @return boolean to set the New line information
     */
    public boolean setLine(String newDescription) {
        if(newDescription.length() > MAX_LINE_CHAR_NUM)
            return false;

        description = newDescription;
        return true;
    }

    /**
     * to set the card the hidden status to true
     */
    public void flipCard() { hidden = !hidden; }

    /**
     *
     * @param used to set the card's status to used
     */
    public void setUsed(boolean used) { this.used = used; }
}
