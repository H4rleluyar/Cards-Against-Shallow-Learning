import org.junit.jupiter.api.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    @Test
    public void testLine(){
        Card empty = new Card();
        Card initialized = new Card("This is a test!");

        //testing initial states & accessor
        assertEquals(empty.line(), "", "Expecting empty Card to have empty line!");
        assertEquals(initialized.line(), "This is a test!", "Expecting initialized card to have line = \"This is a test!\"");

        //testing mutator
        empty.setLine("Not empty anymore!");
        assertEquals(empty.line(), "Not empty anymore!", "Expecting \"Not empty anymore!\" after mutating empty card");

        String strWith101Char = "shadjfhsjdhsjdhfjshdjshdjfhsjdhshdjfhsjdhfhdjshdhfjhfjdksjdhfhfjdksjdhfhfjdksjdhfhfjdksjdhfhfjdksjdhf";
        assertEquals(empty.setLine(strWith101Char), false, "Expect setLine returns false for over the character limit");
        assertEquals(empty.line(), "Not empty anymore!", "Expect line to not change after previous step.");
    }

    @Test
    public void testCardFlip(){
        Card empty = new Card();

        //testing initial state & accessor
        assertEquals(empty.flipped(), false, "Expecting card as not flipped as initial state");

        //testing mutator
        empty.flipCard();
        assertEquals(empty.flipped(), true, "Expecting card to be flipped after mutator call");

        empty.flipCard();
        assertEquals(empty.flipped(), false, "Expecting card to unflip after second mutator call");
    }

    @Test
    public void testUsed(){
        Card empty = new Card();

        //testing inital state & accessor
        assertEquals(empty.used(), false, "Expecting new cards to be unused");

        //testing mutator
        empty.setUsed(true);
        assertEquals(empty.used(), true, "Expecting card's used status to be after setting used to true");
        empty.setUsed(false);
        assertEquals(empty.used(), false, "Expecting card's used status to be false after setting it to false");
    }

    @Test
    public void arrayListTest(){
        ArrayList<Card> cardArr = new ArrayList<Card>();

        for(int i = 0; i < 20; i++)
            cardArr.add(new Card("I am card " + i + "!"));

        //testing each card is correctly set with their line, flipped status and used status
        for(int i = 0; i < 20; i++) {
            assertEquals(cardArr.get(i).line(), "I am card " + i + "!", "Expecting each card in list is given their corresponding index number");
            assertEquals(cardArr.get(i).flipped(), false, "Expecting card's flipped state as not flipped as initial state");
            assertEquals(cardArr.get(i).used(), false, "Expecting new cards to be unused");
        }
    }
}