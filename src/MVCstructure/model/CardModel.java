package MVCstructure.model;

import java.awt.*;
import java.util.ArrayList;

public class CardModel {

    private static final int MAX_LINE_CHAR_NUM = 100;
    private String description = ""; //description and text of the card
    private boolean hidden = false;
    private boolean used = false;

    public CardModel(){

    }

    public CardModel(String description) {
        if(!setLine(description))
            description = "";
    }

    //toString method in order to get card information
    public String toString(){
        return description;
    }

    //accessor
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
    public void setUsed(boolean used) { this.used = used; }
}
