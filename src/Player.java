import java.util.ArrayList;

//Unfinished Player class, Doesn't have Deck yet
public class Player {
    private String name; //name of the Player
    private int score; //amount of score the Player has
    private ArrayList<Card> hand = new ArrayList<Card>(); //cards from player hand
    private boolean czar; //used to check if a player is the czar for the match, which means he is the one who selects a card from the table, that card is associated with a player who will win the point from the round.

    /*
     *@param String name the name of Player
     */
    public Player(String name){
        this.name = name;
        this.score = 0; //players start off with a score of 0
        this.czar = false; //player start of as not czar
        this.hand = new ArrayList<Card>(); //the hand of the player
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
     *returns what player have in hand in clone, cloning arrayList so we wouldn’t mess up the handArrayList
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
