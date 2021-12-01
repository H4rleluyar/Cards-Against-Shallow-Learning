package MVCstructure.controller;

public class AddScoreMessage implements Message {

    private int score = 0; //all players start with a score of zero
    //testing a change from Henry Fan

    public AddScoreMessage(int score){
        this.score = score;
    }

    //get the score here
    public int getScore(){
        return score;
    }

}
