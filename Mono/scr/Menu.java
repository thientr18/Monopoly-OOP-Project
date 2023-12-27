import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Game{
    JFrame Monopoly;
    Container con;
    JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel;
    JLabel titleNameLabel;
    Font titleFon = new Font("Arial", Font.PLAIN, 60);
    Font normalFon = new Font("Arial", Font.PLAIN, 30);
    Font gameFon = new Font("Arial", Font.PLAIN, 65);
    JButton startButton, choice1, choice2;
    JTextArea mainTextArea;

    TitleScreenHandler tsHandler = new TitleScreenHandler();

   
    public static void main(String[] args) {
        new Game();
    }

    public Game(){
        Monopoly = new JFrame();
        Monopoly.setSize(800,600);
        Monopoly.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Monopoly.getContentPane().setBackground(Color.BLACK);
        Monopoly.setLayout(null);
        Monopoly.setVisible(true);
        con = Monopoly.getContentPane();

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100,200,600,150);
        titleNamePanel.setBackground(Color.BLACK);
        titleNameLabel = new JLabel("MONOPOLY");
        titleNameLabel.setForeground(Color.WHITE);
        titleNameLabel.setFont(titleFon);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 300, 200, 100);
        startButtonPanel.setBackground(Color.BLACK);

        startButton = new JButton("START");
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(normalFon);
        startButton.addActionListener(tsHandler);

       

        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);

        con.add(titleNamePanel);
        con.add(startButtonPanel);
    }
    public void endGame(){
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(150, 100, 500, 250);
        mainTextPanel.setBackground(Color.BLACK);
        con.add(mainTextPanel);

        mainTextArea = new JTextArea("YOU WIN GAME");
        mainTextArea.setBounds(150, 300, 500, 250);
        mainTextArea.setBackground(Color.BLACK);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(gameFon);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(250, 350, 300, 150);
        choiceButtonPanel.setBackground(Color.BLACK);
        choiceButtonPanel.setLayout(new GridLayout(2,1));
        con.add(choiceButtonPanel);

        choice1 = new JButton("EXIT");
        choice1.setBackground(Color.BLACK);
        choice1.setForeground(Color.WHITE);
        choice1.setFont(normalFon);
        choiceButtonPanel.add(choice1);
        choice2 = new JButton("RESET GAME");
        choice2.setBackground(Color.BLACK);
        choice2.setForeground(Color.WHITE);
        choice2.setFont(normalFon);
        choiceButtonPanel.add(choice2);
         }
         public class TitleScreenHandler implements ActionListener{
            public void actionPerformed(ActionEvent event){
                endGame();

            }
         }
    }
    
    
