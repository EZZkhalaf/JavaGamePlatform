package levels;

public class Level {

    private int[][] levelData;

    public Level(int[][] levelData){
        this.levelData = levelData;
    }

    public int getFrameIndex(int x, int y){
        return levelData[y][x];
    }

    public int[][] getLevelData(){
        return levelData;
    }
}
