package utils;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;

public class LoadSave {

    public static final String PLAYER_IMAGE = "player_sprites.png";
    public static final String LEVEL_IMAGE = "outside_sprites.png";
    public static final String LEVEL_1_DATA = "level_one_data.png";


    public static BufferedImage getFrameAtlas(String fileName) {//or player atlas
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        try{
             img = ImageIO.read(is);
        }catch(IOException e){
            e.printStackTrace();
        }
        return  img;
    }


    public static int[][]  getLevelData(){
        int[][] levelData = new int[Game.TILES_HEIGHT][Game.TILES_WIDTH];
        BufferedImage img = getFrameAtlas(LEVEL_1_DATA);
        for(int j =0 ;j<img.getHeight() ; j++)
            for(int i =0 ; i < img.getWidth() ; i++){
                Color color = new Color(img.getRGB(i , j));
                int value = color.getRed();
                if(value >= 48) value = 0;//for error handling in the level image
                levelData[j][i] = value;
            }
        return levelData;
    }
}
