

public class Card {

    private static final int MAX_LINE_CHAR_NUM = 100; //not sure what this variable does
    private String description = ""; //description and text of the card
    private boolean hidden = false; // perhaps used for flipping over or revealing card
    private boolean used = false; //check if card is had already be used and puts them into discard pile

    public Card(){}


    //make card object with it’s description
    public Card(String description) {
        //checks if card has a description
        if(!setLine(description))
            description = "";
    }

    //toString method in order to get card information
    public String toString(){
        return description;
    }

    //accessor

    //return description of card
    public String line() { return description; }

    public boolean flipped() { return hidden; }
    public boolean used() { return used; }

    //mutator
    public boolean setLine(String newDescription) {
        if(newDescription.length() > MAX_LINE_CHAR_NUM)
            return false;

        description = newDescription;
        return true;
    }

    public void flipCard() { hidden = !hidden; }
    //need boolean value for our card constructor later, so that when we select the card to put onto the table we change status to used
    public void setUsed(boolean used) { this.used = used; }
}

