package main;


import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta = 100 , yDelta = 100;
    private float xDir = 1f , yDir = 1f;


    public GamePanel(){
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

        setFocusable(true);
        requestFocusInWindow();
    }

    public  void changeXDelta(int value){
        this.xDelta += value;
    }
    public  void changeYDelta(int value){
        this.yDelta += value;
    }


    public void setRectPosition(int x ,int y){
        this.xDelta = x;
        this.yDelta = y;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        updateRectangle();
        g.setColor(Color.blue);
        g.fillRect((int)xDelta,(int)yDelta,50,50);



//        repaint();
    }

    private void updateRectangle(){
        xDelta += xDir;
        if(xDelta > 400 || xDelta < 0){
            xDir *= -1;
        }

        yDelta += yDir ;
        if(yDelta>400 || yDelta <0){
            yDir *= -1;
        }
    }
}
