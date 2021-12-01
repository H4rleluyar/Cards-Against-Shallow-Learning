package MVCstructure.view;

import MVCstructure.controller.*;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class View extends JFrame {
    BlockingQueue<Message> queue;

    //global definition for all components
    JLabel titleLabel; //The title of the Game
    JLabel playerLabel; //player name
    JLabel fileDirLabel;
    JLabel score; //score for the player

    JTextField playerNameTextField;
    JTextField fileDirTextField;

    JButton addPlayerButton;
    JButton loadFileButton;
    JButton startGameButton;
    JButton addScoreButton; // button to add score
    ArrayList<JButton> handButtonArr;


    JTextArea playerScoreBoard;  //the player's scoreBoard
    JTextArea currCzarTextArea; //display who's the current czar
    JTextArea blackCardTextArea; //display black Card
    ArrayList<JTextArea> chosenCardsTextAreaArr;
    ArrayList<JTextArea> handTextAreaArr;

    Font font;

    public View(BlockingQueue<Message> queue) {
        this.queue = queue;
        this.setSize(1280, 720);
        this.setLayout(null);
        handButtonArr = new ArrayList<>();
        chosenCardsTextAreaArr = new ArrayList<>();
        handTextAreaArr = new ArrayList<>();

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
        playerLabel = new JLabel("Enter player's name:");
        playerLabel.setBounds(280, 60, 150, 20);
        this.add(playerLabel);

        playerNameTextField = new JTextField();
        playerNameTextField.setBounds(410, 60, 160, 20);
        playerNameTextField.setDocument(new JTextFieldLimit(15));
        this.add(playerNameTextField);

        addPlayerButton = new JButton("Add Player");
        addPlayerButton.setBounds(580, 60, 100, 20);
        this.add(addPlayerButton);

        //addPlayerButton function here
        addPlayerButton.addActionListener(e -> {
            String name = playerNameTextField.getText(); //get the name base on whatever was enter in textfield

            try {
                Message msg = new AddPlayerMessage(name);
                queue.put(msg);
            } catch (InterruptedException exception) {
                //do nothing
            }
        });

        fileDirLabel = new JLabel("Enter File directory:");
        fileDirLabel.setBounds(280, 90, 150, 20);
        this.add(fileDirLabel);

        fileDirTextField = new JTextField();
        fileDirTextField.setBounds(410, 90, 160, 20);
        this.add(fileDirTextField);

        loadFileButton = new JButton("Load file");
        loadFileButton.setBounds(580, 90, 100, 20);
        this.add(loadFileButton);
        loadFileButton.addActionListener(e -> {
            try {
                Message msg = new LoadFileMessage(fileDirTextField.getText());
                queue.put(msg);
            } catch (InterruptedException exception) {
                //do nothing
            }
        });

        startGameButton = new JButton("Start Game");
        startGameButton.setBounds(700, 60, 100, 50);
        startGameButton.setEnabled(false);
        this.add(startGameButton);
        startGameButton.addActionListener(e -> {
            try {
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
        playerScoreBoard.setBounds(20, 410, 220, 150);
        this.add(playerScoreBoard);

        addScoreButton = new JButton("AddScoreHere"); //not sure why button isn't working
        addScoreButton.addActionListener(e -> {
            JLabel score = new JLabel("0");
            int scoreHere = Integer.parseInt(score.getText());
            try {
                Message msg = new AddScoreMessage(scoreHere);
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
        for (int i = 0; i < 5; i++) {
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
            handButtonArr.get(i).setBounds(280 + 200 * i, 650, 160, 20);
            handButtonArr.get(i).setVisible(false);
            this.add(handButtonArr.get(i));
        }

        /*
        Button Action Listener
         */
        handButtonArr.get(0).addActionListener(e -> {
            System.out.println(0 + "pressed");
            try {
                Message msg = new ChoseFromHandMessage(0);
                queue.put(msg);
            } catch (InterruptedException exception) {
                //do nothing
            }
        });

        handButtonArr.get(1).addActionListener(e -> {
            System.out.println(1 + "pressed");
            try {
                Message msg = new ChoseFromHandMessage(1);
                queue.put(msg);
            } catch (InterruptedException exception) {
                //do nothing
            }
        });
        handButtonArr.get(2).addActionListener(e -> {
            System.out.println(2 + "pressed");
            try {
                Message msg = new ChoseFromHandMessage(2);
                queue.put(msg);
            } catch (InterruptedException exception) {
                //do nothing
            }
        });
        handButtonArr.get(3).addActionListener(e -> {
            System.out.println(3 + "pressed");
            try {
                Message msg = new ChoseFromHandMessage(3);
                queue.put(msg);
            } catch (InterruptedException exception) {
                //do nothing
            }
        });
        handButtonArr.get(4).addActionListener(e -> {
            System.out.println(4 + "pressed");
            try {
                Message msg = new ChoseFromHandMessage(4);
                queue.put(msg);
            } catch (InterruptedException exception) {
                //do nothing
            }
        });

        /*
         *Show Chosen
         * */
        for (int i = 0; i < 5; i++) {
            chosenCardsTextAreaArr.add(new JTextArea("This is one of the Card's description, this chosen card test"));
            chosenCardsTextAreaArr.get(i).setWrapStyleWord(true);
            chosenCardsTextAreaArr.get(i).setLineWrap(true);
            chosenCardsTextAreaArr.get(i).setEditable(false);
            chosenCardsTextAreaArr.get(i).setBackground(Color.white);
            font = new Font("SansSerif", Font.BOLD, 15);
            chosenCardsTextAreaArr.get(i).setFont(font);
            chosenCardsTextAreaArr.get(i).setForeground(Color.black);
            //40 pixels from black card
            chosenCardsTextAreaArr.get(i).setBounds(270 + 200 * i, 130, 180, 250);
            chosenCardsTextAreaArr.get(i).setVisible(false);
            this.add(chosenCardsTextAreaArr.get(i));
        }


        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void updatePlayersInView(ArrayList<String> nameList) {
        playerNameTextField.setText("");
        String scoreBoardStr = "";
        for (String name : nameList) {
            scoreBoardStr += name + "\n";
            scoreBoardStr += "--------------------\n";
        }
        playerScoreBoard.setText(scoreBoardStr);
    }

    public void disableAddPlayerInView() {
        addPlayerButton.setEnabled(false);
        playerNameTextField.setEnabled(false);
    }

    public void disableLoadFileButtonInView() {
        loadFileButton.setEnabled(false);
        fileDirTextField.setEnabled(false);
    }

    public void enableStartGameInView() {
        startGameButton.setEnabled(true);
    }

    public void startAGameInView(String currentCzarName, String blackCardDescription, ArrayList<String> handArr) {
        System.out.println("started");
        currCzarTextArea.setText(currentCzarName + "\nis the Czar");
        currCzarTextArea.setVisible(true);
        blackCardTextArea.setText(blackCardDescription);
        blackCardTextArea.setVisible(true);
        updateHandInView(handArr);
    }

    public void updateHandInView(ArrayList<String> handArr) {
        for (int i = 0; i < 5; i++) {
            handTextAreaArr.get(i).setVisible(false);
            handButtonArr.get(i).setVisible(false);
        }

        for (int i = 0; i < handArr.size(); i++) {
            handTextAreaArr.get(i).setText(handArr.get(i));
            handTextAreaArr.get(i).setVisible(true);
            handButtonArr.get(i).setVisible(true);
        }
    }

    public void updateChosenCardInView(ArrayList<String> chosenArr) {
        for (int i = 0; i < chosenCardsTextAreaArr.size(); i++)
            chosenCardsTextAreaArr.get(i).setVisible(false);

        for (int i = 0; i < chosenArr.size(); i++) {
            chosenCardsTextAreaArr.get(i).setText(chosenArr.get(i));
            chosenCardsTextAreaArr.get(i).setVisible(true);
        }
    }

    public void disableHandButtonsInView() {
        for (int i = 0; i < 5; i++) {
            handButtonArr.get(i).setEnabled(false);
        }
    }

    public void addScoreMessage(){

    }


}

class JTextFieldLimit extends PlainDocument {
    private int limit;
    JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }
    JTextFieldLimit(int limit, boolean upper) {
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
