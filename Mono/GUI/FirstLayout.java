package MONYPOLY;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstLayout extends JFrame implements ActionListener {
    private JButton startButton;
    private JButton exitButton;

    public FirstLayout() {
        setLayout(new GridLayout(4, 1));

        startButton = new JButton("START");
        exitButton = new JButton("EXIT");

        startButton.addActionListener(this);
        exitButton.addActionListener(this);

        add(startButton);
        add(exitButton);

        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            setVisible(false);
            SecondLayout secondLayout = new SecondLayout();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new FirstLayout();
    }
}