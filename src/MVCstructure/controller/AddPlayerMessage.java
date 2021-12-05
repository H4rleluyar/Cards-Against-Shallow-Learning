package MVCstructure.controller;

/**
 * @author 404 Bits Not Found (Henry Lai, Henry Fan, Gabby Kim, Bill Huynh)
 *
 */
public class AddPlayerMessage implements Message{
    /**
     * This class adds adding player message to blocking queue in Model package.
     * @param for "Add Player" button
     */
    private String name;

    public AddPlayerMessage(String name){
        this.name = name;
    }

    public String getName(){ return name; }
}
