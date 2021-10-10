//Unfinished Player class, Doesn't have Deck yet
public class Player {
    private String name; //name of the Player
    private int score; //amount of score the Player has

    /*
    *@param String name the name of Player
    * @param int score the Score of Player
     */
    public Player(String name, int score){
        this.name = name;
        this.score = score;
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


}
