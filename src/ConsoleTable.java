import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class ConsoleTable {
    Player[] playerArr = new Player[4];
    Deck whiteDeck = new Deck();
    Deck blackDeck = new Deck();
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
                        if(line.charAt(0) == 'B'){
                            blackDeck.addCard(new Card(line.substring(2)));
//                            System.out.println(line.substring(2));
                        }
                        //white card
                        else if(line.charAt(0) == 'W'){
                            whiteDeck.addCard(new Card(line.substring(2)));
//                            System.out.println(line.substring(2));
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
}
