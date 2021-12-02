package MVCstructure.controller;

public class RemovePlayerMessage implements Message{

    private String name;

    public RemovePlayerMessage(String name){
        this.name = name;
    }

    public String getName(){ return name; }

}
