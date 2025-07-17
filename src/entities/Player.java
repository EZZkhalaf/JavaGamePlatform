package entities;

import main.GamePanel;
import utils.LoadSave;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import utils.LoadSave.*;
import static utils.Constants.playerConstants.*;
import static utils.HelperMethods.CanMoveHere;
public class Player extends Entity{

    private BufferedImage[][] allAnimations;
    private int animationTick , animationIndex , animationSpeed = 13;
    private int playerAction = IDEAL;
    private int playerDirection = -1; //not moving
    private boolean moving = false , attacking = false;
    private boolean left , right , up , down ;
    private float playerSpeed = 2.0f;
    private int[][] levelData;
    public Player(float x , float y , int width , int height){
        super(x,y,width,height);

        loadAnimation();
    }
    public void update(){
        setPlayerPosition();
        updateHitBox();
        animationUpdate();
        setAnimation();
    }
    public void render(Graphics g){
        g.drawImage(allAnimations[playerAction][animationIndex], (int)x - 30, (int) y - 30,128,80, null); // Example
        drawHitBox(g);
    }


    private void loadAnimation() {
            BufferedImage img = LoadSave.getFrameAtlas(LoadSave.PLAYER_IMAGE);
            allAnimations = new BufferedImage[9][6];
            for(int j =0 ; j< allAnimations.length; j++)
                for (int i =0 ; i < allAnimations[j].length ; i++){
                    allAnimations[j][i] = img.getSubimage(i*64 , j*40 , 64,40);
                }
    }

    public void loadLevelData(int[][] levelData){
        this.levelData = levelData;
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

        if(!left && !right && !up && !down)
            return ;

        float xSpeed = 0 , ySpeed = 0;

        if(right && !left)
            xSpeed = playerSpeed;

        else if(left && !right )
            xSpeed = -playerSpeed;


        if(up && !down )
            ySpeed = -playerSpeed;

        else if(down && !up)
            ySpeed = playerSpeed;

        if(CanMoveHere(x + xSpeed , y + ySpeed , width , height , levelData)){
            this.x += xSpeed;
            this.y += ySpeed;
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

