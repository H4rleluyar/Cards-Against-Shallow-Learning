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

    DeckModel whiteDeck;
    DeckModel blackDeck;
    ArrayList<PlayerModel> players = new ArrayList<>();
    ArrayList<Integer> chosenCardIndex = new ArrayList<>(); //index in whiteDeck

    int curCzarIndex = 0;
    int curPlayerIndex = 1;
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

                ArrayList<String> nameList = new ArrayList<>();
                ArrayList<Integer> score = new ArrayList<>();
                for(PlayerModel p : players) {
                    nameList.add(p.getName());
                    score.add(p.getScore());
                }

                view.updatePlayersInView(nameList, score);

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
                if(whiteDeck.getCards().isEmpty() || blackDeck.getCards().isEmpty())
                    load(DEFAULT_FILE_DIR, whiteDeck, blackDeck);
                dealCards();
                ArrayList<String> curPlayerHand = new ArrayList<>();
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

    private int updateCurPlayer(){
        curPlayerIndex++;
        if(curPlayerIndex >= players.size())
            curPlayerIndex = 0;
        return curPlayerIndex;
    }

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

    //loads from txt file
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

    public boolean dealCards(){
        //deck is large enough for each player to get atleast one card
        if((whiteDeck.getNumOfCards() / players.size()) > 0){
            int playerIndx = 0;
            for(int i = 0; i < whiteDeck.getNumOfCards(); i++) {
                players.get(playerIndx).grabCard(i);
                lastCardDelt = i;
                playerIndx++;
                if(playerIndx > players.size() - 1)
                    playerIndx = 0;
                if(playerIndx == 0) {
                    if(players.get(0).getHand().size() >= 5)
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
