package MVCstructure.view;

import MVCstructure.controller.*;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

/** Responsible for the Gui of the Game
 * @author 404 Bits Not Found(Henry Lai, Henry Fan, Gabby Kim, Bill Huynh)
 */
public class View extends JFrame {
    BlockingQueue<Message> queue;

    //global definition for all components
    JLabel titleLabel; //The title of the Game
    JLabel playerLabel; //player name
    JLabel fileDirLabel;
    JLabel curPlayer;

    JTextField playerNameTextField;
    JTextField fileDirTextField;

    JButton addPlayerButton;
    JButton removePlayerButton;
    JButton loadFileButton;
    JButton startGameButton;
    JButton nextPlayerButton;            //used to go the the next player's turn
    ArrayList<JButton> handButtonArr;    //used by player to chose a card from their hand
    ArrayList<JButton> chosenButtonArr; //used by czar to choose the winner

    // new
    ArrayList<JTextArea> scoreBoard;
    JTextArea jtScoreBoard;


    JTextArea playerScoreBoard;
    JTextArea currCzarTextArea; //display who's the current czar
    JTextArea blackCardTextArea; //display black Card
    ArrayList<JTextArea> chosenCardsTextAreaArr;  //display the ArrayList that has all the cards that the player has chosen
    ArrayList<JTextArea> handTextAreaArr;         //display the Arraylist that has the player's hand and his/her card

    Font font;

    /** Method to indent A String a number of int spaces
     *
     * @param str the String
     * @param spaces the number of spaces
     * @return the String that is indented
     */
    public String indentLine(String str, int spaces) {
        return str.indent(spaces);
    }


