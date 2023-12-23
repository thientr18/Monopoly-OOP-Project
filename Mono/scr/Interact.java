package source;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Interact extends JPanel{

    CardLayout c1 = new CardLayout();
    static int nowPlaying = 0;

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
        btnBuy.setBounds(80, 480, 100, 20);
        this.add(btnBuy);
        btnBuy.setEnabled(false);

        JButton btnPayRent = new JButton("Pay Rent");
        btnPayRent.setBounds(220, 480, 100, 20);
        this.add(btnPayRent);
        btnPayRent.setEnabled(false);

        JButton btnRoll = new JButton("Roll Dice");
        btnRoll.setBounds(150, 520, 100, 30);
        this.add(btnRoll);
        btnRoll.setEnabled(true);

        JButton btnNextTurn = new JButton("Next Turn");
        btnNextTurn.setBounds(150, 580, 100, 30);
        this.add(btnNextTurn);
        btnNextTurn.setEnabled(false);
    }
}
