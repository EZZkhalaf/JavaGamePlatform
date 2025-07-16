package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.playerConstants.*;
import static utils.Constants.DIRECTIONS.*;
public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private float xDir = 1f, yDir = 1f;
    private BufferedImage bufferImage ;
    private BufferedImage[][] allAnimations;
    private int animationTick , animationIndex , animationSpeed = 13;

    private int playerAction = IDEAL;
    private int playerDirection = -1; //not moving
    private boolean moving = false;
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


    public void setDirection(int direction){
        this.playerDirection = direction;
        moving = true;
    }

    public void setMoving(boolean isMove){
        this.moving = isMove;
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


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        animationUpdate();
        setAnimation();
        setPlayerPosition();
         g.drawImage(allAnimations[playerAction][animationIndex], (int)xDelta, (int)yDelta,128,80, null); // Example
        // repaint(); // If needed for animation
    }

    private void setPlayerPosition() {
        if(moving){
            switch(playerDirection){
                case LEFT :
                    xDelta -=5;
                    break;
                case UP :
                    yDelta -=5;
                    break;
                case RIGHT :
                    xDelta+=5;
                    break;
                case DOWN :
                    yDelta +=5;
                    break;

            }
        }
    }

    private void setAnimation() {
        if(moving){
            playerAction = RUN;
        }else{
            playerAction = IDEAL;
        }
    }

    private void animationUpdate() {
        animationTick++;
        if(animationTick >= animationSpeed){
            animationTick =0;
            animationIndex ++ ;
            if(animationIndex >= getImagesAmount(playerAction)){
                animationIndex = 0;
            }
        }
    }
}
