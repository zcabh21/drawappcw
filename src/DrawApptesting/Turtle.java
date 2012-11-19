package DrawApptesting;

import javafx.scene.canvas.GraphicsContext;

public class Turtle {
    
    private int x=0,y=0,angle=0;
    private GraphicsContext gc;
  
    public Turtle(GraphicsContext gc){     
        this.gc=gc;
    }
    
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        }
    
    public void moveForward(int distance){
    int newX =(int) (x + (distance* Math.sin(angle)));
    int newY = (int)(y - (distance* Math.cos(angle)));
    gc.strokeLine(x, y, newX, newY);
     //update
    x=newX;
    y=newY;
    }
    
  public void left(int angle){
   this.angle -= angle;

    }
     public void right(int angle){ 
    this.angle += (90-angle); 
    }    
}
