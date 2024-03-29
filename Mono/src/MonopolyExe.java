package src;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
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
    JButton btnPlayAgain;
    JButton btnExit;
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
    JPanel playerAssetsPanel;
    Boolean ReceivedCard = false;
    JPanel BackgroundWin1;
    JPanel BackgroundWin2;
    private Sound themeSound, rollSound, nextTurnSound, buySound, paySound, cardSound;

    public MonopolyExe(){
        themeSound = new Sound("Mono\\media\\Sound\\Sound_for_ThemeGame.wav");
        themeSound.loop();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MONOPOLY");
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

        player1 = new Player(1, new Color(89, 107, 227));
        players.add(player1);
        layeredPane.add(player1, Integer.valueOf(1));

        player2 = new Player(2, new Color(222,100, 100));
        players.add(player2);
        layeredPane.add(player2, Integer.valueOf(1));

        Dice dice1 = new Dice(305, 440, 40, 40);
        layeredPane.add(dice1, Integer.valueOf(1));
        Dice dice2 = new Dice(355, 440, 40, 40);
        layeredPane.add(dice2, Integer.valueOf(1));

        JPanel right = new JPanel();
        right.setBackground(new Color(51, 255, 153));
        right.setBorder(new LineBorder(new Color(0, 0, 0)));
        right.setBounds(660, 4, 400, 648);
        contentIncluder.add(right);
        right.setLayout(null);
        right.setVisible(true);

        JPanel win1 = new JPanel();
        win1.setBorder(new LineBorder(new Color(0, 0, 0)));
        win1.setBounds(660, 4, 400, 648);
        win1.setLayout(null);
        contentIncluder.add(win1);
        win1.setVisible(false);
   
        JPanel win2 = new JPanel();
        win2.setBorder(new LineBorder(new Color(0, 0, 0)));
        win2.setBounds(660, 4, 400, 648);
        win2.setLayout(null);
        contentIncluder.add(win2);
        win2.setVisible(false);
       
/*
----------------------------------Button to control the game---------------------------------------------------------------------------------------------------------------------------------------
*/
        btnBuy = new JButton(new ImageIcon("Mono\\media\\Image\\Buy.png"));
        btnBuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                buySound = new Sound("Mono\\media\\Sound\\paySound.wav");
                buySound.play();
                Player currentPlayer = players.get(nowPlaying);
                infoConsole.setText("You bought "+gameBoard.getAllSquare().get(currentPlayer.getCurrentSquareNumber()).getName()+"\nPlease click next turn to continue");
                currentPlayer.buyTitleDeed(currentPlayer.getCurrentSquareNumber());
                int withdrawAmount = gameBoard.getAllSquare().get(currentPlayer.getCurrentSquareNumber()).getPrice();
                currentPlayer.withdrawFromWallet(withdrawAmount);
                btnBuy.setEnabled(false);
                updatePanelPlayer1TextArea();
                updatePanelPlayer2TextArea();
            }
        });
        btnBuy.setBounds(40, 480, 100, 40);
        right.add(btnBuy);
        btnBuy.setEnabled(false);


        btnPayRent = new JButton(new ImageIcon("Mono\\media\\Image\\payRent.png"));
        btnPayRent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paySound = new Sound("Mono\\media\\Sound\\paySound.wav");
                paySound.play();
                Player currentPlayer = players.get(nowPlaying);
                Player ownerOfTheSquare = players.get((Player.ledger.get(currentPlayer.getCurrentSquareNumber())) == 1 ? 0 : 1);
                infoConsole.setText("You paid to the player " + ownerOfTheSquare.getPlayerNumber()+ "\nPlease click Next Turn!");
                int withdrawAmount = gameBoard.getAllSquare().get(currentPlayer.getCurrentSquareNumber()).getRentPrice();
                currentPlayer.withdrawFromWallet(withdrawAmount);
                ownerOfTheSquare.payToWallet(withdrawAmount);
                btnNextTurn.setEnabled(true);
                btnPayRent.setEnabled(false);
                if (player1.getWallet() < 0){
                    win2.setVisible(true);
                    right.setVisible(false);
                }
                else if (player2.getWallet() < 0){
                    win1.setVisible(true);
                    right.setVisible(false);
                }
                updatePanelPlayer1TextArea();
                updatePanelPlayer2TextArea();

            }
        });
        btnPayRent.setBounds(150, 480, 100, 40);
        right.add(btnPayRent);
        btnPayRent.setEnabled(false);

        btnGetCard = new JButton(new ImageIcon("Mono\\media\\Image\\getCard.png"));
        btnGetCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                cardSound = new Sound("Mono\\media\\Sound\\CardSound.wav");
                cardSound.play();
                int currentSquareNumber = players.get(nowPlaying).getCurrentSquareNumber();
                if (currentSquareNumber == 4 || currentSquareNumber == 18) {
                    Card card = new Card(Card.CardType.COMMUNITY, (int) (Math.random() * 8));
                    infoConsole.setText(card.text());
                    handleCardAction(card);
                }
                else if (currentSquareNumber == 11 || currentSquareNumber == 25) {
                    Card card = new Card(Card.CardType.CHANCE, (int) (Math.random() * 8));
                    infoConsole.setText(card.text());
                    handleCardAction(card);
                }
                btnNextTurn.setEnabled(true);
                btnGetCard.setEnabled(false);
                if (player1.getWallet() < 0){
                    win2.setVisible(true);
                    right.setVisible(false);
                    
                }
                else if (player2.getWallet() < 0){
                    win1.setVisible(true);
                    right.setVisible(false);
                }
            }
        });
        btnGetCard.setBounds(260, 480, 100, 40);
        right.add(btnGetCard);
        btnGetCard.setEnabled(false);



        btnRoll = new JButton(new ImageIcon("Mono\\media\\Image\\Roll.png"));
        btnRoll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                rollSound = new Sound("Mono\\media\\Sound\\rollSoundButton.wav");

                if (doubleDiceP1 || doubleDiceP2 ){
                    infoConsole.setText("Please click next turn, and you can continue");    
                }
                else {
                    infoConsole.setText("Please click next turn to end your turn");
                }

                if (nowPlaying == 0){

                    dice1.rollDice();
                    dice2.rollDice();

                    int dicesTotal = dice1.getFaceDice() + dice2.getFaceDice();
                    if (dice1.getFaceDice() == dice2.getFaceDice()){
                        doubleDiceP1 = true;
                        rollSound.play();
                    }
                    else {
                        doubleDiceP1 = false;
                        rollSound.play();
                    }
                    player1.move(dicesTotal);
                    if(Player.ledger.containsKey(player1.getCurrentSquareNumber()) && Player.ledger.get(player1.getCurrentSquareNumber()) != player1.getPlayerNumber() ){
                        btnBuy.setEnabled(false);
                        btnRoll.setEnabled(false);
                        btnNextTurn.setEnabled(false);
                        btnPayRent.setEnabled(true);
                        btnGetCard.setEnabled(false);
                        infoConsole.setText("Rent Price at " + gameBoard.getAllSquare().get(player1.getCurrentSquareNumber()).getName()+ " is " + gameBoard.getAllSquare().get(player1.getCurrentSquareNumber()).getRentPrice());
                    } 
                    if (gameBoard.getCardSquares().contains(gameBoard.getAllSquare().get(player1.getCurrentSquareNumber()))){
                        btnGetCard.setEnabled(true);
                        btnNextTurn.setEnabled(false);
                    }

                    if (Player.ledger.containsKey(player1.getCurrentSquareNumber()) && Player.ledger.get(player1.getCurrentSquareNumber()) == player1.getPlayerNumber()){
                        btnBuy.setEnabled(false);
                        btnPayRent.setEnabled(false);
                        btnNextTurn.setEnabled(true);
                        btnGetCard.setEnabled(false);
                    }

                    if (gameBoard.getUnableBuySquares().contains(gameBoard.getAllSquare().get(player1.getCurrentSquareNumber()))){
                        btnBuy.setEnabled(false);
                        btnNextTurn.setEnabled(true);
                        if(player1.getCurrentSquareNumber() == 21){
                            updatePanelPlayer1TextArea();
                        }
                        if (gameBoard.getCardSquares().contains(gameBoard.getAllSquare().get(player1.getCurrentSquareNumber()))){
                            btnBuy.setEnabled(false);
                            btnGetCard.setEnabled(true);
                            btnNextTurn.setEnabled(false);
                        }
                    }
                    
                    else if (!Player.ledger.containsKey(player1.getCurrentSquareNumber())){
                        btnBuy.setEnabled(true);
                        btnNextTurn.setEnabled(true);
                        btnPayRent.setEnabled(false);
                        btnGetCard.setEnabled(false);
                        infoConsole.setText("Player 1\nDo you want to buy: " +gameBoard.getAllSquare().get(player1.getCurrentSquareNumber()).getName() +" -" +gameBoard.getAllSquare().get(player1.getCurrentSquareNumber()).getPrice());
                        if (player1.getWallet() < gameBoard.getAllSquare().get(player1.getCurrentSquareNumber()).getPrice()){
                            btnBuy.setEnabled(false);
                            infoConsole.setText("Please click Next Turn");
                        }
                    }
                }
                else {

                    dice1.rollDice();
                    dice2.rollDice();


                    int dicesTotal = dice1.getFaceDice() + dice2.getFaceDice();
                    if (dice1.getFaceDice()== dice2.getFaceDice()){
                        doubleDiceP2 = true;
                        rollSound.play();
                    }
                    else {
                        doubleDiceP2 = false;
                        rollSound.play();
                    }
                    player2.move(dicesTotal);
                    if (Player.ledger.containsKey(player2.getCurrentSquareNumber()) && Player.ledger.get(player2.getCurrentSquareNumber()) != player2.getPlayerNumber()){
                        btnBuy.setEnabled(false);
                        btnRoll.setEnabled(false);
                        btnNextTurn.setEnabled(false);
                        btnPayRent.setEnabled(true);
                        btnGetCard.setEnabled(false);
                        infoConsole.setText("Rent Price at " + gameBoard.getAllSquare().get(player2.getCurrentSquareNumber()).getName()+ " is " + gameBoard.getAllSquare().get(player2.getCurrentSquareNumber()).getRentPrice());
                    }
                    if (gameBoard.getCardSquares().contains(gameBoard.getAllSquare().get(player2.getCurrentSquareNumber()))){
                        btnGetCard.setEnabled(true);
                        btnNextTurn.setEnabled(false);
                    }


                    if (Player.ledger.containsKey(player2.getCurrentSquareNumber()) && Player.ledger.get(player2.getCurrentSquareNumber()) == player2.getPlayerNumber()){
                        btnBuy.setEnabled(false);
                        btnPayRent.setEnabled(false);
                        btnNextTurn.setEnabled(true);
                        btnGetCard.setEnabled(false);
                    }



                    // The case the squares unable to buy
                    if (gameBoard.getUnableBuySquares().contains(gameBoard.getAllSquare().get(player2.getCurrentSquareNumber()))){
                        btnBuy.setEnabled(false);
                        btnNextTurn.setEnabled(true);
                        if (player2.getCurrentSquareNumber() == 21 ){
                            updatePanelPlayer2TextArea();
                        }
                        if (gameBoard.getCardSquares().contains(gameBoard.getAllSquare().get(player2.getCurrentSquareNumber()))){
                            btnBuy.setEnabled(false);
                            btnGetCard.setEnabled(true);
                            btnNextTurn.setEnabled(false);
                        }
                            
                    }
                    // The case the squares that have no owner.
                    else if (!Player.ledger.containsKey(player2.getCurrentSquareNumber())){
                        btnBuy.setEnabled(true);
                        btnNextTurn.setEnabled(true);
                        btnPayRent.setEnabled(false);
                        btnGetCard.setEnabled(false);
                        infoConsole.setText("Player 2\nDo you want to buy: " +gameBoard.getAllSquare().get(player2.getCurrentSquareNumber()).getName() +" -" +gameBoard.getAllSquare().get(player2.getCurrentSquareNumber()).getPrice());
                        if (player2.getWallet() < gameBoard.getAllSquare().get(player2.getCurrentSquareNumber()).getPrice()){
                            btnBuy.setEnabled(false);
                            infoConsole.setText("Please click Next Turn");
                        }
                    }
                }
                btnRoll.setEnabled(false);
                Player currentPlayer = players.get(nowPlaying);
                updatePanelPlayer1TextArea();
                updatePanelPlayer2TextArea();

                if (gameBoard.getUnableBuySquares().contains(gameBoard.getAllSquare().get(currentPlayer.getCurrentSquareNumber()))){
                    if (gameBoard.getCardSquares().contains(gameBoard.getAllSquare().get(currentPlayer.getCurrentSquareNumber()))){
                        infoConsole.setText("Get your card!");
                    }
                }
                
                
                layeredPane.remove(gameBoard);
                layeredPane.add(gameBoard, Integer.valueOf(0));
                updatePanelPlayer1TextArea();
                updatePanelPlayer2TextArea();
            }
        });
        btnRoll.setBounds(125, 530, 150, 40);
        right.add(btnRoll);

        btnNextTurn = new JButton(new ImageIcon("Mono\\media\\Image\\nextTurn.png"));
        btnNextTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextTurnSound = new Sound("Mono\\media\\Sound\\ClickSound.wav");
                nextTurnSound.play();
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
        btnNextTurn.setBounds(125, 580, 150, 40);
        right.add(btnNextTurn);
        btnNextTurn.setEnabled(false);
