package MVCstructure.controller;

/**
 * @author 404 Bits Not Found(Henry Lai, Henry Fan, Gabby Kim, Bill Huynh)
 */

public class RemovePlayerMessage implements Message{

    private String name;

    public RemovePlayerMessage(String name){
        this.name = name;
    }

    public String getName(){ return name; }

}
