package entities;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static utils.Constants.playerConstants.*;

public class Player extends Entity{

    private BufferedImage[][] allAnimations;
    private int animationTick , animationIndex , animationSpeed = 13;
    private int playerAction = IDEAL;
    private int playerDirection = -1; //not moving
    private boolean moving = false , attacking = false;
    private float x,y;
    private boolean left , right , up , down ;
    private float playerSpeed = 2.0f;

    public Player(float x , float y){
        super(x,y);

        loadAnimation();
    }
    public void update(){
        setPlayerPosition();
        animationUpdate();
        setAnimation();
//        repaint();
    }
    public void render(Graphics g){
        g.drawImage(allAnimations[playerAction][animationIndex], (int)x, (int)y,128,80, null); // Example

    }


    private void loadAnimation() {

        InputStream is = getClass().getResourceAsStream("/player_sprites.png");
        try{
            BufferedImage img = ImageIO.read(is);
            allAnimations = new BufferedImage[9][6];
            for(int j =0 ; j< allAnimations.length; j++)
                for (int i =0 ; i < allAnimations[j].length ; i++){
                    allAnimations[j][i] = img.getSubimage(i*64 , j*40 , 64,40);
                }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setAttacking(boolean attack){
        this.attacking = attack;
    }

    public void resetBoolean(){
        left = false;
        right = false;
        up = false;
        down = false;
    }

    private void setPlayerPosition() {
        moving = false;
        if(right && !left){
            x += playerSpeed;
            moving = true;
        }else if(left && !right ){
            x -= playerSpeed;
            moving = true;
        }

        if(up && !down ){
            y -= playerSpeed;
            moving = true;
        }else if(down && !up){
            y += playerSpeed;
            moving = true;
        }
    }

    private void setAnimation() {

        int startAni = playerAction;

        if(moving){
            playerAction = RUN;
        }else{
            playerAction = IDEAL;
        }

        if(attacking){
            playerAction = ATTACK1;
        }
        if(startAni != playerAction) {
            resetAniTick();
        }
    }

    private void resetAniTick() {
        animationTick = 0;
        animationIndex = 0;
    }

    private void animationUpdate() {
        animationTick++;
        if(animationTick >= animationSpeed){
            animationTick =0;
            animationIndex ++ ;
            if(animationIndex >= getImagesAmount(playerAction)){
                animationIndex = 0;
                attacking = false;
            }
        }
    }


}

