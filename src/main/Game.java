package main;

public class Game implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private final int fpsSet = 120;
    private Thread gameThread ;
    private int frames = 0 ;
    private long lastCheck = 0;

    public Game(){
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }


    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run(){
        double time_per_frame = 1000000000.0 / fpsSet;
        long currentTime = System.nanoTime();
        long lastFrame = currentTime;
        while(true){
            currentTime = System.nanoTime();
            if(currentTime - lastFrame >= time_per_frame){
                gamePanel.repaint();
                lastFrame = currentTime;
                frames ++;
            }

            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS = " + frames);
                frames = 0;
            }
        }
    }
}
