package source;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class MonopolyExe extends JFrame{
    private JPanel contentIncluder;
    static int turnCounter = 0;
    JButton btnNextTurn;
    JButton btnBuy;
    JButton btnPayRent;
    JButton btnRoll;
    JButton btnGetCard;
    CardLayout c1 = new CardLayout();
    static int nowPlaying = 0;
    ArrayList<Player> players = new ArrayList<Player>();
    static JTextArea infoConsole;
    public static Object endGameLabel;
    Board gameBoard;
    JTextArea panelPlayer1TextArea;
    JTextArea panelPlayer2TextArea;
    Player player1;
    Player player2;
    Dice dice1;
    Dice dice2;
    Boolean doubleDiceP1 = false;
    Boolean doubleDiceP2 = false;
    JPanel playerAssetsPanel;

    

    public MonopolyExe(){
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setBackground(Color.GRAY);
        setSize(1100, 720);
        contentIncluder = new JPanel();
        contentIncluder.setBorder(new EmptyBorder(5,5,5, 5));
        setContentPane(contentIncluder);
	contentIncluder.setLayout(null);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBorder(new LineBorder(new Color(0, 0, 0)));
	layeredPane.setBounds(4, 4, 648, 648);
	contentIncluder.add(layeredPane);

        gameBoard = new Board(4,4,648,648);
	gameBoard.setBackground(new Color(51, 255, 153));
	layeredPane.add(gameBoard, Integer.valueOf(0));

	player1 = new Player(1, Color.RED);
	players.add(player1);
	layeredPane.add(player1, Integer.valueOf(1));

	player2 = new Player(2, Color.BLUE);
	players.add(player2);
	layeredPane.add(player2, Integer.valueOf(1));

        JPanel right = new JPanel();
        right.setBackground(Color.GRAY);
        right.setBorder(new LineBorder(new Color(0, 0, 0)));
        right.setBounds(660, 4, 400, 648);
        contentIncluder.add(right);
        right.setLayout(null);


        btnBuy = new JButton("Buy");
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
        btnBuy.setBounds(40, 480, 100, 20);
        right.add(btnBuy);
        btnBuy.setEnabled(false);

        btnNextTurn = new JButton("Next Turn");
        btnNextTurn.setBounds(150, 580, 100, 30);
        right.add(btnNextTurn);
        btnNextTurn.setEnabled(false);

        btnPayRent = new JButton("Pay Rent");
        btnPayRent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player currentPlayer = players.get(nowPlaying);
                Player ownerOfTheSquare = players.get((Player.ledger.get(currentPlayer.getCurrentSquareNumber())) == 1 ? 0 : 1);
                infoConsole.setText("You paid to the player " + ownerOfTheSquare.getPlayerNumber());
        
                int withdrawAmount = gameBoard.getAllSquare().get(currentPlayer.getCurrentSquareNumber()).getRentPrice();
        
                if (withdrawAmount > currentPlayer.getWallet()) {
                    infoConsole.setText("You do not have enough money to pay rent! That means you are LOSER");
                    btnNextTurn.setEnabled(true);
                    btnPayRent.setEnabled(false);
                }
                currentPlayer.withdrawFromWallet(withdrawAmount);
                ownerOfTheSquare.depositToWallet(withdrawAmount);
                btnNextTurn.setEnabled(true);
                btnPayRent.setEnabled(false);
                updatePanelPlayer1TextArea();
                updatePanelPlayer2TextArea();
            }
        });
        btnPayRent.setBounds(150, 480, 100, 20);
        right.add(btnPayRent);
        btnPayRent.setEnabled(false);

        btnGetCard = new JButton("Get Card");
        btnGetCard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                int currentSquareNumber = players.get(nowPlaying).getCurrentSquareNumber();
                if (currentSquareNumber == 4 || currentSquareNumber == 18) {{
                    Card card = new Card(Card.CardType.COMMUNITY, (int) (Math.random() * 8));
                    infoConsole.setText(card.text());
                    handleCardAction(card);
                    
                }} else if (currentSquareNumber == 11 || currentSquareNumber == 25) {{
                    Card card = new Card(Card.CardType.CHANCE, (int) (Math.random() * 8));
                    infoConsole.setText(card.text());
                    handleCardAction(card);
                    
                }}

            }
        });
        btnGetCard.setBounds(260, 480, 100, 20);
        right.add(btnGetCard);
        btnGetCard.setEnabled(false);

        Dice dice1 = new Dice(279, 426, 40, 40);
        layeredPane.add(dice1, Integer.valueOf(1));
        Dice dice2 = new Dice(329, 426, 40, 40);
        layeredPane.add(dice2, Integer.valueOf(1));

        btnRoll = new JButton("Roll Dice");
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
                    if(Player.ledger.containsKey(player1.getCurrentSquareNumber()) && Player.ledger.get(player1.getCurrentSquareNumber()) != player1.getPlayerNumber() ){
                        btnBuy.setEnabled(false);
                        btnRoll.setEnabled(false);
                        btnNextTurn.setEnabled(false);
                        btnPayRent.setEnabled(true);
                    }
                    if (player1.getCurrentSquareNumber() == 4 || player1.getCurrentSquareNumber() == 11 || player1.getCurrentSquareNumber() == 18 || player1.getCurrentSquareNumber() == 25){
                        btnGetCard.setEnabled(true);
                        btnNextTurn.setEnabled(false);
                    }
                    else if (player1.getCurrentSquareNumber() != 4 || player1.getCurrentSquareNumber() != 11 || player1.getCurrentSquareNumber() != 18 || player1.getCurrentSquareNumber() != 25){
                        btnGetCard.setEnabled(false);
                        btnNextTurn.setEnabled(true);
                    }
                    if (Player.ledger.containsKey(player1.getCurrentSquareNumber()) && Player.ledger.get(player1.getCurrentSquareNumber()) == player1.getPlayerNumber()){
                        btnBuy.setEnabled(false);
                        btnPayRent.setEnabled(false);
                        btnNextTurn.setEnabled(true);
                    }
                    if (player1.getWallet() < gameBoard.getAllSquare().get(player1.getCurrentSquareNumber()).getPrice() || gameBoard.getUnableBuySquares().contains(gameBoard.getAllSquare().get(player1.getCurrentSquareNumber()))){
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
                    if (player2.getCurrentSquareNumber() == 4 || player2.getCurrentSquareNumber() == 11 || player2.getCurrentSquareNumber() == 18 || player2.getCurrentSquareNumber() == 25){
                        btnGetCard.setEnabled(true);
                        btnNextTurn.setEnabled(false);
                    }
                    else if (player2.getCurrentSquareNumber() != 4 || player2.getCurrentSquareNumber() != 11 || player2.getCurrentSquareNumber() != 18 || player2.getCurrentSquareNumber() != 25){
                        btnGetCard.setEnabled(false);
                        btnNextTurn.setEnabled(true);
                    }
                    if (Player.ledger.containsKey(player2.getCurrentSquareNumber()) && Player.ledger.get(player2.getCurrentSquareNumber()) == player2.getPlayerNumber()){
                        btnBuy.setEnabled(false);
                        btnPayRent.setEnabled(false);
                    }
                    if (player2.getWallet() < gameBoard.getAllSquare().get(player2.getCurrentSquareNumber()).getPrice() || gameBoard.getUnableBuySquares().contains(gameBoard.getAllSquare().get(player2.getCurrentSquareNumber()))){
                        btnBuy.setEnabled(false);
                        btnNextTurn.setEnabled(true);
                    }
                    
                    // The case the squares that have no owner.
                    else if (!Player.ledger.containsKey(player2.getCurrentSquareNumber())){
                        btnBuy.setEnabled(true);
                        btnNextTurn.setEnabled(true);
                        btnPayRent.setEnabled(false);
                    }
                }
                btnRoll.setEnabled(false);
                if (doubleDiceP1 || doubleDiceP2 ){
                    infoConsole.setText("Please click next turn to continue");
                }
                // else if (players.get(nowPlaying).getCurrentSquareNumber() == 4 || players.get(nowPlaying).getCurrentSquareNumber() == 11 || players.get(nowPlaying).getCurrentSquareNumber() == 18 || players.get(nowPlaying).getCurrentSquareNumber() == 25) {
                //     infoConsole.setText("Please click Get Card to draw a card");
                //     btnGetCard.setEnabled(true);
                // }
                else {
                    infoConsole.setText("Please click!!!");
                }
                layeredPane.remove(gameBoard);
                layeredPane.add(gameBoard, Integer.valueOf(0));
                updatePanelPlayer1TextArea();
                updatePanelPlayer2TextArea();
            }
        });
        btnRoll.setBounds(150, 520, 100, 30);
        right.add(btnRoll);

        btnNextTurn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnRoll.setEnabled(true);
				btnBuy.setEnabled(false);
				btnPayRent.setEnabled(false);
				btnNextTurn.setEnabled(false);
				
				if(nowPlaying == 0 && doubleDiceP1) {
					nowPlaying = 0;
					doubleDiceP1 = false;
				} else if(nowPlaying == 1 && doubleDiceP2) {
					nowPlaying = 1;
					doubleDiceP2 = false;
				} else if(!doubleDiceP1 && !doubleDiceP2) {
					nowPlaying = (nowPlaying + 1) % 2;
				}
				
				
				c1.show(playerAssetsPanel, ""+(nowPlaying==0 ? 1 : 2)); // maps 0 to 1 and 1 to 2
				updatePanelPlayer1TextArea();
				updatePanelPlayer2TextArea();
				infoConsole.setText("It's now player "+(nowPlaying==0 ? 1 : 2)+"'s turn!");
			}
		});
        JPanel test = new JPanel();
        test.setBounds(80, 310, 240, 70);
        right.add(test);
        test.setLayout(null);

        playerAssetsPanel = new JPanel();
	playerAssetsPanel.setBounds(80, 30, 240, 180);
	right.add(playerAssetsPanel);
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

        panelPlayer1TextArea = new JTextArea();
	panelPlayer1TextArea.setBounds(10, 30, 220, 145);
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

	panelPlayer2TextArea = new JTextArea();
	panelPlayer2TextArea.setBounds(10, 30, 220, 145);
	panelPlayer2.add(panelPlayer2TextArea);

    updatePanelPlayer1TextArea();
    updatePanelPlayer2TextArea();

    infoConsole = new JTextArea();
	infoConsole.setColumns(20);
	infoConsole.setRows(5);
	infoConsole.setBounds(4, 4, 240, 60);
	test.add(infoConsole);
	infoConsole.setLineWrap(true);
	infoConsole.setText("Player 1 starts the game by clicking Roll Dice!");
    
    
    JLabel endGameLabel = new JLabel("");
    endGameLabel.setHorizontalAlignment(SwingConstants.CENTER);
    endGameLabel.setBounds(4, 70, 240, 20);
    test.add(endGameLabel);


    }

    private void handleCardAction(Card card) {
        switch (card.action()) {
            case BANK_MONEY:
                players.get(nowPlaying).depositToWallet(card.value());
                break;
            case MOVE_TO:
                int currentSquareNumber = players.get(nowPlaying).getCurrentSquareNumber();
                int travelTo = card.travelTo();
                if (travelTo < currentSquareNumber) {
                    players.get(nowPlaying).depositToWallet(200);
                }
                players.get(nowPlaying).move(travelTo - currentSquareNumber);
                break;
            default:
                break;
        }
        updatePanelPlayer1TextArea();
        updatePanelPlayer2TextArea();
    }

    

    public void updateInfoConsole(){
        String result = "";
        int withdrawAmount1 = gameBoard.getAllSquare().get(player1.getCurrentSquareNumber()).getRentPrice();
        if (withdrawAmount1 > player1.getWallet()){
            result = "Player 2 has WIN the game!!!";
        }
        int withdrawAmount2 = gameBoard.getAllSquare().get(player2.getCurrentSquareNumber()).getRentPrice();
        if (withdrawAmount2 > player2.getWallet()){
            result = "Player 1 has WIN the game!!!";
        }
        infoConsole.setText(result);

    }

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
    public static void main(String[] args) {
        MonopolyExe window = new MonopolyExe();
        window.setVisible(true);
    }
}
