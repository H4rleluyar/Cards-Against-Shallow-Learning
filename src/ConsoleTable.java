import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class ConsoleTable {
    ArrayList<Player> players = new ArrayList<Player>();
    Deck whiteDeck = new Deck();
    Deck blackDeck = new Deck();
    //first add player index, second add chosen card object
    //e.g. want to add player 2 chose 4th card in Hand
    //chosenCard.add(1); <-- player index
    //chosenCard.add(playerArr[1].hand.get(3)); <-- followed by chosen card object
    ArrayList<Object> chosenCard = new ArrayList<Object>();

    boolean load(String fileDir){
        File file = new File(fileDir);
        try {
            Scanner scan = new Scanner(file);
            String line;
            Card c;
            while(scan.hasNextLine()){
                line = scan.nextLine();
                //ignores empty and commented lines
                if(!line.isEmpty()){
                    if(line.charAt(0) != '#') {
                        //black card
                        if(line.split("\\s+")[0].equals("B")){
                            blackDeck.addCard(new Card(line.substring(2)));
                        }
                        //white card
                        else if(line.split("\\s+")[0].equals("W")){
                            whiteDeck.addCard(new Card(line.substring(2)));
                        }
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            //file not found
            return false;
        }
        return true;
    }

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

    boolean dealCards(){
        //deck is large enough for each player to get atleast one card
        if((whiteDeck.getNumOfCards() / players.size()) > 0){
            int playerIndx = 0;
            for(int i = 0; i < whiteDeck.getNumOfCards(); i++) {
                players.get(playerIndx).grabCard(whiteDeck.getCards().get(i));
                playerIndx++;
                if(playerIndx > players.size() - 1)
                    playerIndx = 0;
                if(playerIndx == 0) {
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

    void addPlayers(Player p){
        players.add(p);
    }
}
