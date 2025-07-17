package levels;

import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelHandler {

    public Game game ;
    private BufferedImage[] levelFrame;
    private Level levelOne;



    public LevelHandler(Game game){
        this.game = game;
        importOutsideFrames();
        levelOne = new Level(LoadSave.getLevelData());
    }

    private void importOutsideFrames() {
        levelFrame = new BufferedImage[48];
        BufferedImage img = LoadSave.getFrameAtlas(LoadSave.LEVEL_IMAGE);
        for (int j =0 ; j< 4 ; j++)
            for(int i = 0; i < 12 ; i++){
                int index = j*12 + i ;
                levelFrame[index] = img.getSubimage(i*32 , j*32 , 32,32);
        }
    }


    public void draw(Graphics g){

        for(int j =0; j < Game.TILES_HEIGHT ; j++)
            for(int i =0 ; i< Game.TILES_WIDTH;i++){
                int index = levelOne.getFrameIndex(i , j);
                g.drawImage(levelFrame[index],i*Game.TILES_SIZE,j*Game.TILES_SIZE,null);
            }

    }

    public void update(){

    }

    public Level getCurrentLevel(){
        return levelOne;
    }


}
