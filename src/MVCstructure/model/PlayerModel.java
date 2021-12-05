package MVCstructure.model;

import java.util.ArrayList;

public class PlayerModel {
    private String name; //name of the MVCstructure.Player
    private int score; //amount of score the MVCstructure.Player has
    private ArrayList<Integer> hand = new ArrayList<>();
    private boolean czar;

    /**
    *@param String name the name of MVCstructure.Player
    * @param int score the Score of MVCstructure.Player
     */
    public PlayerModel(String name){
        this.name = name;
        this.score = 0;
    }

    /**
    * @return String of the MVCstructure.Player name
    */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @param name to set the name for the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return int of the MVCstructure.Player's score
     */
    public int getScore(){
        return this.score;
    }

    /**
     *
     * @param score to set the score for the player
     */
    public void setScore(int score){
        this.score = score;
    }


    /**
     * addScore to increase the player's score by one
     */
    public void addScore() {
        this.score++;
    }

    /**
     *minusScore to -1 points for player
     */
    public void minusScore() {
        this.score--;
    }

    /**
     *
     * @return an ArrayList holding Interger of what player have in hand
     */
    public ArrayList<Integer> getHand(){
        return (ArrayList<Integer>) hand.clone();
    }

    /**
     *update hand
     */
    public boolean setHand(ArrayList<Integer> newHand) {
        hand.clear();
        for(int index : newHand)
            hand.add(index);
        return true;
    }

    /**
     *add a card to player's Hand
     */
    public void grabCard(Integer i){
        hand.add(i);
    }

    /**
     * @return boolean to see if player is the czar or not
     */
    public boolean isCzar(){
        return czar;
    }

    /**
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
