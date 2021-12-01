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

    int curCzerIndex = 0;
    int curPlayerIndex = 1;

    public Controller(BlockingQueue<Message> queue, DeckModel whiteDeck, DeckModel blackDeck, View view){
        this.queue = queue;
        this.whiteDeck = whiteDeck;
        this.blackDeck = blackDeck;
        this.view = view;
    }

    public void mainLoop(){
        Message message = null;
        ArrayList<String> cardMessage = new ArrayList<>();

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
                for(PlayerModel p : players)
                    nameList.add(p.getName());

                view.updatePlayersInView(nameList);

                if(players.size() >= 4)
                    view.disableAddPlayerInView();
                if(players.size() > 2)
                    view.enableStartGameInView();
            }
            else if(message.getClass() == LoadFileMessage.class){
                LoadFileMessage fileMessage = (LoadFileMessage) message;

                load(fileMessage.getFileDir(), whiteDeck, blackDeck);
                view.disableLoadFileButtonInView();
            }
            else if(message.getClass() == StartGameMessage.class){
                if(whiteDeck.getCards().isEmpty() || blackDeck.getCards().isEmpty())
                    load(DEFAULT_FILE_DIR, whiteDeck, blackDeck);
                dealCards();
                ArrayList<String> curPlayerHand = new ArrayList<String>();
                for(int index : players.get(curPlayerIndex).getHand())
                    curPlayerHand.add(whiteDeck.getCards().get(index).toString());
                view.startAGameInView(players.get(curCzerIndex).getName(), blackDeck.getCards().get(0).toString(), curPlayerHand);
            }
            else if(message.getClass() == ChoseFromHandMessage.class){
                ChoseFromHandMessage handMessage = (ChoseFromHandMessage) message;

                chosenCardIndex.add(players.get(curPlayerIndex).getHand().get(handMessage.getIndex()));

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
                    cardMessage.clear();
                    //grab description for cards in hand
                    for(int i = 0; i < players.get(curPlayerIndex).getHand().size(); i++){
                        cardMessage.add(whiteDeck.getCards().get(players.get(curPlayerIndex).getHand().get(i)).toString());
                    }
//                view.updateHand(cardMessage);
                }
        }
    }

    private int updateCurPlayer(){
        curPlayerIndex++;
        if(curPlayerIndex > players.size())
            curPlayerIndex = 0;
        if(curPlayerIndex == curCzerIndex) {
            curPlayerIndex++;
            if(curPlayerIndex > players.size())
                curPlayerIndex = 0;
        }
        return curPlayerIndex;
    }

    private int updateCurCzer(){
        curCzerIndex++;
        if(curCzerIndex > players.size())
            curCzerIndex = 0;
        return curCzerIndex;
    }

    //select who gets to be the Czar for the first match of the game
    public void selectCzar(){ //
        Collections.shuffle(this.players);
        this.players.get(0).setCzar(true);
    }

    //loads from txt file
    boolean load(String fileDir, DeckModel whiteDeck, DeckModel blackDeck){
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

    boolean dealCards(){
        //deck is large enough for each player to get atleast one card
        if((whiteDeck.getNumOfCards() / players.size()) > 0){
            int playerIndx = 0;
            for(int i = 0; i < whiteDeck.getNumOfCards(); i++) {
                players.get(playerIndx).grabCard(i);
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
}
