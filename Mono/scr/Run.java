package source;

import javax.swing.JFrame;

public class Run {

    
    public static void main(String[] args) {

        Board test = new Board(4, 4, 648, 648);
        Dice dice1 = new Dice(279, 426, 40, 40);
        Dice dice2 = new Dice(329, 426, 40, 40);
        Interact right = new Interact(660, 4, 400, 648);

        JFrame window = new JFrame();
        window.setTitle("MONOPOLY");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setSize(1100, 700);
        window.add(dice1);
        window.add(dice2);
        window.add(test);
        window.add(right);
        window.setVisible(true);
    }
}
