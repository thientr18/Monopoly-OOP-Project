package GUI;

import javax.swing.*;

import src.MonopolyExe;

import java.awt.*;

public class GUI extends JFrame {

    public static void main(String[] args) {
        
        ImageIcon image1 = new ImageIcon("Mono\\media\\Image\\Button.png");
        ImageIcon image2 = new ImageIcon("Mono\\media\\Image\\GameRule.png");

        JFrame frame = new JFrame();
        frame.setTitle("MONOPOLY GAME");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1100, 720);

        // Creating a custom JPanel to set the background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("Mono\\media\\Image\\gui-background.jpg");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(null);
        frame.setContentPane(backgroundPanel);

        JButton startButton = createButton("START", image1, 700, 370, backgroundPanel);
        JButton ruleButton = createButton("RULE", image1, 700, 450, backgroundPanel);
        JButton exitButton = createButton("EXIT", image1, 700, 530, backgroundPanel);

        JButton returnButton = createButton("BACK", image1, 610, 610, backgroundPanel);
        returnButton.setVisible(false);

        JPanel rulePanel = new JPanel();
        JLabel ruleLabel = new JLabel(image2);
        rulePanel.add(ruleLabel);
        rulePanel.setBounds(10, 10, 900, 600);
        rulePanel.setVisible(false);
        backgroundPanel.add(rulePanel);

        startButton.addActionListener(e -> {
            MonopolyExe mono = new MonopolyExe();
            mono.setVisible(true);
            frame.setVisible(false);
        });

        ruleButton.addActionListener(e -> {
            rulePanel.setVisible(true);
            startButton.setVisible(false);
            exitButton.setVisible(false);
            ruleButton.setVisible(false);
            returnButton.setVisible(true);
        });

        exitButton.addActionListener(e -> System.exit(0));
        
        returnButton.addActionListener(e -> {
            rulePanel.setVisible(false);
            startButton.setVisible(true);
            exitButton.setVisible(true);
            ruleButton.setVisible(true);
            returnButton.setVisible(false);
        });
        
        frame.setVisible(true);
    }

    private static JButton createButton(String text, ImageIcon icon, int x, int y, JPanel panel) {
        JButton button = new JButton(text, icon);
        button.setBounds(x, y, 300, 60);
        button.setFocusable(false);
        button.setHorizontalTextPosition(JButton.LEFT);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setFont(new Font("Comic Sans", Font.BOLD, 34));
        button.setIconTextGap(-5);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(255, 189, 89));
        button.setBorder(BorderFactory.createEtchedBorder());
        panel.add(button);
        return button;
    }
}
