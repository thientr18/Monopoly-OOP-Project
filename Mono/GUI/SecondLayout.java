package MONYPOLY;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecondLayout extends JFrame implements ActionListener {
    private JLabel playerLabel;
    private JLabel moneyLabel;
    private JButton startGameButton;
    private JButton exitButton;

    public SecondLayout() {
        setLayout(new GridLayout(5, 1));

        exitButton = new JButton("RETURN");
        
        playerLabel = new JLabel("Number of Player: ");
        moneyLabel = new JLabel("Amount of Money: ");

        startGameButton = new JButton("LET START");
        

        startGameButton.addActionListener(this);
        exitButton.addActionListener(this);

        add(playerLabel);
        add(moneyLabel);
        add(startGameButton);
        add(exitButton);

        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startGameButton) {
            int numberOfPlayer = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of players: "));
            int amountOfMoney = Integer.parseInt(JOptionPane.showInputDialog("Enter the amount of money: "));

            playerLabel.setText("Number of Player: " + numberOfPlayer);
            moneyLabel.setText("Amount of Money: " + amountOfMoney);
        } else if (e.getSource() == exitButton) {
            setVisible(false);
            FirstLayout firstLayout = new FirstLayout();
        }
    }
}