    /** Represent the Gui/How the Game will look
     *
     * @param queue the Blocking queue
     */
    public View(BlockingQueue<Message> queue) {
        this.queue = queue;
        this.setSize(1280, 720);
        this.setLayout(null);
        handButtonArr = new ArrayList<>();
        chosenButtonArr = new ArrayList<>();
        chosenCardsTextAreaArr = new ArrayList<>();
        handTextAreaArr = new ArrayList<>();
        scoreBoard = new ArrayList<>();

        /*
         *Show title
         * */
        titleLabel = new JLabel("Welcome To Cards Against Shallow Learning");
        font = new Font("SansSerif", Font.BOLD, 30);
        titleLabel.setFont(font);
        titleLabel.setBounds(280, 10, 700, 40);
        this.add(titleLabel);

        /*
        Fields for entering info before game start
         */
        playerLabel = new JLabel("Enter Player's name:");
        playerLabel.setBounds(280, 60, 150, 20);
        this.add(playerLabel);

        playerNameTextField = new JTextField();
        playerNameTextField.setBounds(410,60,160,20);
        playerNameTextField.setDocument(new JTextFieldLimit(15));
        this.add(playerNameTextField);

        addPlayerButton = new JButton("Add");
        addPlayerButton.setBounds(580,60,70,20);
        this.add(addPlayerButton);
        addPlayerButton.addActionListener(e->{
            String name = playerNameTextField.getText();

            try{
                Message msg = new AddPlayerMessage(name);
                queue.put(msg);
            } catch (InterruptedException exception) {
                //do nothing
            }
        });

        removePlayerButton = new JButton("Remove");
        removePlayerButton.setBounds(660,60,85,20);
        this.add(removePlayerButton);
        removePlayerButton.addActionListener(e->{
            String name = playerNameTextField.getText();

            try{
                Message msg = new RemovePlayerMessage(name);
                queue.put(msg);
            } catch (InterruptedException exception) {
                //do nothing
            }
        });

        fileDirLabel = new JLabel("Enter File directory:");
        fileDirLabel.setBounds(280,90,150,20);
        this.add(fileDirLabel);

        fileDirTextField = new JTextField();
        fileDirTextField.setBounds(410,90,160,20);
        this.add(fileDirTextField);

        loadFileButton = new JButton("Load File/Deck");
        loadFileButton.setBounds(580,90,120,20);
        this.add(loadFileButton);
        loadFileButton.addActionListener(e->{
            try{
                Message msg = new LoadFileMessage(fileDirTextField.getText());
                queue.put(msg);
            } catch (InterruptedException exception) {
                //do nothing
            }
        });

        startGameButton = new JButton("Start Game");
        startGameButton.setBounds(760,60,100,50);
        startGameButton.setEnabled(false);
        this.add(startGameButton);
        startGameButton.addActionListener(e->{
            try{
                Message msg = new StartGameMessage();
                queue.put(msg);
            } catch (InterruptedException exception) {
                //do nothing
            }
        });

        /*
        Show Player Score board
         */
        playerScoreBoard = new JTextArea();
        playerScoreBoard.setEditable(false);
        playerScoreBoard.setOpaque(false);
        font = new Font("SansSerif", Font.BOLD, 12);
        playerScoreBoard.setFont(font);
        playerScoreBoard.setBounds(20,410,220, 150);
        this.add(playerScoreBoard);

        /*
        Show current player
         */
        curPlayer = new JLabel();
        curPlayer.setBounds(20,570, 220, 30);
        curPlayer.setVisible(false);
        this.add(curPlayer);

        /*
        Show nextPlayer Button
         */
        nextPlayerButton = new JButton("Next Player");
        nextPlayerButton.setEnabled(false);
        nextPlayerButton.setVisible(false);
        nextPlayerButton.setBounds(40, 620, 160,50);
        this.add(nextPlayerButton);
        nextPlayerButton.addActionListener(e->{
            nextPlayerButton.setEnabled(false);
            try{
                Message msg = new DoNextPlayer();
                queue.put(msg);
            } catch (InterruptedException exception) {
                //do nothing
            }
        });

        /*
         *Show current Czar
         * */
        currCzarTextArea = new JTextArea(" <Player's Name>\n is the Czar");
        currCzarTextArea.setWrapStyleWord(true);
        currCzarTextArea.setLineWrap(true);
        currCzarTextArea.setEditable(false);
        currCzarTextArea.setBackground(new Color(178, 168, 210));
        font = new Font("SansSerif", Font.BOLD, 17);
        currCzarTextArea.setFont(font);
        currCzarTextArea.setBounds(30, 80, 200, 43);
        currCzarTextArea.setVisible(false);
        this.add(currCzarTextArea);

        /*
         *Show black Card
         * */
        blackCardTextArea = new JTextArea("This is one of the Card's description, this ________ is a test");
        blackCardTextArea.setWrapStyleWord(true);
        blackCardTextArea.setLineWrap(true);
        blackCardTextArea.setEditable(false);
        blackCardTextArea.setBackground(Color.black);
        font = new Font("SansSerif", Font.BOLD, 15);
        blackCardTextArea.setFont(font);
        blackCardTextArea.setForeground(Color.white);
        blackCardTextArea.setBounds(40, 130, 180, 250);
        blackCardTextArea.setVisible(false);
        this.add(blackCardTextArea);

        /*
         *Show Hand
         * */
        for (int i = 0 ; i < 5; i++) {
            handTextAreaArr.add(new JTextArea("Default hand card"));
            handTextAreaArr.get(i).setWrapStyleWord(true);
            handTextAreaArr.get(i).setLineWrap(true);
            handTextAreaArr.get(i).setEditable(false);
            handTextAreaArr.get(i).setBackground(new Color(120, 160, 90));
            font = new Font("SansSerif", Font.BOLD, 13);
            handTextAreaArr.get(i).setFont(font);
            handTextAreaArr.get(i).setForeground(Color.black);
            //40 pixels from black card
            handTextAreaArr.get(i).setBounds(280 + 200 * i, 410, 160, 220);
            handTextAreaArr.get(i).setVisible(false);
            this.add(handTextAreaArr.get(i));
        }

        for (int i = 0; i < 5; i++) {
            handButtonArr.add(new JButton("Choose this card"));
            handButtonArr.get(i).setBounds(280 + 200*i, 650, 160, 20);
            handButtonArr.get(i).setVisible(false);
            this.add(handButtonArr.get(i));

            chosenButtonArr.add(new JButton("Choose this card"));
            chosenButtonArr.get(i).setBounds(280 + 200*i, 385, 160, 20);
            chosenButtonArr.get(i).setVisible(false);
            this.add(chosenButtonArr.get(i));
        }

        /*
        Button Action Listener
         */
        handButtonArr.get(0).addActionListener(e->{
            System.out.println(0 + "pressed");
            try{
                Message msg = new ChoseFromHandMessage(0);
                queue.put(msg);
            } catch (InterruptedException exception) {
                //do nothing
            }
        });

        handButtonArr.get(1).addActionListener(e->{
            System.out.println(1 + "pressed");
            try{
                Message msg = new ChoseFromHandMessage(1);
                queue.put(msg);
            } catch (InterruptedException exception) {
                //do nothing
            }
        });
        handButtonArr.get(2).addActionListener(e->{
            System.out.println(2 + "pressed");
            try{
                Message msg = new ChoseFromHandMessage(2);
                queue.put(msg);
            } catch (InterruptedException exception) {
                //do nothing
            }
        });
        handButtonArr.get(3).addActionListener(e->{
            System.out.println(3 + "pressed");
            try{
                Message msg = new ChoseFromHandMessage(3);
                queue.put(msg);
            } catch (InterruptedException exception) {
                //do nothing
            }
        });
        handButtonArr.get(4).addActionListener(e->{
            System.out.println(4 + "pressed");
            try{
                Message msg = new ChoseFromHandMessage(4);
                queue.put(msg);
            } catch (InterruptedException exception) {
                //do nothing
            }
        });

        chosenButtonArr.get(0).addActionListener(e->{
            try{
                Message msg = new CzarChoseCardMessage(0);
                queue.put(msg);
            } catch (InterruptedException exception) {
                //do nothing
            }
        });

        chosenButtonArr.get(1).addActionListener(e->{
            try{
                Message msg = new CzarChoseCardMessage(1);
                queue.put(msg);
            } catch (InterruptedException exception) {
                //do nothing
            }
        });

        chosenButtonArr.get(2).addActionListener(e->{
            try{
                Message msg = new CzarChoseCardMessage(2);
                queue.put(msg);
            } catch (InterruptedException exception) {
                //do nothing
            }
        });

        chosenButtonArr.get(3).addActionListener(e->{
            try{
                Message msg = new CzarChoseCardMessage(3);
                queue.put(msg);
            } catch (InterruptedException exception) {
                //do nothing
            }
        });

        chosenButtonArr.get(4).addActionListener(e->{
            try{
                Message msg = new CzarChoseCardMessage(4);
                queue.put(msg);
            } catch (InterruptedException exception) {
                //do nothing
            }
        });

        /*
         *Show Chosen
         * */
        for(int i = 0; i < 5; i++) {
            chosenCardsTextAreaArr.add(new JTextArea("This is one of the Card's description, this chosen card test"));
            chosenCardsTextAreaArr.get(i).setWrapStyleWord(true);
            chosenCardsTextAreaArr.get(i).setLineWrap(true);
            chosenCardsTextAreaArr.get(i).setEditable(false);
            chosenCardsTextAreaArr.get(i).setBackground(Color.white);
            font = new Font("SansSerif", Font.BOLD, 15);
            chosenCardsTextAreaArr.get(i).setFont(font);
            chosenCardsTextAreaArr.get(i).setForeground(Color.black);
            //40 pixels from black card
            chosenCardsTextAreaArr.get(i).setBounds(270 + 200*i, 130, 180, 250);
            chosenCardsTextAreaArr.get(i).setVisible(false);
            this.add(chosenCardsTextAreaArr.get(i));
        }


        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //methods to be used in controller

    /** Update Player's information in GUI
     *
     * @param nameList the list of Player's name
     * @param score    the list of Player's score
     */
    public void updatePlayersInView(ArrayList<String> nameList, ArrayList<String> score){
        playerNameTextField.setText("");
        String scoreBoardStr = "------Score Board------\n";
        for(int i = 0; i < nameList.size(); i++){
            scoreBoardStr += nameList.get(i) + " | " + score.get(i) + "\n";
            scoreBoardStr += "---------------------\n";
        }
        playerScoreBoard.setText(scoreBoardStr);
    }

    /** Disable the Add Player Button in GUI
     *
     */
    public void disableAddPlayerInView() { addPlayerButton.setEnabled(false);  }

    /** Enable the Add Player Button in GUI
     *
     */
    public void enableAddPlayerInView() { addPlayerButton.setEnabled(true);}

    /** Disable the Remove Player Button in GUI
     *
     */
    public void disableRemovePlayerInView() { removePlayerButton.setEnabled(false);}

    /** Disable the Load File/Deck Button in GUI
     *
     */
    public void disableLoadFileButtonInView() { loadFileButton.setEnabled(false); fileDirTextField.setEnabled(false);}

    /** Enable the Start Button in GUI
     *
     * @param enable to set the start button to be clickable
     */
    public void enableStartGameInView(boolean enable) { startGameButton.setEnabled(enable); }

    /** Enable the game's information in GUI
     *
     * @param currentPlayer Name of the current player
     * @param currentCzarName name of the current czar
     * @param blackCardDescription the black card's information
     * @param handArr the player's hand
     */
    public void startAGameInView(String currentPlayer, String currentCzarName, String blackCardDescription, ArrayList<String> handArr){
        System.out.println("started");
        currCzarTextArea.setText(currentCzarName+"\nis the Czar");
        currCzarTextArea.setVisible(true);
        blackCardTextArea.setText(blackCardDescription);
        blackCardTextArea.setVisible(true);
        nextPlayerButton.setVisible(true);
        curPlayer.setVisible(true);

        startGameButton.setEnabled(false);
        addPlayerButton.setEnabled(false);
        removePlayerButton.setEnabled(false);
        playerNameTextField.setEnabled(false);
        fileDirTextField.setEnabled(false);
        loadFileButton.setEnabled(false);

        updateCurPlayerInView(currentPlayer);
        updateHandInView(handArr);
    }

    /** Update the player's hand in GUI
     *
     * @param handArr the player's hand
     */
    public void updateHandInView(ArrayList<String> handArr){
        for(int i = 0; i < 5; i++) {
            handTextAreaArr.get(i).setVisible(false);
            handButtonArr.get(i).setVisible(false);
        }

        for(int i = 0; i < handArr.size(); i++){
            handTextAreaArr.get(i).setText(handArr.get(i));
            handTextAreaArr.get(i).setVisible(true);
            handButtonArr.get(i).setVisible(true);
        }
    }

    /** Update the Black Card in GUI
     *
     * @param cardDescription the description of the black card
     */
    public void updateBlackCardInView(String cardDescription){ blackCardTextArea.setText(cardDescription);}

    /** Update which player is the Czar in GUI
     *
     * @param name the name of the Czar
     */
    public void updateCzarInView(String name){currCzarTextArea.setText(name+"\nis the Czar");}

    /** Update which player's turn it is in GUI
     *
     * @param name the name of the Player
     */
    public void updateCurPlayerInView(String name) { curPlayer.setText(name + "'s turn");}

    /** Update the chosen card Area in the GUI
     *
     * @param chosenArr the chosen cards that the player pick
     */
    public void updateChosenCardInView(ArrayList<String> chosenArr){
        System.out.println(chosenArr.toString());
        for(int i = 0; i < chosenCardsTextAreaArr.size(); i++)
            chosenCardsTextAreaArr.get(i).setVisible(false);

        for(int i = 0; i < chosenArr.size(); i++){
            chosenCardsTextAreaArr.get(i).setText(chosenArr.get(i));
            chosenCardsTextAreaArr.get(i).setVisible(true);
        }
    }

    /** Disable the Player's hand button in GUI
     *
     */
    public void disableHandButtonsInView(){
        for(int i = 0; i < 5; i++){
            handButtonArr.get(i).setEnabled(false);
        }
    }

    /** Enable the Player's hand button in GUI
     *
     */
    public void enableHandButtonsInView(){
        for(int i = 0; i < 5; i++){
            handButtonArr.get(i).setEnabled(true);
        }
    }

    /** Hide the Chosen Card Button in GUI
     *
     */
    public void hideChosenButtonsInView(){
        for(int i = 0; i < 5; i++){
            chosenButtonArr.get(i).setVisible(false);
        }
    }

    /** Show the Chosen Card Button in GUI
     *
     */
    public void showChosenButtonsInView(){
        for(int i = 0; i < 5; i++){
            if(chosenCardsTextAreaArr.get(i).isVisible())
                chosenButtonArr.get(i).setVisible(true);
        }
    }

    /** Enable the Next Player Button in GUI
     *
     */
    public void enableDoNextPlayerInView(){nextPlayerButton.setEnabled(true);}

    /** Update the Winning Player's in GUI at czar's area
     *
     * @param winnerName The player that is the winner
     */
    public void endGameInView(String winnerName){
        currCzarTextArea.setText("Winner is\n" + winnerName);
    }
}

/** Responsible for limiting the text field to 15 chracter
 *
 */
class JTextFieldLimit extends PlainDocument {
    private int limit;

    /**Respresent the Character limit
     *
     * @param limit the character's limit
     */
    JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null)
            return;
        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}
