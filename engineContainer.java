/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mwengine;

/**
 *
 * @author Phil
 */
    public class engineContainer implements Runnable
 {
     private Thread thread;
     private boolean running = false;
     private final double UPDATE_CAP = 1.0/60;
     private int width = 640, height = 480;
     private float scale = 1f;
     private String title = "MWengine v0.1";
   
 
    
    public void run()
    {
        running = true;       
        boolean render = false;
        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime = 0;
        double unprocessedTime = 0;
        
        double frameTime = 0;
        int frames = 0;
        int fps = 0;
        
        while (running)
        {
               render = false;
               firstTime = System.nanoTime() / 1000000000.0;
               passedTime = firstTime - lastTime;
               lastTime = firstTime;
               
               unprocessedTime += passedTime;
               frameTime += passedTime;
               
               while (unprocessedTime >= UPDATE_CAP)
               {
                   unprocessedTime -= UPDATE_CAP;
                   render = true;
                   
                   if (frameTime >= 1.0)
                   {
                       frameTime = 0;
                       fps = frames;
                       frames = 0;
                       System.out.println("fps: " + fps);
                   }
               }
               
               if (render)
               {
                   frames++;
               }    
               else 
               {                   
                   try
                   {
                       Thread.sleep(1);
                   }
                   catch (InterruptedException e)
                   {
                       e.printStackTrace();
                   }
               }
        }
        
        dispose();
    }

    public void start()
    {
        thread = new Thread(this);
        thread.run();
        
    }
    public void stop()
    {}

    public void dispose()
    {}
    
    public static void main(String args[])
    {
        engineContainer ec = new engineContainer();
        ec.start();
    }
    
    public engineContainer()
    {
        
    }
    
    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getScale() {
        return scale;
    }

    public String getTitle() {
        return title;
    }
 }
