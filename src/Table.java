/*
Henry Fan's draft of class Table

Session 1: 10/20/21 from 4pm-6pm

Notes from Maria's feedback from Milestone 2
class Table that manages number of players (and may be other game info) but should not be concerned
about how to display it. we should have another class that display all or part of your game in View package.

Objectives for Table for future:
creates and displays table
displays center cards
managers number of players

Objectives for Milestone 3:
display opening sign in to our game

Open Questions:
Should I be using Class CardLayout ? https://docs.oracle.com/javase/7/docs/api/java/awt/CardLayout.html

Research:
Java Swing CardLayout Class : https://www.tutorialspoint.com/swing/swing_cardlayout.htm *code below credit from here
as milestone 3 is primarily setting up Jira workflow, and checking branching git fluency in team

Displaying cards: http://www.fredosaurus.com/notes-java/examples/graphics/cardDemo/cardDemo.html


*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Table {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JLabel msglabel;

    public Table(){
        prepareGUI();
    }
    public static void main(String[] args){
        Table swingLayoutDemo = new Table();
        swingLayoutDemo.showCardLayoutDemo();
    }
    private void prepareGUI(){
        mainFrame = new JFrame("404 Bits Not Found");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new GridLayout(3, 1));

        headerLabel = new JLabel("",JLabel.CENTER );
        statusLabel = new JLabel("",JLabel.CENTER);
        statusLabel.setSize(350,100);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }
    private void showCardLayoutDemo(){
        headerLabel.setText("Welcome to Cards Against Shallow Learning");

        final JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);
        panel.setSize(300,300);

        CardLayout layout = new CardLayout();
        layout.setHgap(10);
        layout.setVgap(10);
        panel.setLayout(layout);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(new JButton("OK"));
        buttonPanel.add(new JButton("Cancel"));

        JPanel textBoxPanel = new JPanel(new FlowLayout());
        textBoxPanel.add(new JLabel("Name:"));
        textBoxPanel.add(new JTextField(20));

        panel.add("Button", buttonPanel);
        panel.add("Default Deck", textBoxPanel);
        final DefaultComboBoxModel panelName = new DefaultComboBoxModel();

        panelName.addElement("Default Decks about Learning Science");
        panelName.addElement("Custom Question and Hand Cards that you create!");
        final JComboBox listCombo = new JComboBox(panelName);

        listCombo.setSelectedIndex(0);
        JScrollPane listComboScrollPane = new JScrollPane(listCombo);
        JButton showButton = new JButton("Begin");

        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String data = "";
                if (listCombo.getSelectedIndex() != -1) {
                    CardLayout cardLayout = (CardLayout)(panel.getLayout());
                    cardLayout.show(panel,
                            (String)listCombo.getItemAt(listCombo.getSelectedIndex()));
                }
                statusLabel.setText(data);
            }
        });
        controlPanel.add(listComboScrollPane);
        controlPanel.add(showButton);
        controlPanel.add(panel);
        mainFrame.setVisible(true);
    }
}

