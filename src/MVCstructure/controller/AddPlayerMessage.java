package MVCstructure.controller;

public class AddPlayerMessage implements Message{
    private String name;

    public AddPlayerMessage(String name){
        this.name = name;
    }

    public String getName(){ return name; }


}
