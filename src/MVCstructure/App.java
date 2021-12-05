package MVCstructure;

import MVCstructure.controller.Controller;
import MVCstructure.controller.Message;
import MVCstructure.model.DeckModel;
import MVCstructure.view.View;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/** Initialized everything then run the Game
 * @author 404 Bits Not Found(Henry Lai, Henry Fan, Gabby Kim, Bill Huynh)
 */
public class App {
    public static void main(String args[]){
        BlockingQueue<Message> queue = new LinkedBlockingQueue<>();

        DeckModel whiteDeck = new DeckModel();
        DeckModel blackDeck = new DeckModel();
        View view = new View(queue);
        Controller controller = new Controller(queue, whiteDeck, blackDeck, view);
        controller.mainLoop();
    }
}
