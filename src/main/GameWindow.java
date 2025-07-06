package main;

import javax.swing.*;

public class GameWindow {
    private JFrame jFrame ;
    public GameWindow(GamePanel gamePanel){
        jFrame=new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(400,400);
        jFrame.add(gamePanel);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}
