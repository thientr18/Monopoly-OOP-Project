package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Player extends JPanel {
    
    private int playerNumber;
    JLabel lblPlayerNumber;
    static int totalPlayers = 0;
    static HashMap<Integer, Integer> ledger = new HashMap<Integer, Integer>();

    private int currentSquareNumber = 0; // Player's current lcation on (0-27). Initially zero
    private ArrayList<Integer> titilleDeeds = new ArrayList<Integer>(); // squares that the player owned
    private int wallet = 500; // Initial money

    
    public ArrayList<Integer> getTitleDeeds() {
        return titilleDeeds;
    }

    public int getWallet () {
        return wallet;
    }

    public void setWallet(int newWallet){
        this.wallet = newWallet;
    }

    public void withdrawFromWallet(int withdrawAmount) {
        if(withdrawAmount > wallet) {
            
            MonopolyExe.infoConsole.setText("Player " + playerNumber + "went bankrupt!");
            setVisible(false); // Loser
            wallet = -1;
        }
        else wallet -= withdrawAmount;

    }

    public void depositToWallet(int depositAmount) {
        wallet += depositAmount;
        MonopolyExe.infoConsole.setText("Payday for player " + getPlayerNumber() + ". You earned $" + depositAmount + "!");
    }


    public void payToWallet(int depositAmount) {
        wallet += depositAmount;
    }

    public void travelToSTART() {
        currentSquareNumber = 0;
        MonopolyExe.infoConsole.setText("You moved to START");
    }

    public int getCurrentSquareNumber() {
        return currentSquareNumber;
    }

    public void setCurrentSquareNumber(int newPosition){
        this.currentSquareNumber = newPosition;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public boolean hasTitleDeed(int squareNumber) {
        return titilleDeeds.contains(squareNumber) ? true : false;
    }

    public void buyTitleDeed(int squareNumber) {
        if(ledger.containsKey(squareNumber)) {
            System.out.println("It's already owned. You cannot by here.");
        }
        else {
            titilleDeeds.add(this.getCurrentSquareNumber());
            ledger.put(squareNumber, this.getPlayerNumber()); // everytime a player buys a title deed, it is written in ledger, for example square 1 belongs to player 2
        }
    }

    public Player(int xCoord, int yCoord, int width, int height) {
        setBorder(new LineBorder(new Color(0, 0, 0)));
        setBounds(xCoord, yCoord, 20, 20);
        this.setLayout(null);
    }

    public Player(int playerNumber, Color color) {
        this.playerNumber = playerNumber;
        this.setBackground(color);
        lblPlayerNumber = new JLabel("" + playerNumber);
        lblPlayerNumber.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        this.add(lblPlayerNumber);
        this.setBounds(playerNumber * 30, 33, 20, 28); // need to fix here for adjustable player numbers
        totalPlayers++;
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
    }


    int[] xLocationsOfPlayer1 = {19, 99, 179, 259, 339, 419, 499, 579,
                                619, 619, 619, 619, 619, 619, 619,
                                499, 419, 339, 259, 179, 99, 19,
                                19, 19, 19, 19, 19, 19};

    int[] yLocationsOfPlayer1 = {19, 19, 19, 19, 19, 19, 19, 19,
                                99, 179, 259, 339, 419, 499, 579,
                                619, 619, 619, 619, 619, 619, 619,
                                499, 419, 339, 259, 179, 99};

    int[] xLocationsOfPlayer2 = {59, 139, 219, 299, 379, 459, 539, 619,
                                619, 619, 619, 619, 619, 619, 619,
                                539, 459, 379, 299, 219, 139, 59,
                                19, 19, 19, 19, 19, 19};

    int[] yLocationsOfPlayer2 = {19, 19, 19, 19, 19, 19, 19, 19,
                                139, 219, 299, 379, 459, 539, 619,
                                619, 619, 619, 619, 619, 619, 619,
                                539, 459, 379, 299, 219, 139};

    public void move(int dicesTotal) {
        if (currentSquareNumber + dicesTotal > 27) {
            depositToWallet(100);
        }

        int targetSquare = (currentSquareNumber + dicesTotal) % 28;
        
        currentSquareNumber = targetSquare;

        if (targetSquare == 7) {
            deductionToWallet(-50);
        }

        if (targetSquare == 21) {
            targetSquare = 0;
            currentSquareNumber = 0;
            MonopolyExe.infoConsole.setText("content here");
            System.out.println("Moved to START");
        }

        if (MonopolyExe.nowPlaying == 0) {
            this.setLocation(xLocationsOfPlayer1[targetSquare], yLocationsOfPlayer1[targetSquare]);
        }
        else {
            this.setLocation(xLocationsOfPlayer2[targetSquare], yLocationsOfPlayer2[targetSquare]);
        }
    }
/*
 * By comparing player's coordinates according to the board, we will get it's
 * (1) current square number
 * (2) currently unused, found a better way
 */

    public int getCurrentSquareNumberByCoordinates() {

        int x = this.getX();
        int y = this.getY();

        if (x < 80) {
            if (y < 80) {
                return 0;
            }
            else if (y > 80 && y < 160) {
                return 27;
            }
            else if (y > 160 && y < 240) {
                return 26;
            }
            else if (y > 240 && y < 320) {
                return 25;
            }
            else if (y > 320 && y < 400) {
                return 24;
            }
            else if (y > 400 && y < 480) {
                return 23;
            }
            else if (y > 480 && y < 560) {
                return 22;
            }
            else return 21;
		}
        else if (x > 80 && x < 160) {
            if (y < 80) {
                return 1;
            }
            else return 20;
        }
        else if (x > 160 && x < 240) {
            if (y < 80) {
                return 2;
            }
            else return 19;
        }
        else if (x > 240 && x < 320) {
            if (y < 80) {
                return 3;
            }
            else return 18;
        }
        else if (x > 320 && x < 400) {
            if (y < 80) {
                return 4;
            }
            else return 17;
        }
        else if (x > 400 && x < 480) {
            if (y < 80) {
                return 5;
            }
            else return 16;
        }
        else if (x > 480 && x < 560) {
            if (y < 80) {
                return 6;
            }
            else return 15;
        }
        else {
            if (y < 80) {
                return 7;
            }
            else if (y > 80 && y < 160) {
                return 8;
            }
            else if (y > 160 && y < 240) {
                return 9;
            }
            else if (y > 240 && y < 320) {
                return 10;
            }
            else if (y > 320 && y < 400) {
                return 11;
            }
            else if (y > 400 && y < 480) {
                return 12;
            }
            else if (y > 480 && y < 560) {
                return 13;
            }
            else return 14;

        }
    }
}
