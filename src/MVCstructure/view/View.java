package MVCstructure.view;

import MVCstructure.controller.AddPlayerMessage;
import MVCstructure.controller.Message;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class View extends JFrame {


    BlockingQueue<Message> queue;

   /*
   JTextField textField;
   JButton updateNameButton;
   JButton addClassButton;

   JLabel studentNameLabel;
   JLabel allClassesLabel;
    */

    JFrame mainFrame;   //name of the program
    JLabel headerLabel; //The title of the Game

    JButton addPlayerButton; //player add to game
    JButton removePlayerButton; //player remove to game
    JButton enterDeckButton;   //press enter so that card can be played
    JButton displayHandButton; //display the player's hand
    JButton enterCardButton;   //press enter so that card can be played

    JTextField deckName;  //deck that is being play .txt
    JTextField playerName;  //player name
    JTextField cardIndex;  //the index of card being played

    JLabel playerLabel; //player name
    JLabel deckLabel; //deck label
    JLabel blackCardLabel; //deck label

    public View(BlockingQueue<Message> queue) {
        this.queue = queue;

        this.headerLabel = new JLabel("Welcome to Cards Against Shallow Learning",JLabel.CENTER );
        this.playerLabel = new JLabel("Players");
        this.deckLabel  = new JLabel("Deck");
        this.blackCardLabel = new JLabel("Black Card Display: ");

        this.playerName = new JTextField(10); //The player that will be added into the game
        this.cardIndex = new JTextField(10); //card index, the index of the card in hand
        this.deckName = new JTextField(10); //the deck that is being played

        this.addPlayerButton = new JButton("Add Player");
        this.removePlayerButton = new JButton("Remove Player");
        this.displayHandButton = new JButton("Display Player");
        this.enterCardButton = new JButton("Enter Player");
        this.enterDeckButton = new JButton("Enter Deck");

        addPlayerButton.addActionListener(e->{
            String name = playerName.getText();

            try{
                Message msg = new AddPlayerMessage(name);
                queue.put(msg);
            } catch (InterruptedException exception) {
                //do nothing
            }
        });

//        removePlayerButton.addActionListener(e->{
//            try{
//                Message msg = new RemovePlayerMessage();
//            }
//        });

        this.setLayout(new BorderLayout(1,1));
        this.add(headerLabel, BorderLayout.PAGE_START);

        JPanel flowLayoutHolder = new JPanel();
        flowLayoutHolder.setLayout(new FlowLayout());
        flowLayoutHolder.add(playerLabel);
        this.add(flowLayoutHolder, BorderLayout.LINE_START);
        flowLayoutHolder.add(playerName);
        flowLayoutHolder.add(addPlayerButton);
        flowLayoutHolder.add(removePlayerButton);


        JPanel flowLayoutHolder2 = new JPanel();
        this.add(flowLayoutHolder2, BorderLayout.PAGE_END);

        flowLayoutHolder2.add(deckLabel);
        flowLayoutHolder2.add(deckName);
        flowLayoutHolder2.add(enterDeckButton);

        flowLayoutHolder.setVisible(true);

        this.setSize(500, 500);
        this.setLayout(new FlowLayout());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void updatePlayersInView(String name){ this.playerLabel.setText("Players[" + name + "]");}

   /*
   public void updateNameInView(String value) {
       this.studentNameLabel.setText(value);
   }

   public void updateListOfClassesInView(ArrayList<String> classes) {
       this.allClassesLabel.setText(classes.toString());
   }

    */
}
