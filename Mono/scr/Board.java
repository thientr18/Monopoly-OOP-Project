package source;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Board extends JPanel {
    private ArrayList<Square> allSquares = new ArrayList<Square>();
    private ArrayList<Square> unableBuySquares = new ArrayList<Square>();

    public ArrayList<Square> getUnableBuySquares(){
        return unableBuySquares;
    }

    public ArrayList<Square> getAllSquare(){
        return allSquares;
    }

    public Square getSquareIndex(int location) {
        return allSquares.get(location);
    }

    
    public Board(int x, int y, int width, int height){
        setBorder(new LineBorder(new Color(7, 18, 36)));
        setBounds(x, y, 648, 648);
        this.setLayout(null);
        initializeSquare();
    }
    public void initializeSquare(){
        String[] squareNames = {
            "Start", 
            "Ha Giang",
            "Cao Bang",
            "Community", 
            "Seaport 1", 
            "Yen Bai",
            "Hai Phong", 
            "Visit Prison",

            "Nghe An",
            "Ha Tinh",
            "Chance", 
            "Seaport 2", 
            "Quang Tri",
            "Quang Binh", 
            "Air Plane", 

            "Hue", 
            "Quang Nam", 
            "Community",
            "Seaport 3", 
            "Vung Tau",
            "Tay Ninh", 
            "Go to Jail",

            "Da Nang",
            "Can Tho",
            "Chance",
            "Seaport 4", 
            "Ha Noi", 
            "HCM City"
        };

        //Top Board

        Square st0 = new Square(4,4,80, 80, squareNames[0], -45);
        this.add(st0);
        allSquares.add(st0);
        unableBuySquares.add(st0);

        Square st1 = new Square(84, 4, 80, 80, squareNames[1], 180);
        this.add(st1);
        allSquares.add(st1);

        Square st2 = new Square(164, 4, 80, 80, squareNames[2], 180);
        this.add(st2);
        allSquares.add(st2);

        Square st3 = new Square(244,4,80, 80, squareNames[3], 180);
        this.add(st3);
        allSquares.add(st3);

        Square st4 = new Square(324, 4, 80, 80, squareNames[4], 180);
        this.add(st4);
        allSquares.add(st4);
        unableBuySquares.add(st4);

        Square st5 = new Square(404, 4, 80, 80, squareNames[5], 180);
        this.add(st5);
        allSquares.add(st5);

        Square st6 = new Square(484, 4, 80, 80, squareNames[6], 180);
        this.add(st6);
        allSquares.add(st6);

        Square st7 = new Square(564, 4, 80, 80, squareNames[7], 45);
        this.add(st7);
        allSquares.add(st7);
        unableBuySquares.add(st7);

        //Right Board

        Square sr0 = new Square(564, 84, 80, 80, squareNames[8], -90);
        this.add(sr0);
        allSquares.add(sr0);

        Square sr1 = new Square(564, 164, 80, 80, squareNames[9], -90);
        this.add(sr1);
        allSquares.add(sr1);

        Square sr2 = new Square(564, 244, 80, 80, squareNames[10], -90);
        this.add(sr2);
        allSquares.add(sr2);

        Square sr3 = new Square(564, 324, 80, 80, squareNames[11], -90);
        this.add(sr3);
        allSquares.add(sr3);
        unableBuySquares.add(sr3);

        Square sr4 = new Square(564, 404, 80, 80, squareNames[12], -90);
        this.add(sr4);
        allSquares.add(sr4);

        Square sr5 = new Square(564, 484, 80, 80, squareNames[13], -90);
        this.add(sr5);
        allSquares.add(sr5);

        Square sr6 = new Square(564, 564, 80, 80, squareNames[14], -45);
        this.add(sr6);
        allSquares.add(sr6);
        unableBuySquares.add(sr6);

        //Bottom Board

        Square sb0 = new Square(484, 564, 80, 80, squareNames[15], 0);
        this.add(sb0);
        allSquares.add(sb0);

        Square sb1 = new Square(404, 564, 80, 80, squareNames[16], 0);
        this.add(sb1);
        allSquares.add(sb1);

        Square sb2 = new Square(324, 564, 80, 80, squareNames[17], 0);
        this.add(sb2);
        allSquares.add(sb2);

        Square sb3 = new Square(244, 564, 80, 80, squareNames[18], 0);
        this.add(sb3);
        allSquares.add(sb3);
        unableBuySquares.add(sb3);

        Square sb4 = new Square(164, 564, 80, 80, squareNames[19], 0);
        this.add(sb4);
        allSquares.add(sb4);

        Square sb5 = new Square(84, 564, 80, 80, squareNames[20], 0);
        this.add(sb5);
        allSquares.add(sb5);

        Square sb6 = new Square(4, 564, 80, 80, squareNames[21], 45);
        this.add(sb6);
        allSquares.add(sb6);
        unableBuySquares.add(sb6);

        //Left Board

        Square sl0 = new Square(4, 484, 80, 80, squareNames[22], 90);
        this.add(sl0);
        allSquares.add(sl0);

        Square sl1 = new Square(4, 404, 80, 80, squareNames[23], 90);
        this.add(sl1);
        allSquares.add(sl1);

        Square sl2 = new Square(4, 324, 80, 80, squareNames[24], 90);
        this.add(sl2);
        allSquares.add(sl2);

        Square sl3 = new Square(4, 244, 80, 80, squareNames[25], 90);
        this.add(sl3);
        allSquares.add(sl3);
        unableBuySquares.add(sl3);

        Square sl4 = new Square(4, 164, 80, 80, squareNames[26], 90);
        this.add(sl4);
        allSquares.add(sl4);

        Square sl5 = new Square(4, 84, 80, 80, squareNames[27], 90);
        this.add(sl5);
        allSquares.add(sl5);

        //Set Price for the Top Board
        st1.setPrice(100);
        st1.setRentPrice(10);

        st2.setPrice(100);
        st2.setRentPrice(10);

        st5.setPrice(100);
        st5.setRentPrice(10);

        st6.setPrice(140);
        st6.setRentPrice(14);


        //Set Price for the Right Board

        sr0.setPrice(300);
        sr0.setRentPrice(30);

        sr1.setPrice(160);
        sr1.setRentPrice(16);

        sr4.setPrice(160);
        sr4.setRentPrice(16);

        sr5.setPrice(160);
        sr5.setRentPrice(16);

        //Set Price for the Bottom Board

        sb0.setPrice(180);
        sb0.setRentPrice(18);

        sb1.setPrice(180);
        sb1.setRentPrice(18);

        sb4.setPrice(200);
        sb4.setRentPrice(20);

        sb5.setPrice(200);
        sb5.setRentPrice(20);

        // Set Price for the Left Board

        sl0.setPrice(220);
        sl0.setRentPrice(22);

        sl1.setPrice(300);
        sl1.setRentPrice(30);

        sl4.setPrice(320);
        sl4.setRentPrice(32);

        sl5.setPrice(330);
        sl5.setRentPrice(33);
        
        JLabel labelMonopoly = new JLabel("MONOPOLY"){
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D)g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                AffineTransform aT = g2.getTransform();
                Shape oldShape = g2.getClip();
                double x = getWidth()/2.0;
                double y = getHeight()/2.0;
                aT.rotate(Math.toRadians(0), x, y);
                g2.setTransform(aT);
                g2.setClip(oldShape);
                super.paintComponent(g);
            }
        };
        labelMonopoly.setForeground(Color.WHITE);
        labelMonopoly.setBackground(Color.RED);
        labelMonopoly.setOpaque(true);
        labelMonopoly.setHorizontalAlignment(SwingConstants.CENTER);
        labelMonopoly.setBounds(194,297,260,54);
        this.add(labelMonopoly);

    }
}
