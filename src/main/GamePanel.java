package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private float xDir = 1f, yDir = 1f;
    private BufferedImage bufferImage ;
    private BufferedImage[][] allAnimations;
    private int animationTick , animationIndex , animationSpeed = 20;


    public GamePanel() {
        mouseInputs = new MouseInputs(this);
        importBufferImage();
        loadAnimation();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

        setFocusable(true);
        requestFocusInWindow();
    }

    private void loadAnimation() {
        allAnimations = new BufferedImage[9][6];
        for(int j =0 ; j< allAnimations.length; j++)
            for (int i =0 ; i < allAnimations[j].length ; i++){
                allAnimations[j][i] = bufferImage.getSubimage(i*64 , j*40 , 64,40);
            }
    }

    public void importBufferImage() {
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");
        try{
            bufferImage = ImageIO.read(is);
        }catch(IOException e){
            e.printStackTrace();
        }

    }


    public void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void changeXDelta(int value) {
        this.xDelta += value;
    }

    public void changeYDelta(int value) {
        this.yDelta += value;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        animationUpdate();
         g.drawImage(allAnimations[1][animationIndex], (int)xDelta, (int)yDelta,128,80, null); // Example
        // repaint(); // If needed for animation
    }

    private void animationUpdate() {
        animationTick++;
        if(animationTick >= animationSpeed){
            animationTick =0;
            animationIndex ++ ;
            if(animationIndex >= allAnimations.length){
                animationIndex = 0;
            }
        }
    }
}
