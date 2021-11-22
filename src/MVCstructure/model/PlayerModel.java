package MVCstructure.model;

import java.util.ArrayList;

//Unfinished MVCstructure.Player class, Doesn't have Deck yet
public class PlayerModel {
    private String name; //name of the MVCstructure.Player
    private int score; //amount of score the MVCstructure.Player has
    private ArrayList<Integer> hand = new ArrayList<>();
    private boolean czar;

    /*
    *@param String name the name of MVCstructure.Player
    * @param int score the Score of MVCstructure.Player
     */
    public PlayerModel(String name){
        this.name = name;
        this.score = 0;
    }

    /*
    * @return String of the MVCstructure.Player name
    */
    public String getName() {
        return this.name;
    }

    /*
    * setName to change MVCstructure.Player's name
    */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * @return int of the MVCstructure.Player's score
     */
    public int getScore(){
        return this.score;
    }

    /*
    *setScore to change MVCstructure.Player's score
     */
    public void setScore(int score){
        this.score = score;
    }

    /*
     *addScore to +1 points for player
     */
    public void addScore() {
        this.score++;
    }

    /*
     *minusScore to -1 points for player
     */
    public void minusScore() {
        this.score--;
    }

    /*
     *returns what player have in hand
     */
    public ArrayList<Integer> getHand(){
        return (ArrayList<Integer>) hand.clone();
    }

    /*
     *add a card to player's Hand
     */
    public void grabCard(Integer i){
        hand.add(i);
    }

    /*
     * @return boolean to see if player is the czar or not
     */
    public boolean isCzar(){
        return czar;
    }

    /*
            *setCzar to set the MVCstructure.Player as the czar
    */
    public void setCzar(boolean czar) {
        this.czar = czar;
    }

    //prints what the player have in hand
    public String toString(){
        String retStr = getName();
        retStr += "'s hand:\n";
        for(int i = 0; i < hand.size();i++){
            retStr += "   " + i + ".) " + hand.get(i).toString() + "\n";
        }
        return retStr;
    }
}

//    public void populateHand(Deck d){
//        while(hand.size() != 10){ //if players don't have 10 cards in the player's hand at start of match, then keep adding random white cards from deck to player hand
//            ArrayList<Card> tempWhiteCards = d.getCardsFromDeck();     // an arraylist of white Cards from Deck d
//            int randomCard = random.nextInt(tempWhiteCards.size()); //random index from tempWhiteCard
//            playerWhiteCards.add(tempWhiteCards.get(randomCard));   //add the random card into the player's hand
//            tempWhiteCards.remove(randomCard);
//        }
//    }