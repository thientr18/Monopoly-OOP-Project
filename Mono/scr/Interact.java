package Mono.scr;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Interact extends JPanel{

    CardLayout c1 = new CardLayout();
    static int nowPlaying = 0;
    ArrayList<Player> players = new ArrayList<Player>();
    static JTextArea infoConsole;
    Board gameBoard;
    JTextArea panelPlayer1TextArea;
    JTextArea panelPlayer2TextArea;
    Player player1;
    Player player2;
    Dice dice1;
    Dice dice2;
    Boolean doubleDiceP1 = false;
    Boolean doubleDiceP2 = false;

    public void updatePanelPlayer1TextArea(){
        String result = "";
        result += "Current Balance: "+player1.getWallet()+"\n";
        result += "Title Deed: \n";
        for (int i = 0; i < player1.getTitleDeeds().size();i++){
            result += " - "+gameBoard.getAllSquare().get(player1.getTitleDeeds().get(i)).getName()+"\n";
        }
        panelPlayer1TextArea.setText(result);
    }

    public void updatePanelPlayer2TextArea(){
        String result = "";
        result += "Current Balance: "+player2.getWallet()+"\n";
        result += "Title Deed: \n";
        for (int i = 0; i < player2.getTitleDeeds().size();i++){
            result += " - "+gameBoard.getAllSquare().get(player2.getTitleDeeds().get(i)).getName()+"\n";
        }
        panelPlayer2TextArea.setText(result);
    }

    public Interact(int x, int y, int width, int height){
        
        setBorder(new LineBorder(new Color(0, 0, 0)));
        setBounds(x, y, width, height);
        setBackground(Color.GRAY);
        this.setLayout(null);

    

        JPanel test = new JPanel();
        test.setBounds(80, 310, 240, 70);
        this.add(test);
        test.setLayout(null);

        // Player 1

        JPanel playerAssetsPanel = new JPanel();
		playerAssetsPanel.setBounds(80, 30, 240, 180);
		this.add(playerAssetsPanel);
		playerAssetsPanel.setLayout(c1);

        JPanel panelPlayer1 = new JPanel();
		panelPlayer1.setBackground(Color.RED);
		playerAssetsPanel.add(panelPlayer1, "1");
		panelPlayer1.setLayout(null);

        JLabel panelPlayer1Title = new JLabel("Player 1 All Wealth");
		panelPlayer1Title.setForeground(Color.WHITE);
		panelPlayer1Title.setHorizontalAlignment(SwingConstants.CENTER);
		panelPlayer1Title.setBounds(4, 4, 240, 15);
		panelPlayer1.add(panelPlayer1Title);

        JTextArea panelPlayer1TextArea = new JTextArea();
		panelPlayer1TextArea.setBounds(10, 30, 220, 150);
		panelPlayer1.add(panelPlayer1TextArea);

        //Player 2

        JPanel panelPlayer2 = new JPanel();
		panelPlayer2.setBackground(Color.BLUE);
		playerAssetsPanel.add(panelPlayer2, "2");
		panelPlayer2.setLayout(null);
		c1.show(playerAssetsPanel, ""+nowPlaying);

		JLabel panelPlayer2Title = new JLabel("Player 2 All Wealth");
		panelPlayer2Title.setForeground(Color.WHITE);
		panelPlayer2Title.setHorizontalAlignment(SwingConstants.CENTER);
		panelPlayer2Title.setBounds(0, 6, 240, 16);
		panelPlayer2.add(panelPlayer2Title);

		JTextArea panelPlayer2TextArea = new JTextArea();
		panelPlayer2TextArea.setBounds(10, 34, 230, 149);
		panelPlayer2.add(panelPlayer2TextArea);

        JButton btnBuy = new JButton("Buy");
        btnBuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Player currentPlayer = players.get(nowPlaying);
                infoConsole.setText("You bought "+gameBoard.getAllSquare().get(currentPlayer.getCurrentSquareNumber()).getName());
                currentPlayer.buyTitleDeed(currentPlayer.getCurrentSquareNumber());
                int withdrawAmount = gameBoard.getAllSquare().get(currentPlayer.getCurrentSquareNumber()).getPrice();
                currentPlayer.withdrawFromWallet(withdrawAmount);
                btnBuy.setEnabled(false);
                updatePanelPlayer1TextArea();
                updatePanelPlayer2TextArea();
            }
        });
        btnBuy.setBounds(80, 480, 100, 20);
        this.add(btnBuy);
        btnBuy.setEnabled(false);

        JButton btnNextTurn = new JButton("Next Turn");
        btnNextTurn.setBounds(150, 580, 100, 30);
        this.add(btnNextTurn);
        btnNextTurn.setEnabled(false);

        JButton btnPayRent = new JButton("Pay Rent");
        btnPayRent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Player currentPlayer = players.get(nowPlaying);
				Player ownerOfTheSquare = players.get((Player.ledger.get(currentPlayer.getCurrentSquareNumber()))==1?0:1);
				infoConsole.setText("You paid to the player "+ownerOfTheSquare.getPlayerNumber());

				int withdrawAmount = gameBoard.getAllSquare().get(currentPlayer.getCurrentSquareNumber()).getRentPrice();
				System.out.println(withdrawAmount);
				currentPlayer.withdrawFromWallet(withdrawAmount);
				ownerOfTheSquare.depositToWallet(withdrawAmount);
				
				btnNextTurn.setEnabled(true);
				btnPayRent.setEnabled(false);
				
				updatePanelPlayer1TextArea();
				updatePanelPlayer2TextArea();
			}

		});
        btnPayRent.setBounds(220, 480, 100, 20);
        this.add(btnPayRent);
        btnPayRent.setEnabled(false);

        JButton btnRoll = new JButton("Roll Dice");
        
        btnRoll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (nowPlaying == 0){
                    int dice1_old = dice1.getFaceDice();
                    int dice2_old = dice2.getFaceDice();
                    dice1.rollDice();
                    dice2.rollDice();
                    int dicesTotal = dice1.getFaceDice() + dice2.getFaceDice();
                    if (dice1.getFaceDice() == dice2.getFaceDice()){
                        doubleDiceP1 = true;
                    }
                    else {
                        doubleDiceP1 = false;
                    }
                    player1.move(dicesTotal);
                    if(Player.ledger.containsKey(player1.getCurrentSquareNumber()) && Player.ledger.get(player1.getCurrentSquareNumber()) != player1.getPlayerNumber()){
                        btnBuy.setEnabled(false);
                        btnRoll.setEnabled(false);
                        btnNextTurn.setEnabled(false);
                        btnPayRent.setEnabled(true);
                    }
                    if (Player.ledger.containsKey(player1.getCurrentSquareNumber()) && Player.ledger.get(player1.getCurrentSquareNumber()) == player1.getPlayerNumber()){
                        btnBuy.setEnabled(false);
                        btnPayRent.setEnabled(false);
                        btnNextTurn.setEnabled(true);
                    }
                    if (gameBoard.getUnableBuySquares().contains(gameBoard.getAllSquare().get(player1.getCurrentSquareNumber()))){
                        btnBuy.setEnabled(false);
                        btnNextTurn.setEnabled(true);
                    }
                    else if (!Player.ledger.containsKey(player1.getCurrentSquareNumber())){
                        btnBuy.setEnabled(true);
                        btnNextTurn.setEnabled(true);
                        btnPayRent.setEnabled(false);
                    }
                }
                else {
                    int dice1_old = dice1.getFaceDice();
                    int dice2_old = dice2.getFaceDice();
                    dice1.rollDice();
                    dice2.rollDice();
                    int dicesTotal = dice1.getFaceDice() + dice2.getFaceDice();
                    if (dice1.getFaceDice()== dice2.getFaceDice()){
                        doubleDiceP2 = true;
                    }
                    else {
                        doubleDiceP2 = false;
                    }
                    player2.move(dicesTotal);
                    if (Player.ledger.containsKey(player2.getCurrentSquareNumber()) && Player.ledger.get(player2.getCurrentSquareNumber()) != player2.getPlayerNumber()){
                        btnBuy.setEnabled(false);
                        btnRoll.setEnabled(false);
                        btnNextTurn.setEnabled(false);
                        btnPayRent.setEnabled(true);
                    }
                    if (Player.ledger.containsKey(player2.getCurrentSquareNumber()) && Player.ledger.get(player2.getCurrentSquareNumber()) == player2.getPlayerNumber()){
                        btnBuy.setEnabled(false);
                        btnPayRent.setEnabled(false);
                    }
                    if (gameBoard.getUnableBuySquares().contains(gameBoard.getAllSquare().get(player2.getCurrentSquareNumber()))){
                        btnBuy.setEnabled(false);
                        btnNextTurn.setEnabled(true);
                    }
                    else if (!Player.ledger.containsKey(player2.getCurrentSquareNumber())){
                        btnBuy.setEnabled(true);
                        btnNextTurn.setEnabled(true);
                        btnPayRent.setEnabled(false);
                    }
                }
            }
        });
        btnRoll.setBounds(150, 520, 100, 30);
        this.add(btnRoll);
        btnRoll.setEnabled(false);
    }
}
