package main;

import Objects.SuperObject;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Screen settings
        final int originalTileSize = 16;
       final int scale  = 3 ;
      public final  int tileSize  = originalTileSize * scale;
    public   final int maxScreenColumn = 16;
     public final int maxScreenRow = 12;
     public final int screenWidth = tileSize * maxScreenColumn;

      public final int screenHeight = tileSize * maxScreenRow;

        //world settings

    public final int maxWorldCol  = 50;
    public final int  maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;


      int FPS = 60;

TileManager tileM = new TileManager(this);
      KeyHandler keyH = new KeyHandler();
      //keep the game running
      Thread gameThread;
     public CollisionChecker cChecker = new CollisionChecker(this);

     public AssetSetter aSetter = new AssetSetter(this);
      public Player player = new Player(this, keyH);
      public SuperObject obj[]= new SuperObject[10];


      public GamePanel(){
          this.setPreferredSize(new Dimension(screenWidth ,screenHeight));
          this.setBackground(Color.BLACK);
          this.setDoubleBuffered(true);
          this.addKeyListener(keyH);
          this.setFocusable(true);
      }

    public void setUpGame(){
        aSetter.setObject();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
          double drawInterval = (double) 1000000000 /FPS; //0.01666 secs per frame
        //find the next drawing time, current time in nanosecs + the 0.0166
        double nextDrawTIme = System.nanoTime() + drawInterval;
        while(gameThread != null){

            long currentTime = System.nanoTime(); //get current time
            System.out.println("currentTime " + currentTime);
//            System.out.println("possibly an unending loop"); //a 
            // TODO: 2/6/2024 update information such as character positions 
            // TODO: 2/6/2024  draw the screen with updated information
            update();
            repaint();
            try {

            double remainingTime = nextDrawTIme - System.nanoTime();// the time between the nexttime and now , calculate it
            remainingTime = remainingTime /1000000; // dividing by a million cos sleep() only takes millsecs

            if(remainingTime < 0){
                remainingTime = 0;
            }
                //to prevent your laptop from overheating , saving resources
                //making the thread sleep for 0.016 secs before nextDrawTime
            Thread.sleep((long)remainingTime);

            nextDrawTIme += drawInterval; // nextdrawTime is updated
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update(){
    player.update();

    }


    //graphics g is like the paintbrush here
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //tile
tileM.draw(g2);

//object
        for (int i = 0; i < obj.length; i++) {
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }

//player
player.draw(g2);
        g2.dispose();
    }

}
