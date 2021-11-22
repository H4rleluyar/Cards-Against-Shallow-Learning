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
    BlockingQueue<Message> queue;
    View view;

    DeckModel whiteDeck;
    DeckModel blackDeck;
    ArrayList<PlayerModel> players = new ArrayList<>();

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

            if(message.getClass() == DoNextPlayer.class){
                updateCurPlayer();
                cardMessage.clear();
                //grab description for cards in hand
                for(int i = 0; i < players.get(curPlayerIndex).getHand().size(); i++){
                    cardMessage.add(whiteDeck.getCards().get(players.get(curPlayerIndex).getHand().get(i)).toString());
                }
//                view.updateHand(cardMessage);
            }
            else if(message.getClass() == AddPlayerMessage.class){
                AddPlayerMessage playerMessage = (AddPlayerMessage) message;

                players.add(new PlayerModel(playerMessage.getName()));

                String names = "";
                for(PlayerModel p : players)
                    if(p == players.get(players.size() - 1))
                        names += p.getName();
                    else
                        names += p.getName() + ", ";
                view.updatePlayersInView(names);
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

//     //select who gets to be the Czar for the first match of the game
//     public void selectCzar(){ //
//         Collections.shuffle(this.players);
//         this.players.get(0).setCzar(true);
//     }

//     //loads from txt file
//     boolean load(String fileDir, DeckModel whiteDeck, DeckModel blackDeck){
//         File file = new File(fileDir);
//         try {
//             Scanner scan = new Scanner(file);
//             String line;
//             CardModel c;
//             while(scan.hasNextLine()){
//                 line = scan.nextLine();
//                 //ignores empty and commented lines
//                 if(!line.isEmpty()){
//                     if(line.charAt(0) != '#') {
//                         //black card
//                         if(line.split("\\s+")[0].equals("B")){
//                             blackDeck.addCard(new CardModel(line.substring(2)));
//                         }
//                         //white card
//                         else if(line.split("\\s+")[0].equals("W")){
//                             whiteDeck.addCard(new CardModel(line.substring(2)));
//                         }
//                     }
//                 }
//             }
//         }
//         catch (FileNotFoundException e) {
//             //file not found
//             return false;
//         }
//         return true;
//     }

//     boolean dealCards(){
//         //deck is large enough for each player to get atleast one card
//         if((whiteDeck.getNumOfCards() / players.size()) > 0){
//             int playerIndx = 0;
//             for(int i = 0; i < whiteDeck.getNumOfCards(); i++) {
//                 players.get(playerIndx).grabCard(i);
//                 playerIndx++;
//                 if(playerIndx > players.size() - 1)
//                     playerIndx = 0;
//                 if(playerIndx == 0) {
//                     if(players.get(0).getHand().size() > 10)
//                         return true;
//                     else if(whiteDeck.getNumOfCards() - (players.get(0).getHand().size() * players.size()) < players.size())
//                         return true;
//                 }
//             }
//             return true;
//         }
//         return false;
//     }

//     void addPlayers(PlayerModel p){
//         players.add(p);
//     }
}