/*
-----------------------------Panel to show information of the players---------------------------------------------------------------------------------------------------------------------------
*/
        // For the Player 1
        JPanel test = new JPanel();
        test.setBounds(80, 310, 240, 70);
        right.add(test);
        test.setLayout(null);
        playerAssetsPanel = new JPanel();
        playerAssetsPanel.setBounds(80, 30, 240, 180);
        right.add(playerAssetsPanel);
        playerAssetsPanel.setLayout(c1);

        JPanel panelPlayer1 = new JPanel();
        panelPlayer1.setBackground(new Color(89, 107, 227));
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
        //For Player 2

        JPanel panelPlayer2 = new JPanel();
        panelPlayer2.setBackground(new Color(222,100, 100));
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


        // WIN GAME 
        btnPlayAgain = new JButton(new ImageIcon("Mono\\media\\Image\\PlayAgainButton.png"));
        btnPlayAgain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                dispose();
                MonopolyExe mono = new MonopolyExe();
		mono.setVisible(true);
		mono.setResizable(false);
            }
        });
        btnPlayAgain.setBounds(115, 470, 170, 50);
        btnPlayAgain.setEnabled(true);

        
        btnExit = new JButton(new ImageIcon("Mono\\media\\Image\\ExitButton.png"));
        btnExit.addActionListener(e -> {
            dispose();
            GUI.main(null);          
        });
        btnExit.setBounds(115, 540, 170, 50);
        btnExit.setEnabled(true);
        
        BackgroundWin1 = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                themeSound.stop();
                ImageIcon winGameImage1 = new ImageIcon("Mono\\media\\Image\\Player1Win.gif");
                Image image1 = winGameImage1.getImage();
                g.drawImage(image1,0, 0, getWidth(), getHeight(), this);
                this.add(btnExit);
                this.add(btnPlayAgain);
            }
        };
        BackgroundWin1.setBounds(0, 0, 400, 648);
        win1.add(BackgroundWin1);
        
        BackgroundWin2 = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                themeSound.stop();
                ImageIcon winGameImage2 = new ImageIcon("Mono\\media\\Image\\Player2Win.gif");
                Image image2 = winGameImage2.getImage();
                g.drawImage(image2,0, 0, getWidth(), getHeight(), this);
                this.add(btnExit);
                this.add(btnPlayAgain);
            }
        };
        BackgroundWin2.setBounds(0, 0, 400, 648);      
        win2.add(BackgroundWin2);
    }

    private void handleCardAction(Card card) {
        switch (card.action()) {
            case BANK_MONEY:
                players.get(nowPlaying).payToWallet(card.value());
                break;
            case MOVE_TO:
                int currentSquareNumber = players.get(nowPlaying).getCurrentSquareNumber();
                int travelTo = card.travelTo();
                if (travelTo < currentSquareNumber) {
                    players.get(nowPlaying).payToWallet(100);
                }
                players.get(nowPlaying).move(travelTo - currentSquareNumber);
                break;
            default:
                break;
        }
        updatePanelPlayer1TextArea();
        updatePanelPlayer2TextArea();
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
            result += " - "+ gameBoard.getAllSquare().get(player2.getTitleDeeds().get(i)).getName()+"\n";
        }
        panelPlayer2TextArea.setText(result);
    }
    public static void main(String[] args) {
        MonopolyExe window = new MonopolyExe();
        window.setVisible(true);
        window.setResizable(false);
    }
}
