package src;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class Square extends JPanel{

    private int number;
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
        name = labelString;
        this.setLayout(null);
    }
}
