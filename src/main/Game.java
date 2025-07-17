package main;

import entities.Player;
import jdk.dynalink.support.SimpleRelinkableCallSite;
import levels.LevelHandler;

import java.awt.*;

public class Game implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private final int fpsSet = 120;
    private final int UPS_SET = 200;
    private Thread gameThread ;
    private int frames = 0 ;
    private long lastCheck = 0;
    private Player player;


    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.0f;
    public final static int TILES_WIDTH = 26;
    public final static int TILES_HEIGHT = 14;
    public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_DEFAULT_SIZE * TILES_WIDTH;
    public final static int GAME_HEIGHT = (int)(TILES_DEFAULT_SIZE * TILES_HEIGHT);
    public LevelHandler levelHandler;

    public Game(){
        initializeClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void initializeClasses() {
        player = new Player(200,200);
        levelHandler = new LevelHandler(this);
    }


    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        player.update();
        gamePanel.repaint();
        levelHandler.update();
    }
    public void render(Graphics g){
        levelHandler.draw(g);
        player.render(g);
    }

    public Player getPlayer(){
        return player;
    }
    public void windowFocusLost(){
        player.resetBoolean();
    }
    @Override
    public void run(){
        double time_per_frame = 1000000000.0 / fpsSet;
        double time_per_update = 1000000000.0 / UPS_SET;
        long currentTime = System.nanoTime();


        long previousTime = System.nanoTime();
        int update = 0;


        double deltaU = 0;
        double deltaF = 0;



        while(true){
            currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / time_per_update;
            deltaF += (currentTime - previousTime) / time_per_update;
            previousTime = currentTime;
            if(deltaU >= 1){
                update++;
                deltaU--;
            }

            if(deltaF >= 1){
                update();
                deltaF--;
                frames++;
            }




            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS = " + frames + " | UPS = " + update);
                frames = 0;
                update = 0;
            }
        }
    }
}
