package MVCstructure.controller;

/*
From Lai 12/1/21 @ 1:40pm
Try to implement new add score buttons
Make it so that the number of buttons corresponds to the number player in the game
You can do that by creating 4 (max player we can have for now) individual buttons
And make them visible or not according to the number of players After the start game button pressed"
 */
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
