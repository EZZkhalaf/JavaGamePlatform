package utils;


import main.Game;

//this class is for checking if the player can move in this point etc
public class HelperMethods {

    public static boolean CanMoveHere(float x , float y , int width , int height , int[][] levelData ){
        if(!isSolid(x,y,levelData))
            if(!isSolid(x + width,y + height,levelData))
                if(!isSolid(x + width,y,levelData))
                    if(!isSolid(x,y + height,levelData))
                        return true;
        return false;
    }

    private static boolean isSolid(float x, float y , int[][] levelData){
        if(x < 0 || x >= Game.GAME_WIDTH)
            return true;
        if(y < 0 || y >= Game.GAME_HEIGHT)
            return true ;

        float xIndex = x/Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = levelData[(int)yIndex][(int)xIndex];
        if(value >= 48 || value < 0 || value != 11)
            return true ;
        return false ;
    }

}
