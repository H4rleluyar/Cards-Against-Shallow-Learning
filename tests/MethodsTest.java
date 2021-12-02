import MVCstructure.controller.Message;
import MVCstructure.model.PlayerModel;
import MVCstructure.view.View;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import MVCstructure.model.CardModel;
import MVCstructure.model.DeckModel;
import MVCstructure.controller.Controller;
import MVCstructure.model.PlayerModel;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodsTest {

    //initialize everything
    BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
    DeckModel whiteDeck = new DeckModel(); //deck will populated  load method
    DeckModel whiteDeck2 = new DeckModel(); //deck without the load method
    DeckModel blackDeck = new DeckModel(); //deck will populated  load method
    DeckModel blackDeck2 = new DeckModel(); //deck without the load method
    View view = new View(queue);
    ArrayList<PlayerModel> players = new ArrayList<>(); //array list the store the players who are playing the game
    ArrayList<Integer> chosenCardIndex = new ArrayList<>(); //index in whiteDeck
    Controller controller = new Controller(queue, whiteDeck, blackDeck, view);
    String DEFAULT_FILE_DIR1 = "JUnitDeck.txt"; //lets assume that this is our deck with the cards that we wanted to load for testLoadDeckTest() method
    String DEFAULT_FILE_DIR2 = "JunitDeck2.txt"; //lets assume that this is our deck with the cards that we wanted to load for testDealCardsTest() method

    @Test
    public void DeckAddRemoveCardTest(){ //Testing the adding and remove features in the Deck class to see if it is working
        CardModel testing1 = new CardModel("Hail Hydra");
        CardModel testing2 = new CardModel("HI");
        CardModel testing3 = new CardModel("Tetris is cool");
        CardModel testing5 = new CardModel("I Hope this WORKS!");
        CardModel testing4 = new CardModel("Pizza Time!");

        whiteDeck.addCard(testing2);
        whiteDeck.addCard(testing1);
        whiteDeck.addCard(testing3);
        whiteDeck.addCard(testing5);
        whiteDeck.addCard(testing4);
        //add card to the deck
        //whiteDeck now has: HI, Hail Hydra, Tetris is cool, I Hope this WORKS!, Pizza Time!
        //System.out.println(whiteDeck.getCard().toString()); //testing in console

        assertEquals(whiteDeck.getCard().toString(), "[HI, Hail Hydra, Tetris is cool, I Hope this WORKS!, Pizza Time!]" );

        //now testing the remove features
        whiteDeck.removeCard(0);
        whiteDeck.removeCard(1);
        //System.out.println(whiteDeck.getCard().toString());
        assertEquals(whiteDeck.getCard().toString(), "[Hail Hydra, I Hope this WORKS!, Pizza Time!]" );

    }


    @Test
    public void testLoadDeckTest(){ //testing load method in controller class

        //manual deck base on the defaultDeck.txt file we have
        CardModel testing1 = new CardModel("This is the first white card");
        CardModel testing2 = new CardModel("this is the second white card");
        CardModel testing3 = new CardModel("this is the third white card");
        CardModel testing4 = new CardModel("this is the fourth white card");
        CardModel testing5 = new CardModel("mixing");
        CardModel testing6 = new CardModel("works");
        CardModel testing7 = new CardModel("for this");
        whiteDeck2.addCard(testing1);
        whiteDeck2.addCard(testing2);
        whiteDeck2.addCard(testing3);
        whiteDeck2.addCard(testing4);
        whiteDeck2.addCard(testing5);
        whiteDeck2.addCard(testing6);
        whiteDeck2.addCard(testing7);


        controller.load(DEFAULT_FILE_DIR1, whiteDeck, blackDeck); //load method that make things easier

        assertEquals(whiteDeck.getCards().toString(),whiteDeck2.getCards().toString()); //check if both are the same

    }

    @Test
    public void testDealCardsTest(){ //testing dealCard method in controller class
        CardModel testing1 = new CardModel("This is the first white card");
        CardModel testing2 = new CardModel("this is the second white card");
        CardModel testing3 = new CardModel("this is the third white card");
        CardModel testing4 = new CardModel("this is the fourth white card");
        CardModel testing5 = new CardModel("mixing");
        CardModel testing6 = new CardModel("works");
        CardModel testing7 = new CardModel("for this");
        whiteDeck2.addCard(testing1);
        whiteDeck2.addCard(testing2);
        whiteDeck2.addCard(testing3);
        whiteDeck2.addCard(testing4);
        whiteDeck2.addCard(testing5);
        whiteDeck2.addCard(testing6);
        whiteDeck2.addCard(testing7);
        //manual deck base on the defaultDeck.txt file we have

        //create a bunch of players
        PlayerModel player1 = new PlayerModel("Bill");
        PlayerModel player2 = new PlayerModel("Henry");
        PlayerModel player3 = new PlayerModel("Gabby");
        PlayerModel player4 = new PlayerModel("HenryL");

        //put those player object into the controller class that has an ArrayList of Players inside it
        controller.getPlayers().add(player1);
        controller.getPlayers().add(player2);
        controller.getPlayers().add(player3);
        controller.getPlayers().add(player4);
        int curPlayerIndex = 1; // index to keep track of each player, example index 1 is player 1 and index 2 is player 2

        controller.load(DEFAULT_FILE_DIR2, whiteDeck, blackDeck);
        System.out.println(whiteDeck.getCard().toString());


        controller.dealCards();


        ArrayList<String> curPlayerHand = new ArrayList<String>(); //
        for(int index : controller.getPlayers().get(curPlayerIndex).getHand()) //get the base on the player's position/order, get the card ArrayList from their hand
            curPlayerHand.add(whiteDeck.getCards().get(index).toString());

        controller.load(DEFAULT_FILE_DIR2, whiteDeck, blackDeck);


        /* assume that the deck has not been shuffled yet and we base on DEFAULT_FILE_DIR2 we have 20 cards
        player1 should get card at index 0, 4, 8, 12, 16
        player2 should get card at index 1, 5, 9, 13, 17
        player3 should get card at index 2, 6, 10, 14, 18
        player4 should get card at index 3, 7, 11, 15, 19
         */
        assertEquals(player1.getHand().toString(),"[0, 4, 8, 12, 16]");
        assertEquals(player2.getHand().toString(),"[1, 5, 9, 13, 17]");
        assertEquals(player3.getHand().toString(),"[2, 6, 10, 14, 18]");
        assertEquals(player4.getHand().toString(),"[3, 7, 11, 15, 19]");


    }



}
