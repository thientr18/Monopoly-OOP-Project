package source;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Dice extends JPanel {
    Random dice = new Random();
    int faceDice = 1;

    public Dice (int x, int y, int width, int height){
        setBorder(new LineBorder(new Color(0, 0, 0)));
        setBounds(x, y, width, height);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        if(faceDice == 1){
            g.fillOval(getWidth()/2 - 5/2, getHeight()/2 - 5/2 , 5, 5);
        }
        else if(faceDice == 2) {
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
        }
        else if(faceDice == 3){
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 - 5/2, getHeight()/2 - 5/2 , 5, 5);
        }
        else if(faceDice == 4){
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
        }
        else if(faceDice == 5){
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 - 5/2, getHeight()/2 - 5/2 , 5, 5);
        }
        else if(faceDice == 6){
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 - 15, getHeight()/2 - 5/2 , 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 5/2 , 5, 5);
        }
    }

    public void rollDice(){
        faceDice = dice.nextInt(6)+ 1;
        repaint();
    }

    public int getFaceDice(){
        return faceDice;
    }

    public Dice(int x, int y, int width, int height, String labelString){
        setBorder(new LineBorder(new Color(0, 0, 0)));
        setBounds(x, y, width, height);
    }
}
