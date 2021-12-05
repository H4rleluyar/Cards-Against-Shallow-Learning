package MVCstructure.model;

import java.util.ArrayList;

/** Represent the Player in the Game
 * @author 404 Bits Not Found(Henry Lai, Henry Fan, Gabby Kim, Bill Huynh)
 */
public class PlayerModel {
    private String name; //name of the MVCstructure.Player
    private int score; //amount of score the MVCstructure.Player has
    private ArrayList<Integer> hand = new ArrayList<>();
    private boolean czar;

    /** Create a Player with a name and a starting score of 0
     *@param String name the name of MVCstructure.Player
     */
    public PlayerModel(String name){
        this.name = name;
        this.score = 0;
    }

    /** Get the name of the player
    * @return String of the MVCstructure.Player name
    */
    public String getName() {
        return this.name;
    }

    /** Set the Name for the player
     *
     * @param name to set the name for the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Get the score of the Player
     * @return int of the MVCstructure.Player's score
     */
    public int getScore(){
        return this.score;
    }

    /** Set the Score for the Player
     * @param score to set the score for the player
     */
    public void setScore(int score){
        this.score = score;
    }


    /** Add score from the player
     * addScore to increase the player's score by one
     */
    public void addScore() {
        this.score++;
    }

    /** Subtract score from the player
     *minusScore to -1 points for player
     */
    public void minusScore() {
        this.score--;
    }

    /** Get the Arraylist hold int of Player's hand
     *
     * @return an ArrayList holding Interger of what player have in hand
     */
    public ArrayList<Integer> getHand(){
        return (ArrayList<Integer>) hand.clone();
    }

    /**update hand to new hand
     *
     */
    public boolean setHand(ArrayList<Integer> newHand) {
        hand.clear();
        for(int index : newHand)
            hand.add(index);
        return true;
    }

    /** add a card to player's Hand
     * @param i to get the grab the card at player's hand
     */
    public void grabCard(Integer i){
        hand.add(i);
    }

    /** Check if the player is the czar or not
     * @return boolean to see if player is the czar or not
     */
    public boolean isCzar(){
        return czar;
    }

    /** Set the player's status as the czar
     *@param czar to set the MVCstructure.Player as the czar
    */
    public void setCzar(boolean czar) {
        this.czar = czar;
    }

    /** Return string that is formatted to look a  certain way
     *
     * @return String that is formatted to look a certain way
     */
    public String toString(){
        String retStr = getName();
        retStr += "'s hand:\n";
        for(int i = 0; i < hand.size();i++){
            retStr += "   " + i + ".) " + hand.get(i).toString() + "\n";
        }
        return retStr;
    }
}
