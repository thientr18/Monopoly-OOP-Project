package MonopolypProject;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JFrame;

public class LabelDemo {
    public static void main(String[] args) {
                
        JFrame aplication = new JFrame();

        JLabel northJLabel = new JLabel("North");

        ImageIcon labelIcon = new ImageIcon("D:\\VS CODE\\Java\\MonopolypProject\\gameboard_1600-1200.png");

        JLabel centerJLabel = new JLabel(labelIcon);

        JLabel southLabel = new JLabel(labelIcon);

        southLabel.setText("South");

        aplication.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        aplication.add(centerJLabel, BorderLayout.CENTER);

        aplication.setSize(1624, 1250);
        aplication.setVisible(true);
    }
}