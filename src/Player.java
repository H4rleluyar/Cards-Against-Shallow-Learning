import java.util.ArrayList;

//Unfinished Player class, Doesn't have Deck yet
public class Player {
    private String name; //name of the Player
    private int score; //amount of score the Player has
    private ArrayList<Card> hand = new ArrayList<Card>();
    private boolean czar;

    /*
    *@param String name the name of Player
    * @param int score the Score of Player
     */
    public Player(String name){
        this.name = name;
        this.score = 0;
    }

    /*
    * @return String of the Player name
    */
    public String getName() {
        return this.name;
    }

    /*
    * setName to change Player's name
    */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * @return int of the Player's score
     */
    public int getScore(){
        return this.score;
    }

    /*
    *setScore to change Player's score
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
    public ArrayList<Card> getHand(){
        return (ArrayList<Card>) hand.clone();
    }

    /*
     *add a card to player's Hand
     */
    public void grabCard(Card c){
        hand.add(c);
    }

    /*
     * @return boolean to see if player is the czar or not
     */
    public boolean isCzar(){
        return czar;
    }

    /*
            *setCzar to set the Player as the czar
    */
    public void setCzar(boolean czar) {
        this.czar = czar;
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