
public class Main {
    public static void main(String args[]){
        ConsoleTable ct = new ConsoleTable(); //make new consoleTable object aka new game

        //we’re loading the black and white decks we wanna play with
        ct.load("test.txt");
        ct.whiteDeck.shuffleDeck(); //shuffle white deck
        ct.blackDeck.shuffleDeck(); // shuffle black deck

        //just to see if what we have works
        System.out.println(ct.whiteToString());
        System.out.println(ct.blackToString());

        //making three player objects
        Player player1 = new Player("Bill"), player2 = new Player("HenryL"), player3 = new Player("John");

        //adds all Players who are playing into the arraylist
        ct.addPlayers(player1);
        ct.addPlayers(player2);
        ct.addPlayers(player3);
        ct.dealCards(); //give out cards to the player

        //use to check if player if getHand is correctly working
        System.out.println("Bill's card:" + player1.getHand().toString() + "\n");
        System.out.println("Henry's card:" + player2.getHand().toString() + "\n" );
        System.out.println("John's card:" + player3.getHand().toString() + "\n");

        ct.selectCzar(); //select who will be the Czar

        //use to check if selectCzar method is working
        System.out.println("Player 1's Czar Status: " + player1.isCzar() + "\n"); //checking to test if this player is Czar
        System.out.println("Player 2's Czar Status " + player2.isCzar() + "\n"); //checking to test if this player is Czar
        System.out.println("Player 3's Czar Status " + player3.isCzar() + "\n"); //checking to test if this player is Czar

        ct.displayBlack(); //testing the display of top card
        ct.displayBlack(); //testing the display of second top card
        System.out.println(ct.blackToString()); //testing to see what is remaining in the deck
    }
}
