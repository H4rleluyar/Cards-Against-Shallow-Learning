import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ConsoleTable {
    ArrayList<Player> players;
    Deck whiteDeck; //answers or funny descriptions
    Deck blackDeck;

    //first add player index, second add chosen card object
    //e.g. want to add player 2 chose 4th card in Hand
    //chosenCard.add(1); <-- player index
    //chosenCard.add(playerArr[1].hand.get(3)); <-- followed by chosen card object
    ArrayList<Object> chosenCard = new ArrayList<Object>();

    public ConsoleTable() {
        players = new ArrayList<Player>(); //hold the amount of player that will play the game
        whiteDeck = new Deck(); //answers or funny descriptions
        blackDeck = new Deck(); //question or discussion prompts
    }


    // we make the deck by making a test.txt file which is then loaded into our program from this load function
    boolean load(String fileDir) {
        File file = new File(fileDir);
        try {
            Scanner scan = new Scanner(file);
            String line;
            Card c;
            while (scan.hasNextLine()) {
                line = scan.nextLine();
                //ignores empty and commented lines
                if (!line.isEmpty()) {
                    if (line.charAt(0) != '#') {
                        //black card
                        if (line.split("\\s+")[0].equals("B")) {
                            blackDeck.addCard(new Card(line.substring(2)));
                        }
                        //white card
                        else if (line.split("\\s+")[0].equals("W")) {
                            whiteDeck.addCard(new Card(line.substring(2)));
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            //file not found
            return false;
        }
        return true;
    }


    //add Player object to the player’s arraylist
    void addPlayers(Player p) {
        players.add(p);
    }

    //used to check if our main method & code logic is working as intended, to test and see validity
    String whiteToString(){
        String retStr = "";
        retStr += "White Deck Cards:\n";
        retStr += whiteDeck.toString();
        return retStr;
    }

    String blackToString(){
        String retStr = "";
        retStr += "Black Deck Cards:\n";
        retStr += blackDeck.toString();
        return retStr;
    }

    //select who gets to be the Czar for the first match of the game
    public void selectCzar() {
        Collections.shuffle(this.players);
        this.players.get(0).setCzar(true);
    }

    /*
Initially was void but now is boolean dealCards()
*/
    boolean dealCards(){
        //deck is large enough for each player to get at least one card
        //checks if whitecard is empty or if there are any current players
        if((whiteDeck.getNumOfCards() / players.size()) > 0){
            int playerIndx = 0; //first player
            //to check if we have enough cards in our white deck
            for(int i = 0; i < whiteDeck.getNumOfCards(); i++) {
                //here we give the 1st player the 1st white card from the white deck
                players.get(playerIndx).grabCard(whiteDeck.getCards().get(i));
                playerIndx++; //next player
                //if we arrive at the 5th player, but there isn’t a 5th player, we loop back around to the 1st player so they get their second white card

                if(playerIndx > players.size() - 1)
                    playerIndx = 0;
                if(playerIndx == 0) {
                    //checking so that players do not get more than 10 cards
                    if(players.get(0).getHand().size() > 10)
                        return true;
                    else if(whiteDeck.getNumOfCards() - (players.get(0).getHand().size() * players.size()) < players.size())
                        return true;
                }
            }
            return true;
        }
        return false;
    }


    //display 1 black card on the table
    public void displayBlack() {
        int index = 0; //start from the top of deck
        if (blackDeck.getNumOfCards() > 0) { //check if deck is not empty
            System.out.println(blackDeck.getCards().get(index)); //print the top black card to the table
            blackDeck.getCard().remove(index); //remove the top black card of deck
        } else { //no more cards left
            System.out.println("There's no more black cards in the Deck!");
        }
    }
}
