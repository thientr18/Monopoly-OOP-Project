package source;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Square extends JPanel{

    int number;
    private String name;
    String description;
    JLabel nameLabel;
    static int totalSquares = 0;
    private int price;
    private int rentPrice;

    public String getName(){
        return name;
    }

    public void setPrice(int price){
        this.price = price;
    }
    public int getPrice(){
        return price;
    }

    public void setRentPrice(int rentPrice){
        this.rentPrice = rentPrice;
    }
    public int getRentPrice(){
        return rentPrice;
    }

    public Square(int x, int y, int width, int height, String labelString, int rotationDegrees){
        number = totalSquares;
        totalSquares++;
        setBorder(new LineBorder(new Color(0, 0, 0)));
        setBounds(x, y, width, height);
        name = labelString;
        this.setLayout(null);

        if(rotationDegrees == 0) {
            nameLabel = new JLabel(labelString);
            nameLabel.setFont(new Font("Quicksand", Font.PLAIN, 10));
            nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            nameLabel.setBounds(0, 20, this.getWidth(), 20);
            this.add(nameLabel);
        }
        else {
            nameLabel = new JLabel(labelString){
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D)g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    AffineTransform aT = g2.getTransform();
                    Shape oldshape = g2.getClip();
                    double x = getWidth()/2.0;
                    double y = getHeight()/2.0;
                    aT.rotate(Math.toRadians(rotationDegrees), x, y);
                    g2.setTransform(aT);
                    g2.setClip(oldshape);
                    super.paintComponent(g);
                }
            };
            if(rotationDegrees == 90){
                nameLabel.setBounds(10, 0, this.getWidth(), this.getHeight());
            }
            if(rotationDegrees == -90){
                nameLabel.setBounds(-5, 0, this.getWidth(), this.getHeight());
            }
            if(rotationDegrees == 180){
                nameLabel.setBounds(-5, 10, this.getWidth(), this.getHeight());
            }
            if(rotationDegrees == -135 || rotationDegrees == 135 || rotationDegrees == -45 || rotationDegrees == 45){
                nameLabel.setBounds(-5, 0, this.getWidth(), this.getHeight());
            }
            nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            nameLabel.setFont(new Font("Quicksand", Font.PLAIN, 10));
            

            this.add(nameLabel);
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // For the TOP of the BOARD
        if(this.number == 1 || this.number == 2){
            g.drawRect(0, this.getHeight()- 20, this.getWidth(), 20);
            g.setColor(new Color(234, 242, 68));
            g.fillRect(0, this.getHeight()- 20, this.getWidth(), 20);
        }
        if(this.number == 5 || this.number == 6){
            g.drawRect(0, this.getHeight()- 20, this.getWidth(), 20);
            g.setColor(new Color(32, 189, 35));
            g.fillRect(0, this.getHeight()- 20, this.getWidth(), 20);
        }
        // For the RIGHT of the BOARD
        if(this.number == 8 || this.number == 9){
            g.drawRect(0, 0, 20, this.getHeight());
            g.setColor(new Color(66, 245, 245));
            g.fillRect(0, 0, 20, this.getHeight());
        }
        if(this.number == 12 || this.number == 13){
            g.drawRect(0, 0, 20, this.getHeight());
            g.setColor(new Color(28, 128, 106));
            g.fillRect(0, 0, 20, this.getHeight());
        }
        // For the BOTTOM of the BOARD
        if (this.number == 15 || this.number == 16){
            g.drawRect(0, 0, this.getWidth(), 20);
            g.setColor(new Color(14, 64, 115));
            g.fillRect(0, 0, this.getWidth(), 20);
        }
        if(this.number == 19 || this.number == 20){
            g.drawRect(0, 0, this.getWidth(), 20);
            g.setColor(new Color(129, 66, 245));
            g.fillRect(0, 0, this.getWidth(), 20);
        }
        // For the LEFT of the BOARD
        if(this.number == 22 || this.number == 23){
            g.drawRect(this.getWidth()-20, 0, 20, this.getHeight());
            g.setColor(new Color(245, 66, 158));
            g.fillRect(this.getWidth()-20, 0, 20, this.getHeight());
        }
        if(this.number == 26 || this.number == 27){
            g.drawRect(this.getWidth()-20, 0, 20, this.getHeight());
            g.setColor(new Color(245, 96, 66));
            g.fillRect(this.getWidth()-20, 0, 20, this.getHeight());
        }
    }
}