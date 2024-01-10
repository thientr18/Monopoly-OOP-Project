import java.awt.Color;
import java.awt.Font;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Menu extends JFrame {
   
    public static void main(String[] args) {

        ImageIcon image1 = new ImageIcon("15.png");
        ImageIcon image2 = new ImageIcon("Button.png");

        Border border = BorderFactory.createLineBorder(Color.YELLOW,3);

        JLabel label = new JLabel();
        label.setIcon(image1);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("MV Boil",Font.PLAIN,30));
        label.setIconTextGap(-100);
        label.setBorder(border);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        
        
        JPanel startPanel = new JPanel();
        startPanel.setBackground(Color.WHITE);
        startPanel.setBounds(700,370,300,60);
        JPanel rulePanel = new JPanel();
        rulePanel.setBackground(Color.WHITE);
        rulePanel.setBounds(200,400,50,60);
        JPanel restartPanel = new JPanel();
        restartPanel.setBackground(Color.WHITE);
        restartPanel.setBounds(700,450,300,60);
        JPanel exitPanel = new JPanel();
        exitPanel.setBackground(Color.WHITE);
        exitPanel.setBounds(700,530,300,60);
        
        JButton startButton = new JButton();
        startButton.setBounds(700,370,300,60);
        startButton.setText("START GAME");
        startButton.setFocusable(false);
        startButton.setIcon(image2);
        startButton.setHorizontalTextPosition(JButton.LEFT);
        startButton.setVerticalTextPosition(JButton.CENTER);
        startButton.setFont(new Font("Comic Sans",Font.BOLD,34));
        startButton.setIconTextGap(-5);
        startButton.setForeground(Color.BLACK);
        startButton.setBackground(Color.YELLOW);
        startButton.setBorder(BorderFactory.createEtchedBorder());

        JButton ruleButton = new JButton();
        ruleButton.setBounds(700,450,300,60);
        ruleButton.setText("GAME RULE");
        ruleButton.setFocusable(false);
        ruleButton.setIcon(image2);
        ruleButton.setHorizontalTextPosition(JButton.LEFT);
        ruleButton.setVerticalTextPosition(JButton.CENTER);
        ruleButton.setFont(new Font("Comic Sans",Font.BOLD,38));
        ruleButton.setIconTextGap(-5);
        ruleButton.setForeground(Color.BLACK);
        ruleButton.setBackground(Color.YELLOW);
        ruleButton.setBorder(BorderFactory.createEtchedBorder());

        JButton exitButton = new JButton();
        exitButton.setBounds(700,530,400,60);
        exitButton.setText("EXIT GAME");
        exitButton.setFocusable(false);
        exitButton.setIcon(image2);
        exitButton.setHorizontalTextPosition(JButton.LEFT);
        exitButton.setVerticalTextPosition(JButton.CENTER);
        exitButton.setFont(new Font("Comic Sans",Font.BOLD,41));
        exitButton.setIconTextGap(0);
        exitButton.setForeground(Color.BLACK);
        exitButton.setBackground(Color.YELLOW);
        exitButton.setBorder(BorderFactory.createEtchedBorder());

        

        
        JFrame frame = new JFrame();
        frame.setTitle("MONOPOLY GAME");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1100,720);
        frame.setVisible(true);
        frame.add(label);
        frame.add(startPanel);
        frame.add(exitPanel);
        frame.add(restartPanel);
        frame.add(rulePanel);

        startButton.add(label);
        startPanel.add(label);
        exitPanel.add(label);
        restartPanel.add(label);
        rulePanel.add(label);
       
        startPanel.add(startButton);
        restartPanel.add(ruleButton);
        exitPanel.add(exitButton);
        
        
    }
   
    
}
