package Mono.src;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Mono.GameMain;

public class Player extends JPanel {
    
    private int playerNumber;
    JLabel lblPlayerNumber;
    static int totalPlayers = 0;
    static HashMap<Integer, Integer> ledger = new HashMap<>();

    private int currentSquareNumber = 0; // Player's current lcation on (0-27). Initially zero
    private ArrayList<Integer> titilleDeeds = new ArrayList<Integer>(); // squares that the player owned
    private int wallet = 500; // Initial money

    public ArrayList<Integer> getTitleDeeds() {
        return titilleDeeds;
    }

    public int getWallet () {
        return wallet;
    } 

    public void withdrawFromWallet(int withdrawAmount) {
        if(withdrawAmount > wallet) {
            System.out.println("Player " + playerNumber + "went bankrupt!");
            setVisible(false); // Loser
        }
        else wallet -= withdrawAmount;
    }

    public void depositToWallet(int depositAmount) {
        wallet += depositAmount;
        System.out.println("Payday for player " + getPlayerNumber() + ". You earned $" + depositAmount + "!");
    }

    public int getCurrentSquareNumber() {
        return currentSquareNumber;
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
        setBorder(getBorder());
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

    int[] xLocationsOfPlayer1 = {};

    int[] yLocationsOfPlayer1 = {};

    int[] xLocationsOfPlayer2 = {};

    int[] yLocationsOfPlayer2 = {};

    public void move(int dicesTotal) {
        if (currentSquareNumber + dicesTotal > 27) {
            depositToWallet(100);
        }

        int targetSquare = (currentSquareNumber + dicesTotal) % 28;
        currentSquareNumber = targetSquare;

        if (Interact.nowPlaying == 0) {
            this.setLocation(xLocationsOfPlayer1[targetSquare], yLocationsOfPlayer1[targetSquare]);
        }
        else {
            this.setLocation(xLocationsOfPlayer2[targetSquare], yLocationsOfPlayer2[targetSquare]);
        }

        if (ledger.containsKey(this.getCurrentSquareNumber())) {
            Interact.infoConsole.setText("This property belongs to player "+ledger.get(this.getCurrentSquareNumber()));
        }
    }

/*
 * By comparing player's coordinates according to the board, we will get it's
 * (1) current square number
 * (2) currently unused, found a better way
 */


}
