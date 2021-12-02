package MVCstructure.controller;

import MVCstructure.model.CardModel;
import MVCstructure.model.DeckModel;
import MVCstructure.model.PlayerModel;
import MVCstructure.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class Controller {
    final static String DEFAULT_FILE_DIR = "defaultDeck.txt";
    BlockingQueue<Message> queue;
    View view;

    DeckModel whiteDeck; //will be used to store the Arraylist of White Cards that will be loaded from load method
    DeckModel blackDeck; //will be used to store the Arraylist of Black Cards that will be loaded from load method
    ArrayList<PlayerModel> players = new ArrayList<>(); //array list the store the players who are playing the game
    ArrayList<Integer> chosenCardIndex = new ArrayList<>(); //index in whiteDeck

    int curCzarIndex = 0; // index to keep track of who is the czar
    int curPlayerIndex = 1; // index to keep track of each player, example index 1 is player 1 and index 2 is player 2
    int lastCardDelt = 0;
    int lastBlackCardShown = 0;

    public Controller(BlockingQueue<Message> queue, DeckModel whiteDeck, DeckModel blackDeck, View view){
        this.queue = queue;
        this.whiteDeck = whiteDeck;
        this.blackDeck = blackDeck;
        this.view = view;
        whiteDeck.shuffleDeck();
        blackDeck.shuffleDeck();
    }

    public void mainLoop(){
        Message message = null;

        //selectCzar();
        while(view.isDisplayable()){
            try {
                message = queue.take();
            } catch (InterruptedException e){
                //do nothing
            }

            //When addPlayer button is pressed
            if(message.getClass() == AddPlayerMessage.class){
                AddPlayerMessage playerMessage = (AddPlayerMessage) message;

                players.add(new PlayerModel(playerMessage.getName()));

                System.out.println(playerMessage.getName());

                ArrayList<String> nameList = new ArrayList<>(); //arraylist that holds the player's name
                ArrayList<Integer> score = new ArrayList<>();   //arraylist that holds the player's score
                for(PlayerModel p : players) { //loop through to Arraylist player to get their information
                    nameList.add(p.getName()); //put information of player's name in nameList
                    score.add(p.getScore());   //put information of player's score in score
                }

                view.updatePlayersInView(nameList, score);


                //determine when the addPlayer button and StartGame work will work/stop working
                if(players.size() >= 4)
                    view.disableAddPlayerInView();
                if(players.size() > 2)
                    view.enableStartGameInView();
            }
            //load file button pressed
            else if(message.getClass() == LoadFileMessage.class){
                LoadFileMessage fileMessage = (LoadFileMessage) message;

                load(fileMessage.getFileDir(), whiteDeck, blackDeck);
                view.disableLoadFileButtonInView();
            }
            //game start button pressed
            else if(message.getClass() == StartGameMessage.class){
                if(whiteDeck.getCards().isEmpty() || blackDeck.getCards().isEmpty()) //check to make sure the whiteDeck and blackDeck is empty before loading in the txt file with cord information into the deck
                    load(DEFAULT_FILE_DIR, whiteDeck, blackDeck); // load the cards from txt into the whiteDeck and blackDeck
                dealCards(); //call this method to give players' cards from the Deck
                ArrayList<String> curPlayerHand = new ArrayList<>(); //The player's hand that store string information
                for(int index : players.get(curPlayerIndex).getHand())
                    curPlayerHand.add(whiteDeck.getCards().get(index).toString());
                view.startAGameInView(players.get(curPlayerIndex).getName(), players.get(curCzarIndex).getName(), blackDeck.getCards().get(0).toString(), curPlayerHand);
            }
            //when a card is chosen from hand
            else if(message.getClass() == ChoseFromHandMessage.class){
                ChoseFromHandMessage handMessage = (ChoseFromHandMessage) message;

                chosenCardIndex.add(players.get(curPlayerIndex).getHand().get(handMessage.getIndex()));
                ArrayList<Integer> hand = players.get(curPlayerIndex).getHand();
                hand.remove(handMessage.getIndex());
                players.get(curPlayerIndex).setHand(hand);

                ArrayList<String> chosenCardsDescription = new ArrayList<>();
                for(int cardIndex : chosenCardIndex){
                    chosenCardsDescription.add(whiteDeck.getCards().get(cardIndex).toString());
                }

                view.updateChosenCardInView(chosenCardsDescription);
                view.disableHandButtonsInView();
            }
            //when nextPlayer button is pressed
            else if(message.getClass() == DoNextPlayer.class){
                updateCurPlayer();
                ArrayList<String> handCardDescription = new ArrayList<>();
                if(curPlayerIndex != curCzarIndex) {
                    //grab description for cards in hand
                    for (int i = 0; i < players.get(curPlayerIndex).getHand().size(); i++) {
                        handCardDescription.add(whiteDeck.getCards().get(players.get(curPlayerIndex).getHand().get(i)).toString());
                    }

                    view.updateHandInView(handCardDescription);
                    view.enableHandButtonsInView();
                    view.updateCurPlayerInView(players.get(curPlayerIndex).getName());
                }
                else{
                    view.updateHandInView(handCardDescription);
                    view.showChosenButtonsInView();
                    view.updateCurPlayerInView("Czar");
                    chosenCardIndex.clear();
                 }
            }

            else if(message.getClass() == CzarChoseCardMessage.class){
                CzarChoseCardMessage chosenMessage = (CzarChoseCardMessage) message;
                updateCurCzer();
                updateCurPlayer();//skipping czer index
                updateCurPlayer();

                ArrayList<String> handCardDescription = new ArrayList<>();

                for (int i = 0; i < players.get(curPlayerIndex).getHand().size(); i++) {
                    handCardDescription.add(whiteDeck.getCards().get(players.get(curPlayerIndex).getHand().get(i)).toString());
                }

                view.hideChosenButtonsInView();
                view.updateHandInView(handCardDescription);
                view.enableHandButtonsInView();
                view.enableDoNextPlayerInView();
                view.updateChosenCardInView(new ArrayList<>());
                view.updateCzarInView(players.get(curCzarIndex).getName());
                lastBlackCardShown++;
                view.updateBlackCardInView(blackDeck.getCards().get(lastBlackCardShown).toString());
            }
        }
    }

    //used to get the Next Player in the array list
    private int updateCurPlayer(){
        curPlayerIndex++; //increase the position by 1
        if(curPlayerIndex >= players.size()) //check to see if the index isnt bigger than who is playing
            curPlayerIndex = 0; // reset the player to index 0 ???????, shouldn't this be 1
        return curPlayerIndex;
    }

    //same concept as the updateCurPlayer() method
    private int updateCurCzer(){
        curCzarIndex++;
        if(curCzarIndex >= players.size())
            curCzarIndex = 0;
        return curCzarIndex;
    }

    //select who gets to be the Czar for the first match of the game
    public void selectCzar(){ //
        Collections.shuffle(this.players);
        this.players.get(0).setCzar(true);
    }

    //first param is the file with card information, second and third param is the location of where we will store the white or black card respectively
    public boolean load(String fileDir, DeckModel whiteDeck, DeckModel blackDeck){
        File file = new File(fileDir);
        try {
            Scanner scan = new Scanner(file);
            String line;
            CardModel c;
            while(scan.hasNextLine()){
                line = scan.nextLine();
                //ignores empty and commented lines
                if(!line.isEmpty()){
                    if(line.charAt(0) != '#') {
                        //black card
                        if(line.split("\\s+")[0].equals("B")){
                            blackDeck.addCard(new CardModel(line.substring(2)));
                        }
                        //white card
                        else if(line.split("\\s+")[0].equals("W")){
                            whiteDeck.addCard(new CardModel(line.substring(2)));
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

    //use this method to give players' cards from the Deck
    public boolean dealCards(){
        //deck is large enough for each player to get atleast one card
        if((whiteDeck.getNumOfCards() / players.size()) > 0){  //check if the white card and player size is more than 0 before this method can run
            int playerIndx = 0; //starting at index 0
            for(int i = 0; i < whiteDeck.getNumOfCards(); i++) { //keeps running until the deck is empty
                players.get(playerIndx).grabCard(i); //get the card at whitedeck's index 1
                lastCardDelt = i;
                playerIndx++; //next card at whitedeck's index 2
                if(playerIndx > players.size() - 1) //reset to player index so first player can get card at index 5, assuming there is 4 players and each players have card 1,2 ,3 ,4 (respectively)
                    playerIndx = 0;
                if(playerIndx == 0) {
                    if(players.get(0).getHand().size() >= 5) //check if player has more or less than 5 cards
                        return true;
                    else if(whiteDeck.getNumOfCards() - (players.get(0).getHand().size() * players.size()) < players.size())
                        return true;
                }
            }
            return true;
        }
        return false;
    }

    //get an arraylist of players
    public ArrayList<PlayerModel> getPlayers(){
        return players;
    }
}
